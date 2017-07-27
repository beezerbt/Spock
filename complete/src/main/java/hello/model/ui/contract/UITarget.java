package hello.model.ui.contract;

import hello.model.ui.contract.payments.UIPayment;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@JsonIdentityInfo(scope=UITarget.class, generator=ObjectIdGenerators.PropertyGenerator.class, property="name")
public class UITarget {

	
	private final String name;

	
	private final String agreementTypeCode;

	
	private final Boolean countsForTotalTraffic;

	
	private final String revenueTypeCode;

	@JsonIdentityReference(alwaysAsId = true)
	private final HashSet<UISafetyNet> safetyNets;

	
	private final UICapping capping;

	private final String paymentTerms;
	private final String remarks;

	private final Set<String> additionalDateRuleTypeCodes;

	private final Set<UIPayment> payments;



}
