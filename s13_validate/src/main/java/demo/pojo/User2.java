package demo.pojo;

import demo.validate.ValidationGroup1;
import demo.validate.ValidationGroup2;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class User2 {
    private Integer id;
    @Size(min = 5, max = 10, message = "{user.name.size}", groups = ValidationGroup1.class)
    private String name;
    @NotNull(message = "{user.address.notnull}", groups = ValidationGroup2.class)
    private String address;
    @DecimalMin(value = "1", message = "{user.age.size}")
    @DecimalMax(value = "200", message = "{user.age.size}")
    private Integer age;
    @Email(message = "{user.email.pattern}")
    @NotNull(message = "{user.email.notnull}" ,groups = {ValidationGroup1.class, ValidationGroup2.class})
    private String email;
}
