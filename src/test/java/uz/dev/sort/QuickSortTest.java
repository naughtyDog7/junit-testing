package uz.dev.sort;

import java.time.Duration;

import static java.time.Duration.ofMillis;

public class QuickSortTest extends SortingAlgorithmTest<QuickSort>
        implements PerformanceTest {
    @Override
    QuickSort getSortingAlgorithm() {
        return new QuickSort();
    }

    @Override
    public Duration getTimeout() {
        return ofMillis(400);
    }

    @Override
    public void runAction() {
        int[] arr = getArray(100000, 0);
        getSortingAlgorithm().sort(arr);
    }
}
