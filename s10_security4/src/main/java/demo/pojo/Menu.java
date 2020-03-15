package demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Menu {
    private Integer id;
    private String pattern;
    private List<Role> roles;
}
