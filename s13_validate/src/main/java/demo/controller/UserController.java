package demo.controller;

import demo.pojo.User;
import demo.pojo.User2;
import demo.validate.ValidationGroup2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @PostMapping("/user")
    //@Validated注解表示需要对user进行验证，有BindingResult参数，所以验证结果放入了该对象中；否则直接返回出错信息
    public List<String> addUser(@Validated User user, BindingResult result) {
        List<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error: allErrors) {
                errors.add(error.getDefaultMessage());
            }
        }
        return errors;
    }

    @PostMapping("/user2") //使用group2的验证规则
    public List<String> addUser2(@Validated(ValidationGroup2.class) User2 user2, BindingResult result) {
        List<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error: allErrors) {
                errors.add(error.getDefaultMessage());
            }
        }
        return errors;
    }
}
