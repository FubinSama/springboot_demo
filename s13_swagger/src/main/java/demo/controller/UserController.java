package demo.controller;

import demo.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "用户数据接口") //注解在类上，用来描述整个Controller信息
public class UserController {
    //注解在开发方法上，用来描述一个方法的基本信息，value是对方法作用的一个简短描述，notes则用来备注该方法的详细作用
    @ApiOperation(value = "查询用户", notes = "根据id查询用户")
    // 用来描述方法的参数，paramType是指方法参数的类型，可选值有path(参数获取方法为@PathVariable)、query(参数获取方法为@RequestParam)、
    // header(参数获取方法为@RequestHeader)、body以及form；name表示参数名称，和参数变量对应；value是参数的描述信息；
    // required表示该字段是否必填；defaultValue表示该字段的默认值。
    // 注意：这里的required和defaultValue等字段只是文档上的约束描述，并不能真正约束接口。
    // 如果有多个参数，可以放到ApiImplicitParams
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true)
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Integer id) {
        return "/user/" + id;
    }

    // 对响应结果的描述，code表示响应码，message表示相应的描述信息。若有多个ApiResponse，则一起放到ApiResponses中
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功！"),
            @ApiResponse(code = 500, message = "删除失败！")})
    @ApiOperation(value = "删除用户", notes = "通过id删除用户")
    @DeleteMapping("/user/{id}")
    public Integer deleteUserById(@PathVariable Integer id) {
        return id;
    }

    @ApiOperation(value = "添加用户", notes = "添加一个用户，传入用户名和地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, defaultValue = "wfb"),
            @ApiImplicitParam(paramType = "query", name = "address", value = "用户地址", required = true, defaultValue = "shandong")
    })
    @PostMapping("/user")
    public String addUser(@RequestParam String username, @RequestParam String address) {
        return username + ":" + address;
    }

    @ApiOperation(value = "修改用户", notes = "修改用户，传入用户信息")
    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {
        return user.toString();
    }

    @GetMapping("/ignore")
    @ApiIgnore //表示不对某个接口生成文档
    public void ingoreMethod() {

    }
}
