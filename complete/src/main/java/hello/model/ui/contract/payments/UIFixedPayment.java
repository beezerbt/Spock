package hello.model.ui.contract.payments;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
@Builder
public class UIFixedPayment implements UIPayment{


	private final String name;


	private final String currencyCode;

	private final Set<UIFixedPaymentCycle> cycles;
}
