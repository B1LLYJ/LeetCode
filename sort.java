public class sort {
    public void main(String[] args) {
        sort test = new sort();
    }
    //helper functions
    private int random(int[] array, int left, int right) {
        int res = left + (int)(Math.random() * (right - left + 1));
        return res;
    }
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    //operation functions
    //Selection sort
    public int[] selection(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int tmp = 0;
            for (int j = i + 1; j < array.length; j++) {
                if (array[tmp] > array[j]) {
                    tmp = j;
                }
            }
            swap(array, tmp, i);
        }
        return array;
    }
    //Merge sort
    public int[] merge(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return merge(array, 0, array.length - 1);
    }
    public int[] merge(int[] array, int left, int right) {
        if (left == right) {
            return new int[]{array[left]};
        }
        int mid = (left + right) / 2;
        int[] leftArray = merge(array, left, mid);
        int[] rightArray = merge(array, mid + 1, right);
        int[] res = mergeSort(leftArray, rightArray);
        return res;
    }
    public int[] mergeSort(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int resIndex = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                res[resIndex++] = left[i++];
            } else {
                res[resIndex++] = right[j++];
            }
        }
        while (i < left.length) {
            res[resIndex++] = left[i++];
        }
        while (j < right.length) {
            res[resIndex++] = right[j++];
        }
        return res;
    }
    //Quick sort
    public int[] quick(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        quickSort(array, 0, array.length-1);
        return array;
    }
    public void quickSort(int[] array, int left,int right) {
        if (left >= right) {
            return ;
        }
        int pivot = partition(array, left, right);
        quickSort(array, left, pivot);
        quickSort(array, pivot+1, right);
    }
    public int partition(int[] array, int left, int right) {
        int pivotIndex = random(array, left, right);
        int pivot = array[pivotIndex];
        int leftBound = left;
        int rightBound = right-1;
        swap(array, pivotIndex, right);
        while (leftBound <= rightBound) {
            if (array[leftBound] <= pivot) {
                leftBound++;
            } else  if (array[rightBound] > pivot){
                rightBound--;
            } else {
                swap(array, leftBound++, rightBound--);
            }
        }
        swap(array, leftBound, right);
        return leftBound;
    }
}
