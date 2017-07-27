package hello.model.ui.search;

import lombok.Builder;
import lombok.Value;
import org.joda.time.LocalDate;

@Value
@Builder
public class UIContractSearchResult {
	private final Long id;
	private final String systemName;
	private final String mainSupplierName;
	private final String mainSupplierCode;
	private final LocalDate validityStart;
	private final LocalDate validityEnd;
	private final String status;
	private final boolean active;
}

