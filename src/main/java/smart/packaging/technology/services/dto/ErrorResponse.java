package smart.packaging.technology.services.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ErrorResponse {
    private List<FieldErrorInfo> errors;
    private int httpCode;
}
