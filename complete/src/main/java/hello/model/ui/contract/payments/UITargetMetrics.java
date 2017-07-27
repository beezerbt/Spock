package hello.model.ui.contract.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class UITargetMetrics {

	
	private final String targetDataMeasure; //targetTriggerMeasureX
	
	private final String targetDataInputType; //triggerInputTypeX
	
	private final String targetType; //targetTypeXId

}
