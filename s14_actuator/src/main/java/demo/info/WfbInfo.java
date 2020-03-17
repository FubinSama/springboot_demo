package demo.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WfbInfo implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> java = new HashMap<>();
        java.put("source", "1.8.0_242");
        java.put("target", "1.8.0_242");
        Map<String, Object> app = new HashMap<>();
        app.put("encoding", "UTF-8");
        app.put("java", java);
        Map<String, String> author = new HashMap<>();
        author.put("name", "wfb");
        author.put("email", "fubinsama@qq.com");
        Map<String, Object> info = new HashMap<>();
        info.put("app", app);
        info.put("author", author);
        builder.withDetails(info);
    }
}
