package demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Message implements Serializable {
    private String content;
    private Date date;
}
