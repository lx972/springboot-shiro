package cn.kgc.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * cn.kgc.test
 *
 * @Author Administrator
 * @date 15:06
 */
@SpringBootApplication
@MapperScan("cn.kgc.test.mapper")
public class TestStart {
    public static void main(String[] args) {
        SpringApplication.run(TestStart.class, args);
    }
}
