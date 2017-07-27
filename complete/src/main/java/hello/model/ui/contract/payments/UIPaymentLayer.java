package hello.model.ui.contract.payments;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class UIPaymentLayer {

	
	private final BigDecimal fromNumber;
	
	private final BigDecimal toNumber;
	
	private final BigDecimal rebate;

}
