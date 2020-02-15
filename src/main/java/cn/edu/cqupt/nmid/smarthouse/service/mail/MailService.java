package cn.edu.cqupt.nmid.smarthouse.service.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author piwenjing
 * @description 通过邮件发送验证码
 * @date 2020/1/10 5:12 PM
 */
@Service
public class MailService {
    @Resource
    private JavaMailSenderImpl mailSender;
//    @Autowired
//    RabbitTemplate rabbitTemplate;

    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.title}")
    private String title;

    public void sendMail(String[] to, String content) {
        System.out.println("---to" + to[0]);
        System.out.println("titile:" + title);
        System.out.println("---from" + from);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to[0]);
        message.setSubject(title);
        message.setText(content);
//        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_DIRECT_INFORM, RabbitmqConfig.routingKey,
//                message);

        mailSender.send(message);
    }

}
