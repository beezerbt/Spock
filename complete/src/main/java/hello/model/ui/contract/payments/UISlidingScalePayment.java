package hello.model.ui.contract.payments;

import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Value
@Builder
public class UISlidingScalePayment implements UIPayment {
	
	private final String name;

	
	private final String currencyCode;

	
	@Valid
	private final UIPaymentMetrics paymentMetrics;

	
	@Valid
	private final UITargetMetrics targetMetrics;

	
	@Valid
	private final Set<UILayerPaymentCycle> cycles;
}
