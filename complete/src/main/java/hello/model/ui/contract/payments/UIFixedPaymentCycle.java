package hello.model.ui.contract.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class UIFixedPaymentCycle {

	
	private final LocalDate startDate;

	
	private final LocalDate endDate;

	
	private final BigDecimal amount;

	@Size(max = 255)
	private final String remark;

	/*
	private final Long paymentDataMeasureId;
	private final Long paymentInputTypeId;
	private final Long rebateTypeId;
	private final Long targetCapId;
	private final BigDecimal cappingAmount;
	private final BigDecimal variableRebateAmount;

	private final Long targetTypeXId;
	private final Long targetTypeYId;

	private final Long targetTriggerMeasureX;
	private final Long targetTriggerMeasureY;
	private final Long triggerInputTypeX;
	private final Long triggerInputTypeY;

	private final String targetCustomTriggerX;
	private final String targetCustomTriggerY;
	private final String customPaymentData;
	private final Long numberOfProperties;

	private final Set<DateRuleTO> dateRules;
	private final Set<PaymentLinearScaleTO> paymentLinearScale;
	private final Set<PaymentLayerSlidingScaleTO> paymentLayerSlidingScale;
	private final Set<PaymentMatrixTO> paymentMatrix;
	private final Set<PaymentLinearInflectionTO> paymentLinearInflection;
*/
}
