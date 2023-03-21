package com.blogSearch.object.blogSearch.external;

import com.blogSearch.object.blogSearch.BlogSearchResultDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NCompanyResponseItem {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;

    public static BlogSearchResultDocument to(NCompanyResponseItem item) {
        try {
            return BlogSearchResultDocument
                    .builder()
                    .title(item.title)
                    .url(item.link)
                    .contents(item.description)
                    .blogname(item.bloggername)
                    .datetime(new SimpleDateFormat("yyyyMMdd").parse(item.postdate))
                    .build();
        } catch (ParseException e) {
            return BlogSearchResultDocument
                    .builder()
                    .title(item.title)
                    .url(item.link)
                    .contents(item.description)
                    .blogname(item.bloggername)
                    .datetime(null)
                    .build();
        }
    }
}
