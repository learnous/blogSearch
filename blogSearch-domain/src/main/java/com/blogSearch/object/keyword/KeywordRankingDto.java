package com.blogSearch.object.keyword;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KeywordRankingDto {
    private List<KeywordDto> keywordRanking;
}
