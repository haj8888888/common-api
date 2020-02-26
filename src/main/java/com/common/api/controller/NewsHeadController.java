package com.common.api.controller;

import com.common.api.entity.NewsHead;
import com.common.api.service.INewsHeadService;
import com.common.api.util.MapParam;
import com.common.api.util.RetrunT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news-head")
public class NewsHeadController {

    @Autowired
    private INewsHeadService newsHeadService;

    @GetMapping("/flash")
    @ResponseBody
    public RetrunT flash() {
        newsHeadService.flash();
        return RetrunT.success();
    }

    @GetMapping("/list")
    @ResponseBody
    public RetrunT list(@RequestParam Map<String,Object> params) {
        List<NewsHead> list = newsHeadService.list(params);
        return RetrunT.success(list);
    }
}
