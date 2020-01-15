import java.util.*;
import java.util.LinkedList;

public class Word_ladder {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        Map<String, List<String>> neighbors = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        List<String> list = new ArrayList<>();
        dict.add(start);


        bfs(start, end, distance, neighbors, dict);
        dfs(start, end, distance, neighbors, dict, list, res);

        return res;
    }

    private void bfs(String start, String end, Map<String, Integer> distance, Map<String, List<String>> neighbors, Set<String> dict) {
        for (String str : dict) {
            neighbors.put(str, new ArrayList<>());
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isFound = false;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> neis = getNeighbors(cur, dict);
                int curDis = distance.get(cur);
                for (String nei : neis) {
                    neighbors.get(cur).add(nei);
                    if (!distance.containsKey(nei)) {
                        distance.put(nei, curDis + 1);
                        if (nei.equals(end)) {
                            isFound = true;
                        } else {
                            queue.offer(nei);
                        }
                    }
                }
            }
            if (isFound) {
                break;
            }
        }
    }

    private void dfs(String cur, String end, Map<String, Integer> distance, Map<String, List<String>> neighbors, Set<String> dict, List<String> list, List<List<String>> res) {
        list.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<>(list));
        } else {
            for (String next : neighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, distance, neighbors, dict, list, res);
                }
            }
        }
        list.remove(list.size() - 1);
    }

    private List<String> getNeighbors(String node, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char chs[] = node.toCharArray();

        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) {
                    continue;
                }
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }
        }
        return res;
    }
}
