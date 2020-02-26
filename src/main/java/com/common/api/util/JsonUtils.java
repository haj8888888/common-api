package com.common.api.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * json 字符串的读写对象操作
 *
 * @author pasino
 */
public class JsonUtils {
    /**
     * input 流转换成字符串
     *
     * @param in
     * @return
     */
    public static String parse(InputStream in) {
        StringBuffer buffer = new StringBuffer();

        byte[] bytes = new byte[8096];

        try {
            while (in.read(bytes) != -1) {
                buffer.append(new String(bytes));
            }

            return buffer.toString();

        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * json inputStream 转换成对象 List<T>
     *
     * @param type
     * @param input
     * @return 成功转换返回对象 ，失败返回null
     */
    public static <T> List<T> parseList(Class<T[]> type, InputStream input) {
        T[] ts = parse(type, parse(input));
        if (ts == null) {
            return null;
        }
        return Lists.newArrayList(ts);
    }

    /**
     * json inputStream 转换成对象
     *
     * @param type
     * @param input
     * @return 成功转换返回对象 ，失败返回null
     */
    public static <T> T parse(Class<T> type, InputStream input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            JsonParser jp = mapper.getFactory().createParser(parse(input));
            jp.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
            return mapper.readValue(input, type);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * json string 转换成对象 List<T>
     *
     * @param type
     * @param input
     * @return 成功转换返回对象 ，失败返回null
     */
    public static <T> List<T> parseList(Class<T[]> type, String input) {

        T[] ts = parse(type, input);
        if (ts == null) {
            return null;
        }
        return Lists.newArrayList(ts);
    }


    /**
     * json String 转换成对象
     *
     * @param type
     * @param input
     * @return 成功转换返回对象，失败返回null
     */
    public static <T> T parse(Class<T> type, String input) {
        if (input == null) {
            return null;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            JsonParser jp = mapper.getFactory().createParser(input);
            jp.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
            T t = mapper.readValue(jp, type);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * json String 转换成对象
     *
     * @param input json字符串集合
     * @param types 要转换字符串集合对象
     * @return 成功转换返回对象，失败返回null
     */
    public static List<Object> parse(String input, Class<?>... types) {
        if (input == null || types == null) {
            return null;
        }
        List<Object> list = new ArrayList<Object>();
        String in = input;
        long v = 0;
        for (Class<?> type : types) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                JsonParser jp = mapper.getFactory().createParser(in);
                jp.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
                Object t = mapper.readValue(jp, type);
                list.add(t);
                v = jp.getCurrentLocation().getCharOffset() + 1;
                if (v >= in.length()) {
                    continue;
                }
                in = in.substring((int) v);
            } catch (Exception e) {
                list.add(null);
            }
        }
        return list;

    }

    /**
     * 对象转换成json字符串
     *
     * @return 成功转换返回字符串 ，失败返回null
     */
    public static String formatPrint(Object t) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String m = mapper.writeValueAsString(t);
            //System.out.print(m);
            return m;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对象转换成json字符串
     *
     * @return 成功转换返回字符串 ，失败返回null
     */
    public static String format(Object t) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            return mapper.writeValueAsString(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Object... data) {
        if (data == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (Object o : data) {
            buffer.append(format(o));
        }
        return buffer.toString();
    }

    public static String parseString(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                return leaf.asText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<String> parseStringList(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);

            if (leaf != null) {
                return mapper.convertValue(leaf, new TypeReference<List<String>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer parseInteger(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                return leaf.asInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Integer> parseIntegerList(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);

            if (leaf != null) {
                return mapper.convertValue(leaf, new TypeReference<List<String>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Long parseLong(String body, String field) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null) {
                return leaf.asLong();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


