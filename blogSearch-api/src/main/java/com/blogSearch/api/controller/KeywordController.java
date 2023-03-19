package com.blogSearch.api.controller;

import com.blogSearch.domain.keyword.Keyword;
import com.blogSearch.domain.keyword.KeywordCombineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordCombineService keywordCombineService;

    @GetMapping("/mock/keyword")
    public List<Keyword> getKeywordList(){
        return keywordCombineService.listKeyword();
    }

    @GetMapping("/mock/keyword/insert")
    public void insertKeyword() {
        keywordCombineService.addKeywordMock();
    }

}
