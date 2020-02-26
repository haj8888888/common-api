package com.common.api.util;


import com.google.common.collect.Lists;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串处理通用类
 *
 * @author pasino
 * @version 2017年12月1日
 * @see StringUtils
 */
public class StringUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(StringUtils.class.getSimpleName());

    /**
     * 将对象转换成字符串
     *
     * @param o 对象
     * @return 对象为null 返回null 否则返回对象toString()
     */
    public static String c(Object o) {
        return o == null ? "" : o.toString();
    }

    /**
     * 比较两个字符串是否相等， 都为null 也相等
     *
     * @param a 字符串
     * @param b 字符串
     * @return true 相等 false 不相等
     */
    public static boolean eq(String a, String b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }

    /**
     * 比较两个对象是否相等  都为null 也相等
     *
     * @param a 对象a
     * @param b 对象b
     * @return true 相等 false 不相等
     */
    public static boolean eq(Object a, Object b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }

    /**
     * 判断对象转换字符串后 是否包含字符串'.'
     *
     * @param a 对象
     * @return true 包含字符串'.' 否则false
     */
    public static boolean nodot(Object a) {
        return a == null || !a.toString().contains(".");
    }

    /**
     * 正则表达式查找字符串  未查询 返回null 否则返回查询的第一个 的指定组 的字符串
     * 如果组号小于0 或大于查询的组号 返回null
     *
     * @param input   输入字符串
     * @param pattern 正则查找字符串
     * @param group   组id
     * @return 返回指定组号查询的字符串
     */
    public static String macthonestr(String input, String pattern, int group) {
        Matcher m = Pattern.compile(pattern).matcher(input);
        if (m.find()) {
            if (group >= 0 && group <= m.groupCount()) {
                return m.group(group);
            }
        }
        return null;
    }

    /**
     * 找到匹配的第一个字符串的结果
     *
     * @param input   输入字符串
     * @param pattern 正则查找字符串
     * @return 匹配的第一个字符串的结果
     * @see #macthonestr(String, String, int)
     */
    public static String macthonestr(String input, String pattern) {
        return macthonestr(input, pattern, 0);
    }

    /**
     * 读取文件返回文件内容
     *
     * @param
     * @return
     */
    public static String readStream(InputStream input, String charCode) {
        try {
            return stream2String(input, charCode, true);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 读取文件返回文件内容
     *
     * @param
     * @return
     */
    public static String readStream(InputStream input) {
        try {
            return stream2String(input, "utf-8");
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 读取文件返回文件内容
     *
     * @param file
     * @return
     */
    public static String readFile(File file, String charCode) {
        try {
            return stream2String(new FileInputStream(file), charCode);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 读取文件返回以UTF-8读取文件内容
     *
     * @param file
     * @return
     */
    public static String readFile(File file) {
        try {
            return readFile(file, "utf-8");
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 读取input Stream 字符串
     *
     * @param in
     * @return
     */
    public static ByteArrayOutputStream stream2StringNoClose(InputStream in) {
        try {

            int byteread = 0;

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[8196];
            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            out.flush();
            return out;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 读取input Stream 字符串
     *
     * @param in       input stream
     * @param charCode 字符串编码
     * @param close    是否关闭流
     * @return 读取input stream 成字符串
     */
    public static String stream2String(InputStream in, String charCode, boolean close) {
        String result = null;
        try {

            int byteread = 0;

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[8196];
            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            result = new String(out.toByteArray(), charCode);
            out.flush();
            out.close();
            if (close) {
                in.close();
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 读取input Stream 字符串 且关闭流
     *
     * @param in       input stream
     * @param charCode 字符串编码
     * @return 读取input stream 成字符串
     */
    public static String stream2String(InputStream in, String charCode) {
        return stream2String(in, charCode, true);
    }

    /**
     * 判断字符串是否为空字符串
     *
     * @param a 字符串
     * @return 字符串null或者trim后长度为0 返回true 否则false
     */
    public static boolean isNULL(String a) {
        return a == null || a.trim().length() == 0;
    }

    /**
     * 生产随机字符串根据指定长度，随机字符串只包含大小写字母和数字
     *
     * @param len 随机字符串长度
     * @return 随机字符串
     */
    public static String noncestr(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            int v = 0;
            int m = 0;
            int r = RandomUtils.nextInt(26);
            if (r > 9) {
                m = RandomUtils.nextInt(2);
            } else {
                m = RandomUtils.nextInt(3);
            }
            switch (m) {
                case 0:
                    v = 65 + r;
                    break;
                case 1:
                    v = 97 + r;
                    break;
                case 2:
                    v = 48 + r;
                    break;
                default:
                    v = 65 + r;
                    break;
            }

            ret += (char) v;
        }
        return ret;
    }

    public static String getStringByObject(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String getStringByMapKey(Map<String, Object> obj, String key) {
        if (obj == null || key == null) {
            return "";
        }
        return obj.get(key) == null ? "" : obj.get(key).toString();
    }

    /**
     * @Description: 数组转集合
     */
    public static List<String> getListString(String roles) {
        List<String> roleList = null;
        if (!StringUtils.isNULL(roles)) {
            roles = roles.replace("[", "").replace("]", "");
            String[] roleArray = roles.split(",");
            roleList = Lists.newArrayList();
            for (String role : roleArray) {
                roleList.add(role);
            }
        }
        return roleList;
    }

    /**
     * 替换指定位置的字符
     *
     * @param str     原字符串
     * @param index   替找的位置
     * @param newChar 替换后的新字符
     * @return
     */
    public static String replaceOfIndex(String str, int index, char newChar) {
        if (isEmpty(str)) {
            str = "0";
        }

        if (index == 0) {
            return newChar + str.substring(1);
        }

        if (index == str.length() - 1) {
            //LOGGER.info(str.substring(0,index));  //包含 startIndex,不包含endIndex
            return str.substring(0, index) + newChar;
        }

        String startStr = str.substring(0, index);
        String endStr = str.substring(index + 1);

        return startStr + newChar + endStr;
    }


    /**
     * 判断字符串为空或者长度是否等于0
     * 2017/1/12 tangli 新增null字符串判断
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return "".equals(clear(str)) || isNullStr(str);
    }

    /**
     * 判断多个字符串是否为空，其中有一个为空，返回真
     *
     * @param strs
     * @return
     */
    ////@Deprecated
    public static boolean isEmpty(String... strs) {
        return isOneEmpty(strs);
    }

    /**
     * 判断多个字符串是否为空，其中有一个为空，返回真
     *
     * @param strs
     * @return
     */
    public static boolean isOneEmpty(String... strs) {
        if (strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个字符串是否都为空，所有都为空，返回真
     *
     * @param strs
     * @return
     */
    public static boolean isAllEmpty(String... strs) {
        if (strs.length == 0) {
            return true;
        }
        int len = strs.length;
        int flagCount = 0;
        for (String str : strs) {
            if (isEmpty(str)) {
                flagCount++;
            }
        }
        if (len == flagCount) {
            return true;
        }
        return false;
    }

    /**
     * 判断多个字符串是否都不空，所有都不空，返回真
     *
     * @param strs
     * @return
     */
    public static boolean isAllNotEmpty(String... strs) {
        return !isOneEmpty(strs);
    }

    /**
     * 判断字符串是否为null字符串
     *
     * @param str
     * @return
     */
    public static boolean isNullStr(String str) {
        return "null".equals(str);
    }

    /**
     * 判断字符串值不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断多个字符串是否不为空
     *
     * @param strs
     * @return
     */
    public static boolean isNotEmpty(String... strs) {
        return !isEmpty(strs);
    }

    /**
     * 判断参数a是否为空，如果为空则返回b (适用与字符串等所有对象)
     *
     * @param <K>
     * @param a
     * @param b
     * @return
     */
    public static <K> K nvl(K a, K b) {
        return a == null ? b : a;
    }


    /**
     * 去掉字符串前后的空格，如果为空则返回""
     *
     * @param a
     * @return
     */
    public static String clear(String a) {
        return nvl(a, "").trim();
    }

    /**
     * 去除String[] 数组的空白位置
     *
     * @param obj
     * @return
     */
    public static String[] trim(String[] obj) {
        String[] r = obj;
        int num = 0;
        for (int i = 0; i < r.length; i++) {
            if (r[i] == null || r[i].trim().length() < 1)
                num++;
        }
        if (num > 0) {
            r = new String[r.length - num];
            int j = 0;
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null && obj[i].trim().length() > 0)
                    r[j++] = obj[i].trim();
            }
        }
        return r;
    }

    /**
     * 去除String 的空白位置, 为空返回null
     *
     * @param obj
     * @return
     */
    public static String trimAndNull(String obj) {
        String str = obj.trim();
        return (isEmpty(str)) ? null : str;
    }

    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    /**
     * 截取字符串中的type
     *
     * @param url
     * @return
     */
    public static String getBussType(String url) {
        String s = org.apache.commons.lang.StringUtils.substringAfter(url, "type=");
        if (StringUtils.isEmpty(s)) {
            return "";
        }

        String[] split = s.split("&");
        if (split.length == 0) {
            return "";
        }
        return split[0];
    }


    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     */
    public static BigDecimal changeF2Y(String amount) {
        if (isEmpty(amount)) {
            return new BigDecimal(0).setScale(2, BigDecimal.ROUND_DOWN);
        }
        return BigDecimal.valueOf(Double.valueOf(amount))
                .divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount
     * @return
     */
    public static String changeY2F(String amount) {
        if (isEmpty(amount)) {
            return "0";
        }
        return BigDecimal.valueOf(Double.valueOf(amount)).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toString();
    }

    /**
     * 四舍五入把double转化int整型，0.5进一，小于0.5不进一
     *
     * @param number
     * @return
     */
    public static int getInt(double number) {
        BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }

}
