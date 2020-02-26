package com.common.api.enums;

public enum SourceTypeEnum {
    KR_NEWS("36KR","https://36kr.com/pp/api/newsflash?per_page=50");

    private String name;
    private String url;

    SourceTypeEnum(String name,String url){
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
