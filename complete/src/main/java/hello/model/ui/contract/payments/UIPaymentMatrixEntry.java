package hello.model.ui.contract.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class UIPaymentMatrixEntry {

	
	private final BigDecimal triggerX;
	
	private final BigDecimal triggerY;
	
	private final BigDecimal rebate;
}
