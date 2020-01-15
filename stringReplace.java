import java.util.ArrayList;

public class stringReplace {
    public String replace(String input, String source, String target) {
        // Write your solution here
        char[] array = input.toCharArray();
        if (source.length() >= target.length()) {
            return shortReplace(array, source, target);
        }
        return longReplace(array, source, target);
    }
    public String shortReplace(char[] array, String source, String target) {
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (fast <= array.length - source.length() && equalSubstring(array, fast, source)) {
                copySubstring(array, slow, target);
                slow += target.length();
                fast += source.length();
            } else {
                array[slow++] = array[fast++];
            }
        }
        return new String(array, 0, slow);
    }
    public String longReplace(char[] array, String source, String target) {
        ArrayList<Integer> matches = getAllmatches(array, source);
        char[] res = new char[array.length + matches.size() * (target.length() - source.length())];
        int lastIndex = matches.size() - 1;
        int fast = array.length - 1;
        int slow = res.length - 1;
        while (fast >= 0) {
            if (lastIndex >= 0 && fast == matches.get(lastIndex)) {
                copySubstring(res, slow - target.length() + 1, target);
                slow -= target.length();
                fast -= source.length();
                lastIndex--;
            } else {
                array[slow--] = array[fast--];
            }
        }
        return new String(res);
    }
    public boolean equalSubstring(char[] array, int index, String source) {
        for (int i = 0; i < source.length(); i++) {
            if (array[index + i] != source.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public void copySubstring(char[] array, int index, String target) {
        for (int i = 0; i < target.length(); i++) {
            array[index + i] = target.charAt(i);
        }
    }
    public ArrayList<Integer> getAllmatches(char[] array, String source) {
        ArrayList<Integer> matches = new ArrayList<Integer>();
        int i = 0;
        while (i <= array.length - source.length()) {
            if (equalSubstring(array, i, source)) {
                matches.add(i + source.length() - 1);
            } else {
                i++;
            }
        }
        return matches;
    }
}
