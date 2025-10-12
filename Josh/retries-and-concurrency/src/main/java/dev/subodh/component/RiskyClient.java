package dev.subodh.component;

import dev.subodh.exception.InvalidAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.core.retry.RetryException;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.core.retry.Retryable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class RiskyClient {

    private final AtomicInteger counter = new AtomicInteger();

    private final RetryTemplate retryTemplate;

    public RiskyClient() {
        this.retryTemplate = new RetryTemplate(RetryPolicy.builder()
                .maxAttempts(4)
                .includes(InvalidAuthenticationException.class)
                .build());
    }

    /* @Retryable(includes = {InvalidAuthenticationException.class}, maxAttempts = 4)
    public void write1() throws InvalidAuthenticationException {
        log.info("attempting write, # {}", this.counter.get());
        if (this.counter.incrementAndGet() < 3) {
            throw new InvalidAuthenticationException();
        }
        log.info("finally executing write");
    }*/

    public void write2() throws InvalidAuthenticationException, RetryException {

        this.retryTemplate.execute(new Retryable<>() {

            @Override
            public @Nullable Object execute() throws Throwable {
                log.info("attempting write, # {}", counter.get());
                if (counter.incrementAndGet() < 3) {
                    throw new InvalidAuthenticationException();
                }
                log.info("finally executing write");
                return null;
            }
        });
    }
}
