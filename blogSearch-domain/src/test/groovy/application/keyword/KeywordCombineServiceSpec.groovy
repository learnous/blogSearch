package application.keyword

import com.blogSearch.application.keyword.KeywordCombineService
import com.blogSearch.application.keyword.KeywordService
import com.blogSearch.external.ApiType
import com.blogSearch.external.wrapper.BlogSearchWrapper
import com.blogSearch.external.wrapper.KakaoBlogSearchWrapper
import com.blogSearch.external.wrapper.NaverBlogSearchWrapper
import com.blogSearch.object.blogSearch.BlogSearchRequestDto
import com.blogSearch.object.keyword.Keyword
import spock.lang.Shared
import spock.lang.Specification

class KeywordCombineServiceSpec extends Specification {
    @Shared
    KeywordService keywordService
    @Shared
    KakaoBlogSearchWrapper kakaoBlogSearchWrapper
    @Shared
    NaverBlogSearchWrapper naverBlogSearchWrapper
    @Shared
    List<BlogSearchWrapper> blogSearchWrapperList
    @Shared
    KeywordCombineService combineService

    def setup() {
        keywordService = Mock()
        kakaoBlogSearchWrapper = Mock()
        naverBlogSearchWrapper = Mock()
        blogSearchWrapperList = [kakaoBlogSearchWrapper, naverBlogSearchWrapper]
        combineService = new KeywordCombineService(keywordService, blogSearchWrapperList)
    }

    def "keywordService Entity Entity to Dto"() {
        given:
        def keywordEntities = [
                new Keyword(keyword: "key1", keywordCount: 1),
                new Keyword(keyword: "key2", keywordCount: 2)
        ]
        when:
        def result = combineService.getKeywordRankingDto()
        then:
        1 * keywordService.listTop10Keyword() >> keywordEntities
        result.keywordRanking[0].keyword == "key1"
        result.keywordRanking[0].keywordCount == 1
        result.keywordRanking[1].keyword == "key2"
        result.keywordRanking[1].keywordCount == 2
    }

    def "keywordCount added"() {
        given:
        def requestDto = new BlogSearchRequestDto(keyword: "mockKeyword")
        when:
        combineService.searchBlogResults(requestDto)
        then:
        1 * keywordService.findKeywordByKeyword(requestDto.keyword) >> Optional<Keyword>.ofNullable(keywordResult)
        1 * keywordService.putKeyword(keywordResult)
        1 * kakaoBlogSearchWrapper.isSupport(_ as ApiType) >> true
        0 * naverBlogSearchWrapper.isSupport(_ as ApiType)
        1 * kakaoBlogSearchWrapper.getBlogSearchResult(_ as BlogSearchRequestDto)
        keywordResult.keywordCount == expect

        where:
        keywordResult                   |   expect
        new Keyword(keywordCount: 0)    |   1
        new Keyword(keywordCount: 1)    |   2
    }
}
