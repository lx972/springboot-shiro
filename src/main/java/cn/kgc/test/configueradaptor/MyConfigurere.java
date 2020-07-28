package cn.kgc.test.configueradaptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * cn.kgc.test.configueradaptor
 *
 * @Author Administrator
 * @date 11:00
 */
@Configuration
public class MyConfigurere extends WebMvcConfigurerAdapter {

    /**
     * 设置跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("POST", "GET", "PATCH", "PUT", "OPTIONS", "DELETE")
                .allowedOrigins("http://localhost:8080")
                .allowedHeaders("*");
    }
}
