package hello;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	//TODO::this drives the parameterization fo the SwaggerConfig documentation
/*	@Value("${api.documentation.enabled}")
	private String apiDocumentationEnabled;*/

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SPRING_WEB)
				.directModelSubstitute(LocalDate.class,String.class)
				.enable(Boolean.parseBoolean("true"))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(regex("/complete.*"))
				.build();

	}
}
