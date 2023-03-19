package com.blogSearch.object.keyword;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "keyword", indexes = {
        @Index(name = "keyword_idx_created_at", columnList = "created_at"),
        @Index(name = "keyword_idx_updated_at", columnList = "updated_at")
})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keyword_seq")
    @SequenceGenerator(name = "keyword_seq", sequenceName = "keyword_seq", allocationSize = 1)
    @Column(name = "keyword_id", nullable = false)
    private Long keywordId;

    @Column(name = "keyword", unique = true)
    private String keyword;

    @Column(name = "keyword_count", columnDefinition = "bigint default 0")
    private Long keywordCount;

    @Column(name = "created_at", columnDefinition = "timestamp default now()")
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp default now()")
    private Date updatedAt;
}
