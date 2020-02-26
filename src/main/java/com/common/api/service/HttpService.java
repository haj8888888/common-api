package com.common.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class HttpService {

    @Autowired
    private OkHttpClient okHttpClient;

    public byte[] getRaw(String url) {
        log.debug("prepare to send http request, url={}", url);
        Request request = new Request.Builder().get()
                .url(url)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request)
                    .execute();
            return response.body().bytes();
        } catch (IOException e) {
            log.warn("send http request failed error response = {}", response);
            e.printStackTrace();
        }
        return new byte[1];
    }

    public Response head(String url) throws IOException {
        log.debug("prepare to send http request, url={}", url);
        Request request = new Request.Builder().head()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request)
                .execute();
        if (!response.isSuccessful()) {
            log.warn("send http request failed");
            throw new IOException("error response: " + response);
        }
        return response;
    }

    public JSONObject get(String url){
        return get(url, null);
    }

    public String getString(String url) throws IOException {
        return getString(url, null);
    }

    public String getString(String url, Map<String, Object> params) throws IOException {
        log.debug("prepare to send http request, url={}, params={}", url,
                params == null ? null : Arrays.toString(params.entrySet().toArray()));
        if (params != null) {
            try {
                //处理get请求参数
                url = parseUrl(url, params, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Request request = new Request.Builder().get()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request)
                .execute();
        if (!response.isSuccessful()) {
            log.warn("send http request failed");
            throw new IOException("error response: " + response);
        }
        String returnContent = response.body().string();
        log.debug("send http request, url={}, params={}, result = {}", url,
                params == null ? null : Arrays.toString(params.entrySet().toArray()), returnContent);
        return returnContent;
    }

    public JSONObject get(String url, Map<String, Object> params){
        String returnContent = null;
        try {
            returnContent = getString(url, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!"{".equals(returnContent.subSequence(0, 1))) {
            returnContent = "{'list':" + returnContent + "}";
        }
        JSONObject result = new JSONObject(JSON.parseObject(returnContent, Map.class));
        log.debug("send http request(get), result = {}, url = {}, params = {}", result, url, params);
        return result;
    }

    public JSONObject post(String url, Map<String, Object> params) throws IOException {
        return post(url, params, null, null);
    }

    public JSONArray postJSONArray(String url, Map<String, Object> params) throws IOException {
        JSONObject resultObj = post(url, params, null, null);
        JSONArray arr = resultObj.getJSONArray("datas");
        return arr;
    }

    public JSONObject post(String url, Map<String, Object> params, Map<String, File> files) throws IOException {
        return post(url, params, files, null);
    }

    public JSONObject post(String url, JSONObject json) {  //发送json字符串格式的数据
        log.debug("prepare to send http request, url={}, json={}", url, json);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, json.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response;
        String returnContent = null;
        try {
            response = okHttpClient.newCall(request)
                    .execute();
            if (!response.isSuccessful()) {
                log.warn("send http request failed");
                throw new IOException("error response: " + response);
            }
            returnContent = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject result = new JSONObject(JSON.parseObject(returnContent, Map.class));
        log.debug("send http request, url={}, params={}, result = {}", url,
                json == null ? null : json, returnContent);
        return result;
    }

    public String postString(String url, Map<String, Object> params) throws IOException {
        return postString(url, params, null, null);
    }

    public String postString(String url, Map<String, Object> params, Map<String, File> files, Map<String, Object> headers) throws IOException {
        log.debug("prepare to send http request, url={}, params={}, files={}, headers={}", url,
                params == null ? null : Arrays.toString(params.entrySet().toArray()),
                files == null ? null : Arrays.toString(files.entrySet().stream().map(e -> e.getValue().getName())
                        .collect(Collectors.toList()).toArray()),
                headers == null ? null : Arrays.toString(headers.entrySet().toArray()));
        try {
            RequestBody requestBody;
            // 纯文本
            if (files == null) {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                if (params != null) {
                    params.entrySet()
                            .forEach(e -> {
                                if (e.getValue() instanceof Collection) {
                                    ((Collection) e.getValue()).forEach(c -> formBodyBuilder.add(e.getKey(), c.toString()));
                                } else {
                                    //新增判断 值为空的不传
                                    if (e.getValue() != null) {
                                        formBodyBuilder.add(e.getKey(), e.getValue().toString());
                                    }
                                }
                            });
                }
                requestBody = formBodyBuilder.build();
            } else { // 有文件，用multipart
                MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM);
                if (params != null) {
                    params.entrySet()
                            .forEach(e -> {
                                if (e.getValue() instanceof Collection) {
                                    ((Collection) e.getValue()).forEach(c -> multipartBodyBuilder.addFormDataPart(e.getKey(), c.toString()));
                                } else {
                                    multipartBodyBuilder.addFormDataPart(e.getKey(), e.getValue().toString());
                                }
                            });
                }
                MediaType mediaType = MediaType.parse("application/octet-stream; charset=UTF-8");
                files.entrySet()
                        .forEach(e -> multipartBodyBuilder.addFormDataPart(e.getKey(), e.getValue().getName(),
                                RequestBody.create(mediaType, e.getValue())));
                requestBody = multipartBodyBuilder.build();
            }

            Request.Builder requestBuilder = new Request.Builder()
                    .url(url)
                    .post(requestBody);
            if (headers != null) {
                headers.entrySet()
                        .forEach(e -> requestBuilder.header(e.getKey(), e.getValue().toString()));
            }

            Request request = requestBuilder.build();
            Response response = okHttpClient.newCall(request)
                    .execute();
            if (!response.isSuccessful()) {
                log.warn("send http request failed");
                throw new IOException("error response: " + response);
            }
            String returnContent = response.body().string();
            log.debug("send http request, url={}, result = {}", url, returnContent);
            return returnContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public JSONObject post(String url, Map<String, Object> params, Map<String, File> files, Map<String, Object> headers)
            throws IOException {
        try {
            String returnContent = postString(url, params, files, headers);
            JSONObject result = new JSONObject(JSON.parseObject(returnContent, Map.class));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("send http request failed", e);
            throw e;
        }
    }

    //url 编辑
    private static String parseUrl(String url, Map<String, Object> params, String charcode) throws Exception {
        String charcode2 = "utf-8";
        try {
            Charset.forName(charcode.trim());
            charcode2 = charcode.trim();
        } catch (Throwable e) {
        }

        String murl = url;
        Map<String, String[]> param = new LinkedHashMap();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                Object value = entry.getValue();
                List<String> list = new ArrayList<>();
                if (value == null) {
                    list.add("");
                } else if (value instanceof Collection) {
                    Collection vl = (Collection) value;
                    for (Object v : vl) {
                        if (v != null) {
                            list.add(v.toString());
                        } else {
                            list.add("");
                        }
                    }
                } else if (value.getClass().isArray()) {
                    int len = Array.getLength(value);
                    for (int i = 0; i < len; i++) {
                        Object v = Array.get(value, i);
                        if (v != null) {
                            list.add(v.toString());
                        } else {
                            list.add("");
                        }
                    }
                } else {
                    list.add(value.toString());
                }
                param.put(entry.getKey(), list.toArray(new String[list.size()]));
            }
        }
        List<String> mp = new ArrayList<>();
        for (String k : param.keySet()) {
            for (String m : param.get(k)) {
                mp.add(k + "=" + URLEncoder.encode(m, charcode2));
            }
        }
        String params2 = StringUtils.join(mp, "&");
        if (params2.length() > 0) {
            if (murl.contains("?")) {
                murl = murl + "&" + params2;
            } else {
                murl = murl + "?" + params2;
            }
        }
        return murl.replaceAll("&{2,}", "&");
    }


}