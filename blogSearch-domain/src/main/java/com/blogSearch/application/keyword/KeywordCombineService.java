package com.blogSearch.application.keyword;

import com.blogSearch.exception.CustomException;
import com.blogSearch.external.ApiType;
import com.blogSearch.external.wrapper.BlogSearchWrapper;
import com.blogSearch.object.blogSearch.BlogSearchRequestDto;
import com.blogSearch.object.blogSearch.BlogSearchResult;
import com.blogSearch.object.keyword.Keyword;
import com.blogSearch.object.keyword.KeywordDto;
import com.blogSearch.object.keyword.KeywordRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KeywordCombineService {
    private final KeywordService keywordService;
    private final List<BlogSearchWrapper> blogSearchWrapper;

    public KeywordRankingDto getKeywordRankingDto() {
        return KeywordRankingDto.builder()
                .keywordRanking(keywordService.listTop10Keyword()
                        .stream()
                        .map(KeywordDto::from)
                        .collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public BlogSearchResult searchBlogResults(BlogSearchRequestDto requestDto) {
        Keyword keyword = keywordService.findKeywordByKeyword(requestDto.getKeyword())
                .orElse(Keyword.builder()
                        .keyword(requestDto.getKeyword())
                        .keywordCount(0L)
                        .build());
        keyword.setKeywordCount(keyword.getKeywordCount() + 1);
        keywordService.putKeyword(keyword);

        BlogSearchWrapper searchWrapper = blogSearchWrapper
                .stream()
                .filter(it -> it.isSupport(ApiType.KAKAO))
                .findFirst()
                .orElseThrow(new CustomException("구현되지 않은 API입니다"));

        return searchWrapper.getBlogSearchResult(requestDto);
    }
}
