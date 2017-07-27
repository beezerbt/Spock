package hello.model.ui.contract.payments;

import hello.model.ui.contract.UICapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UILayerPaymentCycle {

	@NotNull
	private final LocalDate startDate;

	@NotNull
	private final LocalDate endDate;

	@Size(max = 255)
	private final String remark;

	@NotNull
	private final UICapping capping;

	@NotNull
	private final List<UIPaymentLayer> layers;

}
