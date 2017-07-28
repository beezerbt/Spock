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
        //TODO::find out what this actually does...
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
        assert responseVersusExpectedResolver(resp, expectedHttpStatusInput,
                expectedFieldErrorCount, expectedFieldValueInput, expectedMessageValueInput)

        where:
        payloadInput | expectedHttpStatusInput | expectedFieldErrorCount | expectedFieldValueInput | expectedMessageValueInput | headerInfoInput
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"","pointsOfSale":[{"name":"","code":"AB"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.BAD_REQUEST | 1 | "contractScope.pointOfSaleScopeCode." | "Invalid PointOfSaleScope" | "no PointOfSaleScope provided FAILS!!"
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"MultiCountry","pointsOfSale":[{"name":"","code":"AB"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.BAD_REQUEST | 1 | "contractScope.pointsOfSale" | "Must have either 0 or >1 point of sale(s) for MultiCountry pointOfScaleScope" | "MultiCountry PointOfSaleScope and 1 PointOfSale FAILS!!"
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"International","pointsOfSale":[{"name":"","code":"AB"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.BAD_REQUEST | 1 | "contractScope.pointsOfSale" | "Must have either 0 or >1 point of sale(s) for International pointOfScaleScope" | "International PointOfSaleScope and 1 PointOfSale FAILS!!."
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"International","pointsOfSale":[{"name":"","code":"AB"}, {"name":"","code":"TZ"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.OK | 0 | "" | "" | "International PointOfSaleScope >1 PointOfSale SUCCEEDS!!."
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"International","pointsOfSale":[],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.OK | 0 | "" | "" | "International PointOfSaleScope 0 PointOfSale SUCCEEDS!!."
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"MultiCountry","pointsOfSale":[{"name":"","code":"AB"}, {"name":"","code":"SC"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.OK | 0 | "" | "" | "MultiCountry PointOfSaleScope >1 PointOfSale SUCCEEDS!!."
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"MultiCountry","pointsOfSale":[],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.OK | 0 | "" | "" | "MultiCountry PointOfSaleScope 0 PointOfSale SUCCEEDS!!."
    }

    /**
     * This utility method simply makes sure that the conditions for the fixture instance
     * are met in order to indicate if it has succeeded or failed.
     * It should itself have a unit test in order to make sure it is sound.
     * @param resp is the RESTClient return object after the REST call
     * @param expectedHttpStatus the expected status of this instance of the REST call
     * @param expectedFieldErrorCount the expected number of validation errors
     * @param expectedFieldValueInput the expected field name for an error
     * @param expectedMessageValueInput the expected field error message for an error
     * @return boolean with true == REST call succeeded and false for a failure
     */
    private boolean responseVersusExpectedResolver(Object resp,
                                           HttpStatus expectedHttpStatus,
                                           int expectedFieldErrorCount,
                                           String expectedFieldValueInput,
                                           String expectedMessageValueInput) {


        if (!expectedHttpStatus.value().equals(resp.status)) {
            return false
        }
        int actualFieldErrorCount = 0
        try {
            actualFieldErrorCount = resp.data.entrySet()[0].getProperties().get("key").equals("fieldErrors")
        } catch (Exception e) {
            //carry on
        }
        if (!expectedFieldErrorCount == actualFieldErrorCount) {
            return false
        }
        if (expectedFieldErrorCount == 0) {
            return true
        }
        List<Map> fieldErrorList = null
        try {
            resp.data.entrySet()[0].getProperties().get("key").equals("fieldErrors")
            fieldErrorList = resp.data.entrySet()[0].getProperties().get("value")
        } catch (Exception e1) {
            return false
        }
        if (fieldErrorList == null) {
            return false
        }
        for (item in fieldErrorList) {
            if (!item.get("field").equals(expectedFieldValueInput)) {
                return false
            }
            if (!item.get("message").equals(expectedMessageValueInput)) {
                return false;
            }
        }
        return true;
    }
}
