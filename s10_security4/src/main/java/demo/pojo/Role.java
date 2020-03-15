package demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {
    private Integer id;
    private String name;
    private String nameZh;
}
