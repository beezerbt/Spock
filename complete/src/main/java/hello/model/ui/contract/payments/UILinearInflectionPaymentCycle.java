package hello.model.ui.contract.payments;

import hello.model.ui.contract.UICapping;
import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
public class UILinearInflectionPaymentCycle {

	
	private final LocalDate startDate;

	
	private final LocalDate endDate;

	@Size(max = 255)
	private final String remark;

	
	private final UICapping capping;

	
	private final UIPaymentTargetRebate minimum;

	
	private final UIPaymentTargetRebate maximum;

	
	private final Set<UIPaymentTargetRebate> inflections;
}
