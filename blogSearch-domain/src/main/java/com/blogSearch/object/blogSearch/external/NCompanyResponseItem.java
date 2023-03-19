package com.blogSearch.object.blogSearch.external;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NCompanyResponseItem {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;
}
