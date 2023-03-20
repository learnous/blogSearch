package com.blogSearch.external.wrapper;

import com.blogSearch.external.ApiType;
import com.blogSearch.external.BlogSearchWebClient;
import com.blogSearch.object.blogSearch.BlogSearchRequestDto;
import com.blogSearch.object.blogSearch.BlogSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NaverBlogSearchWrapper implements BlogSearchWrapper {
    private final BlogSearchWebClient webClient;
    private final String CLIENT_ID = "KrYwtuwKz5kt4kyF5sKt";
    private final String CLIENT_SECRET = "OsUeqwSfLh";
    private final String BASE_URI = "https://openapi.naver.com";
    private final String ENDPOINT = "/v1/search/blog.json";
    private final ApiType API_TYPE = ApiType.NAVER;
    private final LinkedMultiValueMap<String, String> DEFAULT_HEADER = new LinkedMultiValueMap<>();

    @PostConstruct
    private void init() {
        DEFAULT_HEADER.add("X-Naver-Client-Id", CLIENT_ID);
        DEFAULT_HEADER.add("X-Naver-Client-Secret", CLIENT_SECRET);
    }

    @Override
    public boolean isSupport(ApiType apiType) {
        return API_TYPE.equals(apiType);
    }

    public BlogSearchResult getBlogSearchResult(BlogSearchRequestDto requestDto) {

        return webClient.get(getBlogSearchFullUri(requestDto), DEFAULT_HEADER)
                .bodyToMono(BlogSearchResult.class)
                .block();
    }

    private String getBlogSearchFullUri(BlogSearchRequestDto requestDto) {
        return UriComponentsBuilder
                .fromUriString(BASE_URI)
                .path(ENDPOINT)
                .queryParam("query", requestDto.getKeyword())
                .queryParamIfPresent("sort", Optional.ofNullable(requestDto.getSort()))
                .queryParamIfPresent("start", Optional.ofNullable(requestDto.getPage()))
                .queryParamIfPresent("display", Optional.ofNullable(requestDto.getSize()))
                .build()
                .toString();
    }
}
