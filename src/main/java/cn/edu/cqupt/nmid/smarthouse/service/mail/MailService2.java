//package cn.edu.cqupt.nmid.smarthouse.service.mail;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;
//
///**
// * @author piwenjing
// * @description
// * @date 2020/2/14 9:31 PM
// */
//@Service
//public class MailService2 {
//    @Autowired
//    JavaMailSenderImpl mailSender;
//
//    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
//    private void send(SimpleMailMessage message) {
//        mailSender.send(message);
//    }
//}
