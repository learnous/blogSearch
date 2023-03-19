package com.blogSearch.configuration;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogSearchErrorResponse {
    private String code;
    private String message;
    private Object data;

    public static BlogSearchErrorResponse from(String message, Object data) {
        return BlogSearchErrorResponse.builder()
                .message(message)
                .data(data)
                .build();
    }
}
