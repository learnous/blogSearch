package com.blogSearch.external.wrapper;

import com.blogSearch.external.ApiType;
import com.blogSearch.external.BlogSearchWebClient;
import com.blogSearch.object.blogSearch.BlogSearchRequestDto;
import com.blogSearch.object.blogSearch.BlogSearchResult;
import com.blogSearch.object.blogSearch.external.NCompanyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

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

    @Override
    public BlogSearchResult getBlogSearchResult(BlogSearchRequestDto requestDto) {

        return getBlogSearchResultMono(requestDto).block();
    }

    @Override
    public Mono<BlogSearchResult> getBlogSearchResultMono(BlogSearchRequestDto requestDto) {

        return webClient.get(getBlogSearchFullUri(requestDto), DEFAULT_HEADER)
                .bodyToMono(NCompanyResponseDto.class)
                .map(NCompanyResponseDto::to)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.error(new Throwable("API 응답이 올바르지 않습니다"));
                });
    }

    private String getBlogSearchFullUri(BlogSearchRequestDto requestDto) {
        String sort = "recency".equalsIgnoreCase(requestDto.getSort()) ? "date" : "sim";
        Integer page = Optional.ofNullable(requestDto.getPage()).orElse(1);
        Integer size = Optional.ofNullable(requestDto.getSize()).orElse(10);

        return UriComponentsBuilder
                .fromUriString(BASE_URI)
                .path(ENDPOINT)
                .queryParam("query", requestDto.getKeyword())
                .queryParam("sort", sort)
                .queryParam("start", Math.min((page - 1) * size + 1, 1000))
                .queryParam("display", size)
                .build()
                .toString();
    }
}
