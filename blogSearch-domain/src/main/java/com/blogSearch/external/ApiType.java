package com.blogSearch.external;

public enum ApiType {
    KAKAO("카카오"),
    NAVER("네이버");

    private final String name;

    public String getName() {
        return name;
    }


    ApiType(String name) {
        this.name = name;
    }
}
