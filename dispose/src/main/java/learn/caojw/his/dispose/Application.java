package learn.caojw.his.dispose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("learn.caojw.his.common.api")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
