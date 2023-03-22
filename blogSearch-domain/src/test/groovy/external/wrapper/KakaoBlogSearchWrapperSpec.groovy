package external.wrapper

import com.blogSearch.external.BlogSearchWebClient
import com.blogSearch.external.wrapper.KakaoBlogSearchWrapper
import com.blogSearch.external.wrapper.NaverBlogSearchWrapper
import spock.lang.Shared
import spock.lang.Specification

class KakaoBlogSearchWrapperSpec extends Specification {
    @Shared
    BlogSearchWebClient webClient
    @Shared
    NaverBlogSearchWrapper fallBackWrapper
    @Shared
    KakaoBlogSearchWrapper wrapper

    def setup() {
        webClient = Mock()
        fallBackWrapper = Mock()
        wrapper = new KakaoBlogSearchWrapper(webClient, fallBackWrapper)
    }

//    def "kakao error -> naver api call"() {
//        given:
//        def requestDto = new BlogSearchRequestDto(keyword: "mockKeyward", sort: "mockSort", page: 1, size: 1)
//        when:
//        wrapper.getBlogSearchResult(requestDto)
//        then:
//        1 * webClient.get(_ as String,*_)
//        1 * fallBackWrapper.getBlogSearchResult(*_)
//    }
}
