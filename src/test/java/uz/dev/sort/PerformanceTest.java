package uz.dev.sort;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public interface PerformanceTest {

    @Test
    default void testPerformance() {
        assertTimeout(getTimeout(), this::runAction);
    }

    Duration getTimeout();
    void runAction();
}
