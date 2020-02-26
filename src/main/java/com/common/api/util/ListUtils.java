package com.common.api.util;


import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 集合排序工具类
 */
public class ListUtils {

    /**
     * @param targetList 目标排序List
     * @param sortField  排序字段(实体类属性名)
     * @param sortMode   排序方式（asc or  desc）
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> void sort(List<T> targetList, final String sortField, final String sortMode) {
        if (CollectionUtils.isEmpty(targetList) || StringUtils.isEmpty(sortField)) {
            return;
        }
        Collections.sort(targetList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    //首字母转大写
                    String newStr = sortField.substring(0, 1).toUpperCase() + sortField.replaceFirst("\\w", "");
                    String methodStr = "get" + newStr;

                    Method method1 = ((T) obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr, null);
                    if (method1.invoke(((T) obj2), null) == null || method2.invoke(((T) obj2), null) == null)
                        return retVal;

                    if (sortMode != null && "asc".equals(sortMode)) {
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString()); // 正序
                    } else {
                        retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString()); // 倒序
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return retVal;
            }
        });
    }

    /**
     * @param targetList 目标排序List 根据某日期字段排序
     * @param sortField  排序字段(实体类属性名)
     * @param sortMode   排序方式（asc or  desc）
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> void sortDate(List<T> targetList, final String sortField, final String sortMode) {
        if (CollectionUtils.isEmpty(targetList) || StringUtils.isEmpty(sortField)) {
            return;
        }
        Collections.sort(targetList, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                try {
                    //首字母转大写
                    String newStr = sortField.substring(0, 1).toUpperCase() + sortField.replaceFirst("\\w", "");
                    String methodStr = "get" + newStr;

                    Method method1 = ((T) obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr, null);

                    Date dt1 = (Date) method1.invoke(((T) obj1), null);
                    Date dt2 = (Date) method2.invoke(((T) obj2), null);
                    if (dt1 == null) {
                        return -1;
                    }
                    if (dt2 == null) {
                        return 1;
                    }
                    if (sortMode != null && "asc".equals(sortMode)) {
                        return compareTo(dt1.getTime(), dt2.getTime());
                    } else {
                        return compareTo(dt2.getTime(), dt1.getTime());
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        });
    }

    /**
     * @param targetList 目标排序List 根据map某key排序, key对应的值必须为日期类型
     * @param sortField  排序字段(实体类属性名)
     * @param sortMode   排序方式（asc or  desc）
     */
    public static void sortMapDate(List<Map<String, Object>> targetList, final String sortField, final String sortMode) {
        if (CollectionUtils.isEmpty(targetList) || StringUtils.isEmpty(sortField)) {
            return;
        }
        Collections.sort(targetList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    String s1 = (String) o1.get(sortField);
                    String s2 = (String) o2.get(sortField);
                    if (StringUtils.isEmpty(s1)) {
                        return -1;
                    }
                    if (StringUtils.isEmpty(s2)) {
                        return 1;
                    }
                    Date d1 = sdf.parse(s1);
                    Date d2 = sdf.parse(s2);
                    if (sortMode != null && "asc".equals(sortMode)) {
                        // 正序
                        return compareTo(d1.getTime(), d2.getTime());
                    } else {
                        // 倒序
                        return compareTo(d2.getTime(), d1.getTime());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * 日期比较
     *
     * @param time1
     * @param time2
     * @return
     */
    private static int compareTo(long time1, long time2) {
        if (time1 > time2) {
            return 1;
        } else if (time1 < time2) {
            return -1;
        } else {
            return 0;
        }
    }
}
