package com.cwt.web

import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Ignore
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

    static OK = 200
    static NOT_FOUND = 404

    static VALID_ID = 100  // order ID to be added to database
    static INVALID_ID = 101 // not in database!

    @Shared
    def client = new RESTClient("http://localhost:8080")
    def slurper = new JsonSlurper()

    def setupSpec() {
        /* this simplifies exception handling so that Spock doesn't
            swallow the exception! */
        client.handler.failure = client.handler.success
    }

    @Ignore
    def "Call Contract creation rest endpoint with an invalid payload content"() {
        when:
        def resp = client.post(path: '/greeting',
                requestContentType: ContentType.JSON,
                headers: ['Content-Type': "application/json"],
                body: ['id'   : VALID_ID,
                       'items': ["Book", "CD", "DVD"]])
        then:
        assert resp.status == NOT_FOUND
    }

    @Ignore
    def "Test map creation of the entire input payload"() {
        when:
        def object = slurper.parseText('{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-07","typeCode":"54safds","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking date","pointOfSaleScopeCode":"international","pointsOfSale":[{"name":"algeria","code":"aa"}],"active":true,"validatingSuppliers":[{"name":"BS","code":"bestSupplier"}],"marketingSuppliers":[{"name":"BS","code":"bestSupplier"}],"operatingSuppliers":[{"name":"BS","code":"bestSupplier"}],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","countsForTotalTraffic":true,"revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"capping":{"type":"Amount","amount":20000},"paymentTerms":"terms","remarks":"remarksText","additionalDateRuleTypeCodes":["Arrival Date"],"payments":[{"type":"linear","name":"my Linear Payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"startDate":"2017-09-01","endDate":"2017-09-01","remark":"no remarks","capping":{"type":"Amount","amount":3300},"minimum":{"target":2,"rebate":30},"maximum":{"target":10,"rebate":40},"curveFactor":1}]},{"type":"linearInflection","name":"my inflection payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"startDate":"2017-09-01","endDate":"2017-09-01","remark":"no remarks","capping":{"type":"amount","amount":3300},"minimum":{"target":2,"rebate":30},"maximum":{"target":10,"rebate":40},"inflections":[{"target":5,"rebate":35}]}]},{"type":"qualitative","name":"my variable payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":null,"cycles":[{"startDate":"2017-03-01","endDate":"2017-03-02","remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"rebate":10000}]},{"type":"fixed","name":"myFixedPayment","currencyCode":"EUR","cycles":[{"startDate":"2017-01-07","endDate":"2017-01-08","amount":1000,"remark":"hello"}]},{"type":"variable","name":"my variable payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"cycles":[{"startDate":"2017-03-01","endDate":"2017-03-02","remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"rebate":10000}]},{"type":"layer","name":"myLayerPayment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"startDate":"2017-02-07","endDate":"2017-02-08","remark":"hello","capping":{"type":"Amount","amount":123},"layers":[{"fromNumber":2,"toNumber":3,"rebate":50},{"fromNumber":3,"toNumber":20,"rebate":500}]}]},{"type":"slidingScale","name":"myLayerPayment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"startDate":"2017-02-07","endDate":"2017-02-08","remark":"hello","capping":{"type":"Amount","amount":123},"layers":[{"fromNumber":2,"toNumber":3,"rebate":50},{"fromNumber":3,"toNumber":20,"rebate":500}]}]},{"type":"matrix","name":"my matrix payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetricsXaxis":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"targetMetricsYaxis":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"startDate":"2017-04-01","endDate":"2017-04-02","remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"matrixEntries":[{"triggerX":2,"triggerY":2,"rebate":44},{"triggerX":1,"triggerY":2,"rebate":77},{"triggerX":2,"triggerY":1,"rebate":33},{"triggerX":1,"triggerY":1,"rebate":55}]}]}]}],"safetyNets":[{"name":"safetyfirst","agreementType":"agree1","cappingType":"flatcap","countsForTotalTraffic":false,"targetRevenueType":"safetyRev","paymentTerms":null,"cappingAmount":null,"remarks":null,"trafficScopeId":null,"additionalDateRuleDateTypes":null,"payments":null}]}')
        then:
        assert object instanceof Map
        println(">>>>>pointsOfSale" + object.contractScope.active)
    }


    @Ignore
    def "Posting a VALID contract type succeeds"() {
        when:

        Map payload = slurper.parseText('{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"international","pointsOfSale":[{"name":"algeria","code":"aa"}],"active":true,"validatingSuppliers":[{"name":"BS","code":"bestSupplier"}],"marketingSuppliers":[{"name":"BS","code":"bestSupplier"}],"operatingSuppliers":[{"name":"BS","code":"bestSupplier"}],"businessDivisionCodes":["Commercial","Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","countsForTotalTraffic":true,"revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"capping":{"type":"Amount","amount":20000},"paymentTerms":"terms","remarks":"remarksText","additionalDateRuleTypeCodes":["Arrival Date"],"payments":[{"type":"linear","name":"my Linear Payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"no remarks","capping":{"type":"Amount","amount":3300},"minimum":{"target":2,"rebate":30},"maximum":{"target":10,"rebate":40},"curveFactor":1}]},{"type":"linearInflection","name":"my inflection payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"no remarks","capping":{"type":"amount","amount":3300},"minimum":{"target":2,"rebate":30},"maximum":{"target":10,"rebate":40},"inflections":[{"target":5,"rebate":35}]}]},{"type":"qualitative","name":"my variable payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":null,"cycles":[{"remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"rebate":10000}]},{"type":"fixed","name":"myFixedPayment","currencyCode":"EUR","cycles":[{"amount":1000,"remark":"hello"}]},{"type":"variable","name":"my variable payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"cycles":[{"remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"rebate":10000}]},{"type":"layer","name":"myLayerPayment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"hello","capping":{"type":"Amount","amount":123},"layers":[{"fromNumber":2,"toNumber":3,"rebate":50},{"fromNumber":3,"toNumber":20,"rebate":500}]}]},{"type":"slidingScale","name":"myLayerPayment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"hello","capping":{"type":"Amount","amount":123},"layers":[{"fromNumber":2,"toNumber":3,"rebate":50},{"fromNumber":3,"toNumber":20,"rebate":500}]}]},{"type":"matrix","name":"my matrix payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetricsXaxis":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"targetMetricsYaxis":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"matrixEntries":[{"triggerX":2,"triggerY":2,"rebate":44},{"triggerX":1,"triggerY":2,"rebate":77},{"triggerX":2,"triggerY":1,"rebate":33},{"triggerX":1,"triggerY":1,"rebate":55}]}]}]}],"safetyNets":[{"name":"safetyfirst","agreementType":"agree1","cappingType":"flatcap","countsForTotalTraffic":false,"targetRevenueType":"safetyRev","paymentTerms":null,"cappingAmount":null,"remarks":null,"trafficScopeId":null,"additionalDateRuleDateTypes":null,"payments":null}]}')
        Map actualRequest = [:]
        actualRequest.put('path', '/complete/greeting');
        actualRequest.put('requestContentType', ContentType.JSON);
        actualRequest.put('headers', ['Content-Type': "application/json"]);
        actualRequest.put('body', payload)
        def resp = client.post(actualRequest)

        then:
        assert resp.status == OK
    }
    @Ignore
    def "Call Contract creation rest endpoint with a INVALID contract type name...which is a list of ContractTypes"() {
        when:
        def resp = client.post(
                path: '/aircontracts',
                requestContentType: ContentType.JSON,
                headers: ['Content-Type': "application/json"],
                body: [
                        "contractScope": [

                        ]
                ]
        )
        then:
        assert resp.status == NOT_FOUND
    }
    @Ignore
    def "Posting an INVALID contract type fails"() {
        when:

        Map payload = slurper.parseText('{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","typeCode":"InvalidCode","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"international","pointsOfSale":[{"name":"algeria","code":"aa"}],"active":true,"validatingSuppliers":[{"name":"BS","code":"bestSupplier"}],"marketingSuppliers":[{"name":"BS","code":"bestSupplier"}],"operatingSuppliers":[{"name":"BS","code":"bestSupplier"}],"businessDivisionCodes":["Commercial","Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","countsForTotalTraffic":true,"revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"capping":{"type":"Amount","amount":20000},"paymentTerms":"terms","remarks":"remarksText","additionalDateRuleTypeCodes":["Arrival Date"],"payments":[{"type":"linear","name":"my Linear Payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"no remarks","capping":{"type":"Amount","amount":3300},"minimum":{"target":2,"rebate":30},"maximum":{"target":10,"rebate":40},"curveFactor":1}]},{"type":"linearInflection","name":"my inflection payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"no remarks","capping":{"type":"amount","amount":3300},"minimum":{"target":2,"rebate":30},"maximum":{"target":10,"rebate":40},"inflections":[{"target":5,"rebate":35}]}]},{"type":"qualitative","name":"my variable payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":null,"cycles":[{"remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"rebate":10000}]},{"type":"fixed","name":"myFixedPayment","currencyCode":"EUR","cycles":[{"amount":1000,"remark":"hello"}]},{"type":"variable","name":"my variable payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"cycles":[{"remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"rebate":10000}]},{"type":"layer","name":"myLayerPayment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"hello","capping":{"type":"Amount","amount":123},"layers":[{"fromNumber":2,"toNumber":3,"rebate":50},{"fromNumber":3,"toNumber":20,"rebate":500}]}]},{"type":"slidingScale","name":"myLayerPayment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetrics":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"hello","capping":{"type":"Amount","amount":123},"layers":[{"fromNumber":2,"toNumber":3,"rebate":50},{"fromNumber":3,"toNumber":20,"rebate":500}]}]},{"type":"matrix","name":"my matrix payment","currencyCode":"EUR","paymentMetrics":{"paymentDataMeasure":"Value","paymentDataInputType":"Coupon","rebateType":"Amount per unit"},"targetMetricsXaxis":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"targetMetricsYaxis":{"targetDataMeasure":"% vs Baseline","targetDataInputType":"Coupon","targetType":"Index"},"cycles":[{"remark":"nothing to see here","capping":{"type":"Amount","amount":3300},"matrixEntries":[{"triggerX":2,"triggerY":2,"rebate":44},{"triggerX":1,"triggerY":2,"rebate":77},{"triggerX":2,"triggerY":1,"rebate":33},{"triggerX":1,"triggerY":1,"rebate":55}]}]}]}],"safetyNets":[{"name":"safetyfirst","agreementType":"agree1","cappingType":"flatcap","countsForTotalTraffic":false,"targetRevenueType":"safetyRev","paymentTerms":null,"cappingAmount":null,"remarks":null,"trafficScopeId":null,"additionalDateRuleDateTypes":null,"payments":null}]}')
        Map actualRequest = [:]
        actualRequest.put('path', '/complete/greeting');
        actualRequest.put('requestContentType', ContentType.JSON);
        actualRequest.put('headers', ['Content-Type': "application/json"]);
        actualRequest.put('body', payload)
        def resp = client.post(actualRequest)

        then:
        assert resp.status != OK
    }

    @IgnoreRest
    @Unroll
    def "Posting a VALID contract to JBOSS instance succeeds"(String payloadInput,
                                                              HttpStatus expectedHttpStatusInput,
                                                              int expectedFieldErrorCount,
                                                              String expectedFieldValueInput,
                                                              String expectedMessageValueInput) {
        when:

        //Map payloadForJBossInstance = slurper.parseText('{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"MultiCountry","pointsOfSale":[{"name":"","code":"AB"}, {"name":"","code":"TZ"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}')
        Map payloadForJBossInstance = slurper.parseText(payloadInput)
        Map actualRequest = [:]
        actualRequest.put('path', '/gsm/rest/aircontracts');
        actualRequest.put('requestContentType', ContentType.JSON);
        actualRequest.put('headers', ['Content-Type': "application/json"]);
        actualRequest.put('headers', ['Accept': "application/json"]);
        actualRequest.put('body', payloadForJBossInstance)
        def resp = client.post(actualRequest)

        then:
        // VALID CONTRACT PAYLOAD'{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"MultiCountry","pointsOfSale":[{"name":"","code":"AB"}, {"name":"","code":"TZ"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | _
        //assert resp.status == HttpStatus.BAD_REQUEST.value()

        //Replacing the following with a method call
        assert RESTCallAssertionResolver(resp, expectedHttpStatusInput,
                expectedFieldErrorCount, expectedFieldValueInput, expectedMessageValueInput)

        /*assert resp.status == expectedHttpStatusInput.value()
        def fieldErrorCount = resp.data.entrySet().size()
        assert fieldErrorCount == expectedFieldErrorCount
        def expectedFieldError = resp.data.entrySet()[0]
        assert expectedFieldError != null
        def fieldErrorValue = resp.data.entrySet()[0].getValue()

        def errorValue = fieldErrorValue[0]
        def expectedFieldErrorFieldValue = errorValue.entrySet()[0]
        def expectedFieldErrorMessageValue = errorValue.entrySet()[1]

        String fieldKey = expectedFieldErrorFieldValue.getKey()
        assert fieldKey.equalsIgnoreCase("field")
        String fieldValue = expectedFieldErrorFieldValue.getValue()
        //assert fieldValue.equalsIgnoreCase("contractScope.pointsOfSale")
        assert fieldValue.equalsIgnoreCase(expectedFieldValueInput)

        String messageKey = expectedFieldErrorMessageValue.getKey()
        assert messageKey.equalsIgnoreCase("message")
        String messageValue = expectedFieldErrorMessageValue.getValue()
        // assert messageValue.equalsIgnoreCase("Must have either 0 or >1 point of sale(s) for MultiCountry pointOfScaleScope")
        assert messageValue.equalsIgnoreCase(expectedMessageValueInput)
*/
        where:
        payloadInput | expectedHttpStatusInput | expectedFieldErrorCount | expectedFieldValueInput | expectedMessageValueInput
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"MultiCountry","pointsOfSale":[{"name":"","code":"AB"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.BAD_REQUEST | 1 | "contractScope.pointsOfSale" | "Must have either 0 or >1 point of sale(s) for MultiCountry pointOfScaleScope"
        '{"contractScope":{"description":"MyContract","systemName":"systematic","currencyCode":"EUR","owner":"Alberto Palma","validityStartDate":"2016-10-06","validityEndDate":"2017-10-05","typeCode":"SLA","statusCode":"Draft","mainSupplier":{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"},"dateTypeCode":"Booking","pointOfSaleScopeCode":"International","pointsOfSale":[{"name":"","code":"AB"}],"active":true,"validatingSuppliers":[{"name":"CHINA SOUTHERN AIRLINES","code":"CZ"}],"marketingSuppliers":[],"operatingSuppliers":[],"businessDivisionCodes":["Commercial"],"contractLevel":"TICKET","remark":"hello"},"targets":[{"name":"target1","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","safetyNets":["safetyfirst"],"remarks":"remarksText","periodicity":"Monthly","payments":[{"name":"my Linear Payment","remarks":"linear"},{"name":"my Linear Payment","remarks":"not so linear"}]}],"safetyNets":[{"name":"safetyfirst","agreementTypeCode":"Performance Based","revenueTypeCode":"Service Fee","remarks":"remarksText"}]}' | HttpStatus.BAD_REQUEST | 1 | "contractScope.pointsOfSale" | "Must have either 0 or >1 point of sale(s) for International pointOfScaleScope"
    }

    boolean RESTCallAssertionResolver(Object resp,
                                      HttpStatus expectedHttpStatus,
                                      int expectedFieldErrorCount,
                                      String expectedFieldValueInput,
                                      String expectedMessageValueInput) {

        try {
            assert "Assert HttpStatus was:" + expectedHttpStatus.value() + "", resp.status == expectedHttpStatus.value()
            def fieldErrorCount = resp.data.entrySet().size()
            assert fieldErrorCount == expectedFieldErrorCount
            def expectedFieldError = resp.data.entrySet()[0]
            assert expectedFieldError != null
            def fieldErrorValue = resp.data.entrySet()[0].getValue()

            def errorValue = fieldErrorValue[0]
            def expectedFieldErrorFieldValue = errorValue.entrySet()[0]
            def expectedFieldErrorMessageValue = errorValue.entrySet()[1]

            String fieldKey = expectedFieldErrorFieldValue.getKey()
            assert fieldKey.equalsIgnoreCase("field")
            String fieldValue = expectedFieldErrorFieldValue.getValue()
            assert fieldValue.equalsIgnoreCase(expectedFieldValueInput)

            String messageKey = expectedFieldErrorMessageValue.getKey()
            assert messageKey.equalsIgnoreCase("message")
            String messageValue = expectedFieldErrorMessageValue.getValue()
            assert messageValue.equalsIgnoreCase(expectedMessageValueInput)
        } catch (AssertionError e) {
            println "Something bad happened: " + e.getMessage()
            return false
        }
        return true;
    }
}
