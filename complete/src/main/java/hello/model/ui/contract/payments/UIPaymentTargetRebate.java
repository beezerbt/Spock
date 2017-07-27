package hello.model.ui.contract.payments;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UIPaymentTargetRebate {

	private final BigDecimal target;
	private final BigDecimal rebate;
}
