package validation

import com.carslon.contractupload.model.BusinessDivisions
import com.carslon.contractupload.validation.BusinessDivisionValidator
import groovyx.net.http.ContentType
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Subject

@Stepwise

class BusinessDivisionValidatorTest extends Specification {

    @Subject
    BusinessDivisionValidator ut
    BusinessDivisions businessDivisions
    final String VALID_BUSINESS_DIVISION_CODE = "Commercial"

    def setup() {
        ut.b
        def businessDivisions = Mock(BusinessDivisions)
        businessDivisions = Mock()
        businessDivisions.exists(VALID_BUSINESS_DIVISION_CODE) >> true
        ut = new BusinessDivisionValidator()
        ut.initialize(businessDivisions)
    }

    def "Test Setup was successful"() {
        given:
        def i
        then
        businessDivisions != null
        ut !=null
    }

    @Ignore
    def "Validating a valid buisiness division payload succeeds"() {
        given:

        when:
        def resp = client.post(path: '/greeting',
                requestContentType: ContentType.JSON,
                headers: ['Content-Type': "application/json"],
                body: ['id'   : VALID_ID,
                       'items': ["Book", "CD", "DVD"]])
        then:
        assert resp.status == NOT_FOUND
    }
}
