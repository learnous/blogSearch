package com.blogSearch.external.wrapper;

import com.blogSearch.external.ApiType;
import com.blogSearch.external.BlogSearchWebClient;
import com.blogSearch.object.blogSearch.BlogSearchRequestDto;
import com.blogSearch.object.blogSearch.BlogSearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoBlogSearchWrapper implements BlogSearchWrapper {
    private final BlogSearchWebClient webClient;
    private final NaverBlogSearchWrapper fallBackWrapper;

    private final String API_KEY = "040b4d2fe3c8e3e3d2b0dc4cd500f145";
    private final String BASE_URI = "https://dapi.kakao.com";
    private final String ENDPOINT = "/v2/search/blog";
    private final ApiType API_TYPE = ApiType.KAKAO;
    private final LinkedMultiValueMap<String, String> DEFAULT_HEADER = new LinkedMultiValueMap<>();

    @PostConstruct
    private void init() {
        DEFAULT_HEADER.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + API_KEY);
    }

    @Override
    public boolean isSupport(ApiType apiType) {
        return API_TYPE.equals(apiType);
    }

    @Override
    public BlogSearchResult getBlogSearchResult(BlogSearchRequestDto requestDto) {
        return getBlogSearchResultMono(requestDto).block();
    }
    @Override
    public Mono<BlogSearchResult> getBlogSearchResultMono(BlogSearchRequestDto requestDto) {
        return webClient.get(getBlogSearchFullUri(requestDto), DEFAULT_HEADER)
                .bodyToMono(BlogSearchResult.class)
                .onErrorResume((e) -> {
                    log.warn("KAKAO API에서 에러가 발생해서 Naver API를 호출합니다: " + requestDto);
                    e.printStackTrace();
                    return fallBackWrapper.getBlogSearchResultMono(requestDto);
                });
    }

    private String getBlogSearchFullUri(BlogSearchRequestDto requestDto) {
        return UriComponentsBuilder
                .fromUriString(BASE_URI)
                .path(ENDPOINT)
                .queryParam("query", requestDto.getKeyword())
                .queryParamIfPresent("sort", Optional.ofNullable(requestDto.getSort()))
                .queryParamIfPresent("page", Optional.ofNullable(requestDto.getPage()))
                .queryParamIfPresent("size", Optional.ofNullable(requestDto.getSize()))
                .build()
                .toString();
    }
}
