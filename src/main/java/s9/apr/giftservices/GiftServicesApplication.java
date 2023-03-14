package s9.apr.giftservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "s9.apr")
public class GiftServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(GiftServicesApplication.class, args);
    }

}
