package com.blogSearch.object.blogSearch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogSearchResult {
    private BlogSearchResultMeta meta;
    private BlogSearchResultDocuments documents;
}
