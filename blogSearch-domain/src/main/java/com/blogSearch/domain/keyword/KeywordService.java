package com.blogSearch.domain.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public Optional<Keyword> getKeywordByKeyword(String keyword) {
        return keywordRepository.findFirstByKeyword(keyword);
    }

    public List<Keyword> listKeyword() {
        return keywordRepository.findAll();
    }

    public void addKeywordMock() {
        keywordRepository.save(Keyword.builder()
                .keyword("mock")
                .keywordCount(0L)
                .build());
    }

}
