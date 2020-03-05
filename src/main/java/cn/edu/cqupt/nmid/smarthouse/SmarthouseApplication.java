package cn.edu.cqupt.nmid.smarthouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@EntityScan("cn.edu.cqupt.nmid.smarthouse.pojo")
public class SmarthouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarthouseApplication.class, args);
    }


}
