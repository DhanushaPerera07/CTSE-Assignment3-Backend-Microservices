package lk.sliit.ecommercejavaproject;

import lk.sliit.ecommercejavaproject.config.LogConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories(basePackages = {
        "lk.sliit.ecommercejavaproject.repository"
})
@EnableAspectJAutoProxy
public class ECommerceJavaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceJavaProjectApplication.class, args);
    }

}
