package demo.pojo;

import lombok.Data;

@Data
public class Chat {
    private String to;
    private String from;
    private String content;
}
