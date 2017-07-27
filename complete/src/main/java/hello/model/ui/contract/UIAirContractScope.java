package hello.model.ui.contract;

import com.carslon.contractupload.validation.annotation.ValidBusinessDivision;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDate;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class UIAirContractScope {
	
	private final String description;

	private final String systemName;

	private final String currencyCode;

	private final String owner;

	
	private final LocalDate validityStartDate;

	
	private final LocalDate validityEndDate;

	

	private final String typeCode;

	

	private final String statusCode;

	

	private final UISupplier mainSupplier;

	

	private final String dateTypeCode;

	
	private final String pointOfSaleScopeCode;

	private final Set<UICountry> pointsOfSale;

	
	private final Boolean active;

	private final Set<UISupplier> validatingSuppliers;
	private final Set<UISupplier> marketingSuppliers;
	private final Set<UISupplier> operatingSuppliers;

	@ValidBusinessDivision
	private final Set<String> businessDivisionCodes;

	
	private final String contractLevel;

	private final String remark;


}
