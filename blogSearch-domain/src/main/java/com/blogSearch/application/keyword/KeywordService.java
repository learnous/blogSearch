package com.blogSearch.application.keyword;

import com.blogSearch.object.keyword.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public List<Keyword> listTop10Keyword() {
        return keywordRepository.findTop10ByOrderByKeywordCountDesc();
    }

    public List<Keyword> listKeyword() {
        return keywordRepository.findAll();
    }

}
