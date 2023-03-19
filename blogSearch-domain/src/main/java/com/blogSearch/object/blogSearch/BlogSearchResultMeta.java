package com.blogSearch.object.blogSearch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogSearchResultMeta {
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;

}
