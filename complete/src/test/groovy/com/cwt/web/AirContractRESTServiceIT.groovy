package com.cwt.web

import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Specification
import org.springframework.http.HttpStatus

// No need for the @Grab annotation
import spock.lang.Stepwise
import spock.lang.Unroll

// Define the specification class as follows:

@Stepwise
// allows for sequential testing

class AirContractRESTServiceIT extends Specification {

    @Shared
    def client = new RESTClient("http://localhost:8080")
    def slurper = new JsonSlurper()

    def setupSpec() {
        client.handler.failure = client.handler.success
    }

    @IgnoreRest
    @Unroll
    def "REST Contract creation with #headerInfoInput "(String payloadInput,
                                                              HttpStatus expectedHttpStatusInput,
                                                              int expectedFieldErrorCount,
                                                              String expectedFieldValueInput,
                                                              String expectedMessageValueInput, String headerInfoInput) {
        when:
        Map payloadForJBossInstance = slurper.parseText(payloadInput)
        Map actualRequest = [:]
        actualRequest.put('path', '/gsm/rest/aircontracts');
        actualRequest.put('requestContentType', ContentType.JSON);
        actualRequest.put('headers', ['Content-Type': "application/json"]);
        actualRequest.put('headers', ['Accept': "application/json"]);
        actualRequest.put('body', payloadForJBossInstance)
        def resp = client.post(actualRequest)

        then:
        //Replacing the following with a method call
        assert responseVersusExpectedResolver(resp, expectedHttpStatusInput,
                expectedFieldErrorCount, expectedFieldValueInput, expectedMessageValueInput)

        where:
        payloadInput | expectedHttpStatusInput | expectedFieldErrorCount | expectedFieldValueInput | expectedMessageValueInput | headerInfoInput
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"MultiCountry","pointsOfSale":[{"name":"","code":"AB"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.BAD_REQUEST | 1 | "contractScope.pointsOfSale" | "Must have either 0 or >1 point of sale(s) for MultiCountry pointOfScaleScope" | "MultiCountry PointOfSaleScope and 1 PointOfSale fails."
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"International","pointsOfSale":[{"name":"","code":"AB"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.BAD_REQUEST | 1 | "contractScope.pointsOfSale" | "Must have either 0 or >1 point of sale(s) for International pointOfScaleScope" | "International PointOfSaleScope and 1 PointOfSale fails."
    }

    boolean responseVersusExpectedResolver(Object resp,
                                           HttpStatus expectedHttpStatus,
                                           int expectedFieldErrorCount,
                                           String expectedFieldValueInput,
                                           String expectedMessageValueInput) {

        try {
            assert "Assert HttpStatus was:" + expectedHttpStatus.value() + "", resp.status == expectedHttpStatus.value()
            int fieldErrorCount = 0
            try {
                fieldErrorCount = resp.data.entrySet().size()
            } catch (Exception e) {
                //carry on
            }
            assert fieldErrorCount == expectedFieldErrorCount
            if (expectedFieldErrorCount == 0) return true

            //Now we look at the expected error fields (overkill or what?)
            assert resp.data.entrySet()[0].getProperties().get("key").equals("fieldErrors")
            List<Map> fieldErrorList = resp.data.entrySet()[0].getProperties().get("value")
            assert fieldErrorList != null

            for (item in fieldErrorList) {
                println "FieldErrorEntry was: ${item}"
                item.get("field").equals(expectedFieldValueInput)
                item.get("message").equals(expectedMessageValueInput)
            }
        }
        catch (AssertionError e) {
            return false
        }
        return true;
    }
}
