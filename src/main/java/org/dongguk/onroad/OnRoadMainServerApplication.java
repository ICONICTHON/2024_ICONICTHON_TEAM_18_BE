package org.dongguk.onroad;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.TimeZone;

@EnableAsync
@EnableJpaRepositories(basePackages = "org.dongguk.onroad.*.repository")
@EnableRedisRepositories(basePackages = "org.dongguk.onroad.security.repository.redis")
@SpringBootApplication
public class OnRoadMainServerApplication {
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(OnRoadMainServerApplication.class, args);
    }
}
