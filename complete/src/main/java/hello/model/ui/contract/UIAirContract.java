package hello.model.ui.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UIAirContract {

	private UILastUpdateInformation lastUpdate;

	@Valid
	private final UIAirContractScope contractScope;

	private final Set<UITarget> targets;

	private final Set<UISafetyNet> safetyNets;
}
