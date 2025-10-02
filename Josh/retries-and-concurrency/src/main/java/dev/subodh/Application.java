package dev.subodh;

import dev.subodh.component.ConcurrentClient;
import dev.subodh.component.RiskyClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;

@EnableResilientMethods
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner retryRunner(RiskyClient riskyClient) {
        // return _ -> riskyClient.write1();
        return _ -> riskyClient.write2();
    }

    //@Bean
    ApplicationRunner reliabilityRunner(TaskScheduler taskScheduler, ConcurrentClient concurrentClient) {
        return _ -> {
            taskScheduler.scheduleAtFixedRate(concurrentClient::debug, Duration.ofMillis(1500));

            if (taskScheduler instanceof TaskExecutor taskExecutor) {
                for (var i = 0; i < 1000; i++) {
                    taskExecutor.execute(() -> {
                        try {
                            concurrentClient.write();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        };
    }
}
