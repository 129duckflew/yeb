package cn.duckflew.mail;

import cn.duckflew.pojo.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class SendMail
{
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    MailProperties mailProperties;
    @RabbitListener(queues = {"mail.welcome"})
    public void send(Employee employee)
    {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message);
        try
        {
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(employee.getEmail());
            helper.setSubject("入职欢迎邮件");
            helper.setSentDate(new Date());
            Context context=new Context();
            context.setVariable("name",employee.getName());
            context.setVariable("posName",employee.getName());
//            context.setVariable("joblevelName",employee.getJobLevel().getName());
//            context.setVariable("departmentName",employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            javaMailSender.send(message);
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

}
