package cn.edu.cqupt.nmid.smarthouse.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/10 2:41 PM
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Pi")
                .enable(true);
    }

    //配置apiInfo即Swagger-ui
    private ApiInfo apiInfo() {
        Contact contact = new Contact("piwenjing", "", "pi.wenjing@nexuslink.cn");
        return new ApiInfo("居家护理咨询与体征监测平台", "test---test",
                "1.0", null, contact, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}
