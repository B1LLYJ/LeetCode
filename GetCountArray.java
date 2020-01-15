public class GetCountArray {
    public int[] countArray(int[] array) {
        int[] indexArray = initArray(array);
        int[] counterArray = new int[array.length];
        int[] helper = new int[array.length];
        mergeSort(array, indexArray, counterArray, helper, 0, array.length - 1);
        return counterArray;
    }

    private void mergeSort(int[] array, int[] indexArray, int[] counterArray, int[] helper, int left, int right) {
        if (left >= right) {
            return ;
        }

        int mid = left + (right - left) / 2;
        mergeSort(array, indexArray, counterArray, helper, left, mid);
        mergeSort(array, indexArray, counterArray, helper, mid + 1, right);
        merge(array, indexArray, counterArray, helper, left, mid, right);
    }

    private void merge(int[] array, int[] indexArray, int[] counterArray, int[] helper, int left, int mid, int right) {
        copyArray(indexArray, helper, left, right);
        int l = left;
        int r = mid + 1;
        int cur = left;
        while (l <= mid) {
            if (r > right || array[helper[l]] < array[helper[r]]) {
                counterArray[helper[l]] += (r - mid - 1);
                indexArray[cur++] = helper[l++];
            } else {
                indexArray[cur++] = helper[r++];
            }
        }
    }

    private void copyArray(int[] indexArray, int[] helper, int left, int right) {
        for (int i = left; i <= right; i++) {
            indexArray[i] = helper[i];
        }
    }

    private int[] initArray(int[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = i;
        }
        return res;
    }
}
