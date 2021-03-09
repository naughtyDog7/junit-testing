package uz.dev.sort;

import java.util.Arrays;

public class MergeSort implements SortingAlgorithm {
    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, right, mid);
        }
    }

    private void merge(int[] arr, int left, int right, int mid) {
        int[] leftSubArray = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightSubArray = Arrays.copyOfRange(arr, mid + 1, right + 1);
        for (int leftArrIndex = 0, rightArrIndex = 0;
             left <= right; left++) {
            if (leftArrIndex < leftSubArray.length && (rightArrIndex == rightSubArray.length ||
                    leftSubArray[leftArrIndex] <= rightSubArray[rightArrIndex])) {
                arr[left] = leftSubArray[leftArrIndex++];
            } else {
                arr[left] = rightSubArray[rightArrIndex++];
            }
        }
    }
}
