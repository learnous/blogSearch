package com.blogSearch.object.blogSearch.external;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NCompanyRequestDto {
    private String query; // UTF-8
    private String sort; // sim(default), date
    private Integer display; // 한번에 표시할 검색 결과 수(default 10 ~ 100)
    private Integer start; // 검색 시작 위치(default 1 ~ 1000)
}
