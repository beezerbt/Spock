package hello.model.ui.contract;

import hello.model.ui.contract.payments.UIPayment;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@JsonIdentityInfo(scope=UISafetyNet.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class UISafetyNet {

	
	private final String name;

	
	private final String agreementType;

	
	private final String cappingType;

	
	private final Boolean countsForTotalTraffic;

	
	private final String targetRevenueType;

	private final String paymentTerms;
	private final BigDecimal cappingAmount;
	private final String remarks;

	private final Long trafficScopeId;

	private final Set<String> additionalDateRuleDateTypes;

	private final Set<UIPayment> payments;

}
