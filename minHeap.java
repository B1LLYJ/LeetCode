import java.util.NoSuchElementException;

public class minHeap {
    private int[] array;
    private int size;

    public minHeap(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("input array can not be null or empty");
        }
        this.array = array;
        size = array.length;
        heapify();
    }

    public void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    public minHeap(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("capacity can not be <= 0");
        }
        array = new int[cap];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public  boolean isFull() {
        return size == array.length;
    }

    private void percolateUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (array[parentIndex] > array[index]) {
                swap(array, parentIndex, index);
            } else {
                break;
            }
            index = parentIndex;
        }
    }

    private void percolateDown(int index) {
        while (index <= size / 2 - 1) {
            int leftIndex = index * 2 + 1;
            int rightIndex = index * 2 + 2;
            int swapCandidate = leftIndex;
            if (rightIndex < size - 1 && array[rightIndex] <= array[leftIndex]) {
                swapCandidate = rightIndex;
            }
            if (array[index] > array[swapCandidate]) {
                swap(array, index, swapCandidate);
            } else {
                break;
            }
            index = swapCandidate;
        }
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("heep is empty");
        }
        return array[0];
    }

    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException("heep is empty");
        }
        int tmp = array[0];
        swap(array, array.length - 1, 0);
        size--;
        percolateDown(0);
        return tmp;
    }

    public void offer(int e) {
        if (size == array.length) {
            int[] newArray = new int[array.length + array.length / 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size] = e;
        size++;
        percolateUp(size-1);
    }

    public int update(int index, int e) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("invalid index range");
        }
        int res = array[index];
        array[index] = e;
        if (res > e) {
            percolateUp(index);
        } else {
            percolateDown(index);
        }
        return res;
    }
}
