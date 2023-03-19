package com.blogSearch.application.keyword;

import com.blogSearch.object.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findFirstByKeyword(String keyword);
    List<Keyword> findTop10ByOrderByKeywordCountDesc();
}
