public class ArrayImplQueueStack {
    public class BoundedQueue {
        int head;
        int tail;
        int size;
        Integer[] array;

        public BoundedQueue(int cap) {
            array= new Integer[cap];
            head = 0;
            tail = 1;
            size = 0;
        }

        public boolean offer(Integer ele) {
            if (size == array.length) {
                return false;
            }
            array[tail] = ele;
            tail = tail + 1 == array.length ? 0 : tail + 1;
            size++;
            return true;
        }

        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return array[head];
        }

        public Integer poll() {
            if (size == 0) {
                return null;
            }
            Integer res = array[head];
            head = head + 1 == array.length ? 0 : head + 1;
            size--;
            return res;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == array.length;
        }
    }

    public class BoundedStack {
        int head;
        Integer[] array;
        public BoundedStack(int cap) {
            array = new Integer[cap];
            head = -1;
        }

        public boolean offer(Integer ele) {
            if (head == array.length - 1) {
                return false;
            }
            array[++head] = ele;
            return true;
        }

        public Integer peek() {
            if (head == -1) {
                return null;
            }
            return array[head];
        }

        public Integer poll() {
            if (head == -1) {
                return null;
            }
            Integer res = array[head];
            head--;
            return res;
        }

        public int size() {
            return head + 1;
        }

        public boolean isEmpty() {
            return head == -1;
        }
    }
}
