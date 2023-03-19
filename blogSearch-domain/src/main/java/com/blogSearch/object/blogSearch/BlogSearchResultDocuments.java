package com.blogSearch.object.blogSearch;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BlogSearchResultDocuments {
    private String title;
    private String contents;
    private String url;
    private String blogName;
    private String thumbnail;
    private Date datetime;

}
