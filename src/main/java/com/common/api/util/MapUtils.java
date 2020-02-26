package com.common.api.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 接口对对象 map 数据的一些公关操作
 */
@Slf4j
public class MapUtils {
    /**
     * 得到map 的key值 并转换字符串
     *
     * @param data map对象
     * @param key  map key.
     * @return 返回map 的key值 并转换字符串
     */
    public static String getStringObjectDefaultNull(Map<?, ?> data, Object key) {
        return getStringObjectDefault(data, key, null);
    }

    /**
     * 得到map中key对着的value值并转化成字符串 不存在或者数据为空返回默认值
     *
     * @param data     map对象
     * @param key      map key
     * @param defaults 默认值
     * @return map中key对着的value值并转化成字符串 不存在或者数据为空返回默认值
     */
    public static String getStringObjectDefault(Map<?, ?> data, Object key, String defaults) {
        if (data == null || key == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return defaults;
        }
        Object p = data.get(key);
        if (p == null) {
            return defaults;
        }
        String n = p.toString();
        return n.trim().length() < 1 ? defaults : n;
    }


    /**
     * 得到map 对象key的value值并转换成Integer
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Integer getIntegerObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Integer.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }


    /**
     * 得到map 对象key的value值并转换成Integer
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Short getShortObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Short.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }

    /**
     * 得到map 对象key的value值并转换成Double
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Double getDoubleObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Double.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }


    public static Integer getIntegerObjectDefault(Map<?, ?> data, String key, Integer defVal) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return defVal;
        }
        try {
            return Integer.valueOf(data1);
        } catch (Throwable e) {
        }
        return defVal;
    }

    /**
     * 得到map 对象key的value值并转换成Integer
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Byte getByteObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Byte.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }

    /**
     * 得到map 对象key的value值并转换成Integer
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Byte getByteObjectDefault(Map<?, ?> data, String key, Byte def) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        try {
            return Byte.valueOf(data1);
        } catch (Throwable e) {
        }
        return def;
    }


    /**
     * 得到map 对象key的value值并转换成Date
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Date值 无法转换或不存在返回null
     */
    public static Date getDateObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(data1);
        } catch (Throwable e) {
        }
        return null;
    }


    /**
     * 得到map 对象key的value值并转换成Long
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Long值 无法转换或不存在返回null
     */
    public static Long getLongObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Long.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }

    /**
     * 得到map 对象key的value值并转换成Long
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Long值 无法转换或不存在返回null
     */
    public static Long getLongObjectDefault(Map<?, ?> data, String key, Long def) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        try {
            return Long.valueOf(data1);
        } catch (Throwable e) {
        }
        return def;
    }


    /**
     * 得到map 对象key的value值并转换成List<Long>
     *
     * @param data map对象
     * @param key  key值
     * @return map中的List<Long>值 无法转换或不存在返回null
     */
    public static List getList(Map<?, ?> data, String key) {
        if (data == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        Object v = data.get(key);
        if (v == null) {
            return null;
        }
        List list = Lists.newArrayList();

        if (v instanceof List) {
            List<?> list2 = (List<?>) v;
            if (list2 != null) {
                for (Object o : list2) {
                    if (o != null) {
                        String p = o.toString().trim();
                        if (p.length() > 0) {
                            try {

                                list.add(p);
                            } catch (Throwable e) {
                            }
                        }
                    }
                }
            }
        } else if (v instanceof Object[]) {
            Object[] list2 = (Object[]) v;
            if (list2 != null) {
                for (Object o : list2) {
                    String p = o.toString().trim();
                    if (p.length() > 0) {
                        try {

                            list.add(p);
                        } catch (Throwable e) {
                        }
                    }
                }
            }
        } else {
            String p = v.toString().trim();
            if (p.length() > 0) {
                try {

                    list.add(p);
                } catch (Throwable e) {
                }
            }
        }
        return list.isEmpty() ? null : list;
    }

    /**
     * 得到map 对象key的value值并转换成List<Long>
     *
     * @param data map对象
     * @param key  key值
     * @return map中的List<Long>值 无法转换或不存在返回null
     */
    public static List<Long> getLongList(Map<?, ?> data, String key) {
        try {
            List list = getList(data, key);
            if (list == null || list.isEmpty()) {
                return null;
            }
            List<Long> resultList = Lists.newArrayList();
            for (Object ele : list) {
                resultList.add(Long.valueOf(ele.toString()));
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到map 对象key的value值并转换成String
     *
     * @param data map对象
     * @param key  key值
     * @return map中的String值 无法转换或不存在返回null
     */
    public static String getMapData(Map<?, ?> data, Object key) {
        if (data == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        Object p = data.get(key);
        if (p == null) {
            return null;
        }
        String n = p.toString();
        return n.trim().length() < 1 ? null : n;
    }

    public static <T> List<T> getStringList(Class<T[]> type, Map<?, ?> data, Object key) {
        String jsonList = MapUtils.getMapData(data, key);
        if (StringUtils.isEmpty(jsonList)) {
            return null;
        }
        return JsonUtils.parseList(type, jsonList);
    }

    /**
     * 得到map 对象key的value值并转换成String
     *
     * @param data map对象
     * @param key  key值
     * @return map中的String值 无法转换或不存在返回null
     */
    public static String getMapDataContainKeyDefault(Map<?, ?> data, Object key, String containKeyDefaultValue) {
        if (data == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        Object p = data.get(key);
        if (p == null) {
            return containKeyDefaultValue;
        }
        String n = p.toString();
        return n.trim().length() < 1 ? containKeyDefaultValue : n;
    }

    /**
     * 得到map 对象key的value值并转换成默认值的类
     *
     * @param data                   map对象
     * @param key                    key值
     * @param containKeyDefaultValue map中的String值 无法转换或不存在返回null
     * @return
     */
    public static <T> T getMapData(Map<?, ?> data, String key, T containKeyDefaultValue) {
        if (data == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        Object p = data.get(key);
        if (p == null) {
            return containKeyDefaultValue;
        }

        if (containKeyDefaultValue instanceof String) {
            String n = p.toString();
            T s = n.trim().length() < 1 ? containKeyDefaultValue : (T) n;
            return s;
        }
        if (containKeyDefaultValue instanceof Long) {
            return (T) getLongObjectDefaultNull(data, key);
        }
        if (containKeyDefaultValue instanceof Integer) {
            return (T) getIntegerObjectDefaultNull(data, key);
        }
        if (containKeyDefaultValue instanceof Date) {
            return (T) getDateObjectDefaultNull(data, key);
        }
        return (T) p;
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null) {
            return null;
        }
        return JsonUtils.parse(beanClass, JsonUtils.format(map));
    }

    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return JsonUtils.parse(Map.class, JsonUtils.format(obj));
    }
}
