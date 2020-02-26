package com.common.api.util;

import java.util.HashMap;

public class MapParam extends HashMap<String,Object>{

    /**  */
    private static final long serialVersionUID = 1L;

    public static MapParam create(String key, Object value) {
        MapParam param = new MapParam();
        param.put(key, value);
        return param;
    }

    public MapParam add(String key, Object value) {
        put(key, value);
        return this;
    }
}
