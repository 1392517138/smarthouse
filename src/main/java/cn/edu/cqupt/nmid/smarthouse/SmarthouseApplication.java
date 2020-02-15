package cn.edu.cqupt.nmid.smarthouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("cn.edu.cqupt.nmid.smarthouse.pojo")
public class SmarthouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarthouseApplication.class, args);
    }
}
