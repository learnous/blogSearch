package com.blogSearch.object.keyword;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeywordDto {
    private String keyword;
    private Long keywordCount;

    public static KeywordDto from(Keyword entity) {
        return KeywordDto.builder()
                .keyword(entity.getKeyword())
                .keywordCount(entity.getKeywordCount())
                .build();
    }
}
