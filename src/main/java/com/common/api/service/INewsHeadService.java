package com.common.api.service;

import com.common.api.entity.NewsHead;
import com.common.api.util.MapParam;

import java.util.List;
import java.util.Map;

public interface INewsHeadService {
    void flash();

    List<NewsHead> list(Map<String,Object> params);
}
