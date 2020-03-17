package demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// 注解在model类上，用来配置model的描述信息
@ApiModel(value = "用户实体类", description = "用户信息描述类")
@Data
public class User {
    @ApiModelProperty(value = "用户名") //对model属性做说明
    private String username;
    @ApiModelProperty(value = "用户地址")
    private String address;
}
