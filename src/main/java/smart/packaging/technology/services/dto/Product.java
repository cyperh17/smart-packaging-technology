package smart.packaging.technology.services.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {

    //{"name":"milk","code":"2364758363546"}

    @NotNull(message = "'product.name' must not be null")
    @NotBlank(message = "'product.name' must not be blank")
    private String name;


    @NotNull(message = "'product.code' must not be null")
    @NotBlank(message = "'product.code' must not be blank")
    @Length(min = 13,max = 13, message = "'product.code' length must be equal to 13")
    @Digits(integer = 13, fraction = 0, message = "'product.code' must contain only digits")
    private String code;
}
