package hello.model.ui.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class UICapping {


	private final String type;

	private final BigDecimal amount;
}
