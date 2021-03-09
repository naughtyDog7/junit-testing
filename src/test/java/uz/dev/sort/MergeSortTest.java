package uz.dev.sort;

public class MergeSortTest extends SortingAlgorithmTest<MergeSort> {
    @Override
    MergeSort getSortingAlgorithm() {
        return new MergeSort();
    }
}
