package demo;

import demo.pojo.User;
import demo.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.StringWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendmailApplicationTests {
    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("fubinsama@qq.com", "201601011420@sdust.edu.cn", "1070952257@qq.com",
                "测试邮件主题", "测试邮件内容");
    }

    @Test
    public void sendAttachFileMail() {
        mailService.sendAttachFileMail("fubinsama@qq.com", "201601011420@sdust.edu.cn", "1070952257@qq.com",
                "测试邮件主题", "测试邮件内容", new File("/home/wfb/毕设/test/test2/HelloWorld.java"));
    }

    @Test
    public void sendMailWithImg() {
        String content = "<div>\n" +
                "\thello，这是一封带图片资源的邮件：\n" +
                "\t这是图片1：<div><img src='cid:p01'/></div>\n" +
                "\t这是图片2：<div><img src='cid:p02'/></div>\n" +
                "</div>";
        mailService.sendMailWithImg("fubinsama@qq.com", "201601011420@sdust.edu.cn", "1070952257@qq.com",
                "测试邮件主题（图片）", content, new String[]{
                        "/run/media/wfb/DATA/图片/辉夜姬.jpg",
                        "/run/media/wfb/DATA/图片/喝茶.jpg"
                }, new String[]{"p01", "p02"});
    }

    @Test
    public void sendHtmlMail() {
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            ClassLoader loader = MailApplication.class.getClassLoader();
            configuration.setClassLoaderForTemplateLoading(loader, "ftl");
            Template template = configuration.getTemplate("mailtemplate.ftl");
            StringWriter mail = new StringWriter();
            User user = new User();
            user.setUsername("汪福斌");
            user.setGender("男");
            template.process(user, mail);
            mailService.sendHtmlMail("fubinsama@qq.com", "201601011420@sdust.edu.cn",
                    "测试邮件主题", mail.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendHtmlMailThymeleaf() {
        Context ctx = new Context();
        ctx.setVariable("username", "汪福斌");
        ctx.setVariable("gender", "男");
        String mail = templateEngine.process("mailtemplate.html", ctx);
        mailService.sendHtmlMail("fubinsama@qq.com", "201601011420@sdust.edu.cn",
                "测试邮件主题", mail);
    }
}
