package hello.model.ui.contract.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class UIPaymentMetrics {
	
	private final String paymentDataMeasure;
	
	private final String paymentDataInputType;
	
	private final String rebateType;
}
