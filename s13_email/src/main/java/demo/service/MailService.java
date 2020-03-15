package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 发送简单邮件
     * @param from
     * @param to
     * @param cc
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发送带附件的邮件
     * @param from
     * @param to
     * @param cc
     * @param subject
     * @param content
     * @param file
     */
    public void sendAttachFileMail(String from, String to, String cc, String subject, String content, File file) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content);
            helper.addAttachment(file.getName(), file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送带图片资源的邮件
     * @param from
     * @param to
     * @param cc
     * @param subject
     * @param content
     * @param srcPath
     * @param resIds
     */
    public void sendMailWithImg(String from, String to, String cc, String subject, String content, String[] srcPath, String[] resIds) {
        if (srcPath.length != resIds.length) {
            System.out.println("发送失败");
            return;
        }
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content, true);
            for (int i = 0; i < srcPath.length; i++) {
                FileSystemResource res = new FileSystemResource(new File(srcPath[i]));
                helper.addInline(resIds[i], res);
            }
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用FreeMarker构建邮件模块
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String from, String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
