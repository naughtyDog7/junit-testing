package uz.dev.sort;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

abstract class SortingAlgorithmTest<T extends SortingAlgorithm> {

    abstract T getSortingAlgorithm();

    @Test
    public void testWithSizeZero() {
        sortTestTemplateForSize(0, 0);
    }

    @Test
    public void testWithSizeOne() {
        sortTestTemplateForSize(0, 0);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/seedsForRandom.csv")
    public void testWithSizeTwo(int seedForRand) {
        sortTestTemplateForSize(0, seedForRand);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/seedsForRandom.csv")
    public void testWithSizeEven(int seedForRand) {
        sortTestTemplateForSize(8, seedForRand);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/seedsForRandom.csv")
    public void testWithSizeOdd(int seedForRand) {
        sortTestTemplateForSize(9, seedForRand);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/seedsForRandom.csv")
    public void testWithBigSize(int seedForRand) {
        sortTestTemplateForSize(1000, seedForRand);
    }

    private void sortTestTemplateForSize(int arrSize, int seedForRand) {
        int[] arr = getArray(arrSize, seedForRand);
        getSortingAlgorithm().sort(arr);
        assertArraySorted(arr);
    }

    private void assertArraySorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int currentNum = arr[i];
            int previousNum = arr[i - 1];
            assertThat(currentNum, Matchers.greaterThanOrEqualTo(previousNum));
        }
    }

    protected int[] getArray(int size, int seedForRand) {
        return new Random(seedForRand)
                .ints(size)
                .toArray();
    }
}