package hello.model.ui.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
public class UILastUpdateInformation {
	private final String user;
	private final DateTime timestamp;
}
