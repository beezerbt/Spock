package hello.model.ui.contract;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UICountry {

	private final String name;
	
	private final String code;
}
