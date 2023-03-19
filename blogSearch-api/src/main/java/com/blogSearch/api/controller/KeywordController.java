package com.blogSearch.api.controller;

import com.blogSearch.application.keyword.KeywordCombineService;
import com.blogSearch.object.blogSearch.BlogSearchRequestDto;
import com.blogSearch.object.blogSearch.BlogSearchResult;
import com.blogSearch.object.keyword.KeywordDto;
import com.blogSearch.object.keyword.KeywordRankingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class KeywordController {

    private final KeywordCombineService keywordCombineService;

    @GetMapping("/mock/keyword")
    public List<KeywordDto> getKeywordList(){
        return keywordCombineService.listKeywordDto();
    }

    @GetMapping("/blog/keyword/ranking")
    public KeywordRankingDto getKeywordRanking() {
        return keywordCombineService.getKeywordRankingDto();
    }

    @PostMapping("/blog/search")
    public BlogSearchResult getBlogSearchResult(@Valid @RequestBody BlogSearchRequestDto requestDto) {
        return null;
    }

}
