package com.blogSearch.external.wrapper;

import com.blogSearch.external.ApiType;
import com.blogSearch.object.blogSearch.BlogSearchRequestDto;
import com.blogSearch.object.blogSearch.BlogSearchResult;
import reactor.core.publisher.Mono;

public interface BlogSearchWrapper {
    boolean isSupport(ApiType apiType);
    BlogSearchResult getBlogSearchResult(BlogSearchRequestDto requestDto);
    Mono<BlogSearchResult> getBlogSearchResultMono(BlogSearchRequestDto requestDto);
}
