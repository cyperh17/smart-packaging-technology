package smart.packaging.technology.services.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FieldErrorInfo {
    private String field;
    private String error;
    private Object suppliedValue;
}