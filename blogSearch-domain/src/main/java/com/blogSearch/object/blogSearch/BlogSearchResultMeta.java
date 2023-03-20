package com.blogSearch.object.blogSearch;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogSearchResultMeta {
    private Integer total_count;
    private Integer pageable_count;
    private Boolean is_end;

}
