package uz.dev.sort;

public class QuickSort implements SortingAlgorithm {
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right, int pivot) {
        int subArrayLength = right - left + 1;
        if (subArrayLength == 2 && arr[left] > arr[right]) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        } else if (subArrayLength > 2) {
            int pivotNum = arr[pivot];
            int[] pivotDividedArr = new int[subArrayLength];
            int lesserNumIndex = 0;
            int greaterNumIndex = subArrayLength - 1;
            for (int i = left; i <= right; i++) {
                if (i == pivot) continue;
                if (arr[i] >= pivotNum) {
                    pivotDividedArr[greaterNumIndex--] = arr[i];
                } else {
                    pivotDividedArr[lesserNumIndex++] = arr[i];
                }
            }
            int newPivotIndex = left + lesserNumIndex;
            pivotDividedArr[lesserNumIndex] = pivotNum;
            System.arraycopy(pivotDividedArr, 0, arr, left, subArrayLength);
            quickSort(arr, left, newPivotIndex - 1, newPivotIndex - 1);
            quickSort(arr, newPivotIndex + 1, right, right);
        }
    }
}
