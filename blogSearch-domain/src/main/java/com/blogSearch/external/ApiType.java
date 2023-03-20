package com.blogSearch.external;

public enum ApiType {
    KAKAO("카카오", "NAVER"),
    NAVER("네이버", null);

    private final String name;
    private final String nextType;
    public String getName() {
        return name;
    }
    public String getNextType() {
        return nextType;
    }

    ApiType(String name, String nextType) {
        this.name = name;
        this.nextType = nextType;
    }
}
