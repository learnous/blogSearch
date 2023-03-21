package com.blogSearch.object.blogSearch.external;

import com.blogSearch.object.blogSearch.BlogSearchResult;
import com.blogSearch.object.blogSearch.BlogSearchResultDocument;
import com.blogSearch.object.blogSearch.BlogSearchResultMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NCompanyResponseDto {
    private Integer total;
    private Integer start;
    private Integer display;
    private List<NCompanyResponseItem> items;

    public static BlogSearchResult to(NCompanyResponseDto dto) {
        int pageableCount = Math.min(1000 + dto.display, dto.total) - dto.start - dto.display;
        BlogSearchResultMeta meta = BlogSearchResultMeta.builder()
                .total_count(dto.total)
                .pageable_count(pageableCount)
                .is_end(pageableCount - dto.display <= 0)
                .build();

        List<BlogSearchResultDocument> documents = dto.items.stream()
                .map(NCompanyResponseItem::to)
                .collect(Collectors.toList());

        return BlogSearchResult.builder()
                .meta(meta)
                .documents(documents)
                .build();
    }
}
