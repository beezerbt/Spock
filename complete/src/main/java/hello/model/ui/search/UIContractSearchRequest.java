package hello.model.ui.search;

import lombok.Builder;
import lombok.Value;
import org.joda.time.LocalDate;

import java.util.Set;

@Value
@Builder
public class UIContractSearchRequest {
	private final String mainSupplierCode;
	private final String owner;
	private final String pointOfSaleScopeCode;
	private final Boolean active;
	private final LocalDate validity;
	private final Set<String> typeCodes;
	private final Set<String> statusCodes;
	private final Set<String> pointOfSaleCountryCodes;
}
