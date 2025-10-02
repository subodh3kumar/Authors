package dev.subodh.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class ConcurrentClient {

    private final Set<String> threads = new ConcurrentSkipListSet<>();

    @ConcurrencyLimit(1)
    public void write() throws Exception {
        Thread.sleep(1000);
        threads.add(Thread.currentThread().getName());
    }

    public void debug() {
        log.info("{} : {}", threads.size(), threads);
    }
}
