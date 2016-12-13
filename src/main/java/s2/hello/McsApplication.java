package s2.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by russl on 11/28/2016.
 */
@Configuration
@ComponentScan(basePackages = "s2")
@EnableAutoConfiguration
public class McsApplication {
    public static void main(String[] args ) {
        SpringApplication.run(McsApplication.class, args);
    }
}
