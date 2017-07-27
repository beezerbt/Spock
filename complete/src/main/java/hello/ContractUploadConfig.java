package hello;

import com.carslon.contractupload.model.BusinessDivisions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class ContractUploadConfig {
	private static Logger LOG = LoggerFactory.getLogger(ContractUploadConfig.class);
	//TODO::KS::Once the debugging is sorted this should be removed...or set to log at debug.
    @Bean
	public Filter loggingFilter(){

		AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {

			@Override
			protected void beforeRequest(HttpServletRequest request, String message) {
				LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>beforeRequest: " +message);
			}

			@Override
			protected void afterRequest(HttpServletRequest request, String message) {
				LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>afterRequest: " +message);
			}
		};
		f.setMaxPayloadLength(9000);
		f.setIncludeClientInfo(true);
		f.setIncludePayload(true);
		f.setIncludeQueryString(true);

		f.setBeforeMessagePrefix("BEFORE REQUEST  [");
		f.setAfterMessagePrefix("AFTER REQUEST    [");
		f.setAfterMessageSuffix("]\n");
		return f;
	}

	@Bean
	@Autowired
	public BusinessDivisions createBusinessDivisions(){
		return new BusinessDivisions();
	}
}
