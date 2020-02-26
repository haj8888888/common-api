package com.common.api.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.api.entity.NewsHead;
import com.common.api.entity.NewsHeadExample;
import com.common.api.enums.SourceTypeEnum;
import com.common.api.mapper.NewsHeadMapper;
import com.common.api.service.HttpService;
import com.common.api.service.INewsHeadService;
import com.common.api.util.DateUtils;
import com.common.api.util.MapUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NewsHeadServiceImpl implements INewsHeadService {

    @Resource
    private NewsHeadMapper newsHeadMapper;

    @Autowired
    private HttpService httpService;

    private static final String KR = "https://36kr.com/pp/api/newsflash?per_page=50";

    /**
     * "id": 203824,
    * "project_id": 1,
    * "column_id": 72,
    *  "post_id": null,
    * "is_top": 0,
    *  "pin": 0,
    * "title": "雷诺公司对戈恩提起民事诉讼，保留追责权利",
    * "catch_title": "",
    *  "description": "据《欧洲时报》报道，当地时间24日晚，雷诺公司宣布，在就该公司前总裁戈恩滥用公司资产的司法调查框架内，雷诺对戈恩提出附带民事起诉。据报道，戈恩被指在担任雷诺总裁期间，在凡尔赛宫举办了两场私人豪华晚宴，代价是以雷诺公司的名义与凡尔赛宫签订了赞助合同。雷诺公司在公开声明中表示，“本公司作为民事当事人行使权利……并保留在调查定案后，讨回损失和损害赔偿的权利”。（中国新闻网）",
    *  "cover": "",
    *  "news_url_type": "news_url",
    *   "news_url": "https://finance.sina.com.cn/chanjing/gsnews/2020-02-26/doc-iimxxstf4511726.shtml",
     *   "published_at": "2020-02-26 15:04:13",
     */
    @Override
    public void flash() {
        JSONObject jsonObject = httpService.get(SourceTypeEnum.KR_NEWS.getUrl());
        if(jsonObject.getInteger("code") != 0){
            log.error("请求失败："+jsonObject.toJSONString());
            return;
        }
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("items");
        for (Object item : jsonArray) {

            try{
                JSONObject newsItem = (JSONObject) item;
                NewsHead newsHead = new NewsHead();
                newsHead.setSourceId(newsItem.getLong("id") +"");
                newsHead.setSourceType(SourceTypeEnum.KR_NEWS.getName());
                newsHead.setTitle(newsItem.getString("title"));
                newsHead.setDescription(newsItem.getString("description"));
                newsHead.setNewsUrl(newsItem.getString("news_url"));
                newsHead.setPublishedAt(DateUtils.parse(newsItem.getString("published_at")));
                save(newsHead);
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }

        }

    }

    @Override
    public List<NewsHead> list(Map<String, Object> params) {
        NewsHeadExample example = new NewsHeadExample();
        NewsHeadExample.Criteria criteria = example.createCriteria();
        String sourceType = MapUtils.getStringObjectDefaultNull(params,"sourceType");
        if(sourceType != null){
            criteria.andSourceTypeEqualTo(sourceType);
        }
        PageHelper.orderBy("source_id desc");
        PageHelper.startPage(MapUtils.getIntegerObjectDefault(params,"pageNo",1),
                MapUtils.getIntegerObjectDefault(params,"pageSize",100));
        List<NewsHead> list = newsHeadMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    private Long save(NewsHead newsHead) {
        NewsHeadExample example = new NewsHeadExample();
        example.createCriteria()
                .andSourceIdEqualTo(newsHead.getSourceId())
                .andSourceTypeEqualTo(newsHead.getSourceType());
        List<NewsHead> list = newsHeadMapper.selectByExampleWithBLOBs(example);
        if(list != null && list.size() > 0){
            newsHead.setId(list.get(0).getId());
            newsHeadMapper.updateByPrimaryKeySelective(newsHead);
        }else {
            newsHeadMapper.insertSelective(newsHead);
        }
        return newsHead.getId();
    }
}
