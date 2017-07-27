package hello.model.ui.search;

import hello.model.ui.contract.UIAirContract;
import hello.model.ui.contract.UIAirContractScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Component
public class UIContractSearchResultMapper {

	public List<UIContractSearchResult> from(Map<Long,UIAirContract> contracts){
		List<UIContractSearchResult> results = newArrayList();
		for (Map.Entry<Long,UIAirContract> entry : contracts.entrySet()) {
			UIAirContractScope scope = entry.getValue().getContractScope();
			results.add(UIContractSearchResult.builder()
					.id(entry.getKey())
					.active(scope.getActive())
					.status(scope.getStatusCode())
					.mainSupplierName(scope.getMainSupplier().getName())
					.mainSupplierCode(scope.getMainSupplier().getCode())
					.validityStart(scope.getValidityStartDate())
					.validityEnd(scope.getValidityEndDate())
					.systemName(scope.getSystemName())
			.build());
		}
		return results;
	}
}
