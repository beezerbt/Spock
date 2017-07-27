package hello.model.ui.contract.payments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = UIFixedPayment.class, name = "fixed"),
		@JsonSubTypes.Type(value = UIVariablePayment.class, name = "variable"),
		@JsonSubTypes.Type(value = UILayerPayment.class, name = "layer"),
		@JsonSubTypes.Type(value = UISlidingScalePayment.class, name = "slidingScale"),
		@JsonSubTypes.Type(value = UIMatrixPayment.class, name = "matrix"),
		@JsonSubTypes.Type(value = UILinearInflectionPayment.class, name = "linearInflection"),
		@JsonSubTypes.Type(value = UIQualitativePayment.class, name = "qualitative"),
		@JsonSubTypes.Type(value = UILinearPayment.class, name = "linear")})
public interface UIPayment {
}
