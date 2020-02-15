//package cn.edu.cqupt.nmid.smarthouse.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author piwenjing
// * @description
// * @date 2020/2/11 7:14 PM
// */
//@Configuration
//public class RabbitmqConfig {
//
//    //队列bean名称
//    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
//    //交换机名称
//    public static final String EXCHANGE_DIRECT_INFORM = "exchange_direct_inform";
//
//    //routingKey
//    @Value("${smarthouse.mq.routingKey}")
//    public static String routingKey;
//
//    //声明交换机
//    @Bean(EXCHANGE_DIRECT_INFORM)
//    public Exchange EXCHANGE_DIRECT_INFORM() {
//        return ExchangeBuilder.directExchange(EXCHANGE_DIRECT_INFORM).durable(true).build();
//    }
//
//    //声明队列
//    @Bean(QUEUE_INFORM_EMAIL)
//    public Queue QUEUE_INFORM_EMAIL() {
//        return new Queue(QUEUE_INFORM_EMAIL);
//    }
//
//    //绑定队列到交换机
//    @Bean
//    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
//                                              @Qualifier(EXCHANGE_DIRECT_INFORM) Exchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
//    }
//}
