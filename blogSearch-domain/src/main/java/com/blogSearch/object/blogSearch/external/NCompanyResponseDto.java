package com.blogSearch.object.blogSearch.external;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NCompanyResponseDto {
    private Integer total;
    private Integer start;
    private Integer display;
    private List<NCompanyResponseItem> items;
}
