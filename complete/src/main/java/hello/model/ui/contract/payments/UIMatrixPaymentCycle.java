package hello.model.ui.contract.payments;

import hello.model.ui.contract.UICapping;
import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
public class UIMatrixPaymentCycle {
	
	private final LocalDate startDate;

	
	private final LocalDate endDate;

	@Size(max = 255)
	private final String remark;

	
	private final UICapping capping;

	
	@Valid
	private final Set<UIPaymentMatrixEntry> matrixEntries;

}
