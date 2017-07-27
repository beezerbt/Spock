package hello.model.ui.contract.payments;

import hello.model.ui.contract.UICapping;
import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
public class UILinearPaymentCycle {

	
	private final LocalDate startDate;

	
	private final LocalDate endDate;

	@Size(max = 255)
	private final String remark;

	
	private final UICapping capping;

	
	private final UIPaymentTargetRebate minimum;

	
	private final UIPaymentTargetRebate maximum;

	
	private final BigDecimal curveFactor;


}
