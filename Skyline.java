import java.util.*;

public class Skyline {

//    int[][] intervals = new int[][]{{2, 9, 10},{3, 7, 5},{5, 12, 12},{15, 20, 10},{19, 24, 8}};
//    int[][] res = test.getSkyline(intervals);

    public int[][] getSkyline(int[][] buildings) {
        List<int[]> resList = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] building : buildings) {
            height.add(new int[]{building[0], -building[2]});
            height.add(new int[]{building[1], building[2]});
        }
        Collections.sort(height, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                if (i1[0] != i2[0]) {
                    return i1[0] - i2[0];
                } else {
                    return i1[1] - i2[1];
                }
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(11, Collections.reverseOrder());
        pq.offer(0);
        int prev = 0;
        for (int[] h : height) {
            if (h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if (prev != cur) {
                resList.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return getInt(resList);
    }

    private int[][] getInt(List<int[]> list) {
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}
