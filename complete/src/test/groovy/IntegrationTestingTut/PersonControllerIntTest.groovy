package IntegrationTestingTut

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@Import([IntegrationTestMockingConfig])
class PersonControllerIntTest extends Specification {

    @Autowired MockMvc mvc

    /**
     * This is our mock we created in our test config.
     * We inject it in so we can control it in our specs.
     */
  /*  @Autowired ExternalRankingService externalRankingServiceMock

    def "GetRank"() {
        when: 'Calling getRank for a known seed data entity'
        MvcResult mvcResult = mvc.perform(get("/persons/1/rank"))
                .andExpect(status().is2xxSuccessful())
                .andReturn()

        then: 'we define the mock for JUST the external service'
        externalRankingServiceMock.getRank(_) &gt;&gt; {
            new Rank(level: 1, classification: 'Captain')
        }
        noExceptionThrown()

        when: 'inspecting the contents'
        def content = mvcResult.response.contentAsString

        then: 'result contains mix of mocked service data and actual wired component data'
        content == 'Capt James Kirk ~ Captain:Level 1'
    }*/
}
