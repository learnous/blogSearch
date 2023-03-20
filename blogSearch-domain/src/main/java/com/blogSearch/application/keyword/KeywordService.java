package com.blogSearch.application.keyword;

import com.blogSearch.object.keyword.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public List<Keyword> listTop10Keyword() {
        return keywordRepository.findTop10ByOrderByKeywordCountDesc();
    }

    public Optional<Keyword> findKeywordByKeyword(String keyword) {
        return keywordRepository.findFirstByKeyword(keyword);
    }

    public Keyword putKeyword(Keyword keyword) {
        return keywordRepository.save(keyword);
    }

}
