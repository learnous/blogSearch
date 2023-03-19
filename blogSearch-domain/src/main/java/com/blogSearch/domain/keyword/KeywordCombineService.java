package com.blogSearch.domain.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KeywordCombineService {
    private final KeywordService keywordService;

    public List<Keyword> listKeyword() {
        return keywordService.listKeyword();
    }

    public void addKeywordMock() {
        keywordService.addKeywordMock();
    }
}
