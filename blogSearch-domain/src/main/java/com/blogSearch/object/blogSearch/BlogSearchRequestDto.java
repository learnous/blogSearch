package com.blogSearch.object.blogSearch;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BlogSearchRequestDto {
    @NotBlank(message = "검색 키워드는 필수 값입니다.")
    private String keyword;
    private String sort; // accuracy, recency
    @Max(value = 50)
    private Integer page; // default 1
    @Max(value = 50)
    private Integer size; // default 10
}
