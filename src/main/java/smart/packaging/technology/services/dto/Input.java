package smart.packaging.technology.services.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Input {

    //{
    //"seller":"123534251",
    //"customer":"648563524",
    //"products":[{"name":"milk","code":"2364758363546"},{"name":"water","code":"3656352437590"}]
    //}

    @NotNull(message = "'seller' code must not be null")
    @NotBlank(message = "'seller' code must not be blank")
    @Length(min = 9,max = 9, message = "'seller' code length must be equal to 9")
    @Digits(integer = 9, fraction = 0, message = "'seller' code must contain only digits")
    private String seller;


    @NotNull(message = "'customer' code must not be null")
    @NotBlank(message = "'customer' code must not be blank")
    @Length(min = 9,max = 9, message = "'customer' code length must be equal to 9")
    @Digits(integer = 9, fraction = 0, message = "'customer' code must contain only digits")
    private String customer;

    @NotNull(message = "'products' array must not be null")
    private @Valid Product[] products;
}
