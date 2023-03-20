package com.blogSearch.object.blogSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogSearchResult {
    private BlogSearchResultMeta meta;
    private List<BlogSearchResultDocument> documents;
}
