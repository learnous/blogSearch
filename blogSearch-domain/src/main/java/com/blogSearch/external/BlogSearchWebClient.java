package com.blogSearch.external;

import com.blogSearch.exception.CustomException;
import io.netty.channel.ChannelOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
public class BlogSearchWebClient {
    private static final Integer TIMEOUT_MILLIS = 3000;
    private final HttpClient httpClient = HttpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT_MILLIS)
            .responseTimeout(Duration.of(TIMEOUT_MILLIS, ChronoUnit.MILLIS));

    private final WebClient webClient = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();

    public WebClient.ResponseSpec get(String uri) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return this.get(uri, headers);
    }

    public WebClient.ResponseSpec post(String uri, Object body) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return this.post(uri, body, headers);
    }

    public WebClient.ResponseSpec get(String uri, MultiValueMap<String, String> headers) {
        log.warn(headers.toString());
        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(String.class)
                        .flatMap(r -> Mono.error((Throwable) new CustomException(String.format("API요청에 실패했습니다 - {%s} / {%s}", uri, r)))))
                .onStatus(HttpStatus::is5xxServerError, response -> response.bodyToMono(String.class)
                        .flatMap(r -> Mono.error((Throwable) new CustomException(String.format("타 서버 오류가 발생했습니다 - {%s} / {%s}", uri, r)))));
    }

    public WebClient.ResponseSpec post(String uri, Object body, MultiValueMap<String, String> headers) {
        return webClient.post()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(String.class)
                        .flatMap(r -> Mono.error((Throwable) new CustomException(String.format("API요청에 실패했습니다 - {%s} / {%s}", uri, r)))))
                .onStatus(HttpStatus::is5xxServerError, response -> response.bodyToMono(String.class)
                        .flatMap(r -> Mono.error((Throwable) new CustomException(String.format("타 서버 오류가 발생했습니다 - {%s} / {%s}", uri, r)))));
    }


}
