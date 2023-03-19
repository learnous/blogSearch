package com.blogSearch.application.keyword;

import com.blogSearch.object.keyword.KeywordDto;
import com.blogSearch.object.keyword.KeywordRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KeywordCombineService {
    private final KeywordService keywordService;

    public List<KeywordDto> listKeywordDto() {
        return keywordService.listKeyword().stream().map(KeywordDto::from).collect(Collectors.toList());
    }

    public KeywordRankingDto getKeywordRankingDto() {
        return KeywordRankingDto.builder()
                .keywordRanking(keywordService.listTop10Keyword()
                        .stream()
                        .map(KeywordDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
