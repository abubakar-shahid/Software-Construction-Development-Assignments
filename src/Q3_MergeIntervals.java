import java.util.*;

public class Q3_MergeIntervals {

    public void mergeIntervals(List<int[]> intervals) {
        for (int i = 0; i < intervals.size(); i++) {
            if (i != intervals.size() - 1) {
                if (intervals.get(i + 1)[0] < intervals.get(i)[1]) {
                    intervals.get(i)[1] = intervals.get(i + 1)[1];
                    intervals.remove(i + 1);
                    System.out.println("Removed for [" + intervals.get(i)[0] + ", " + intervals.get(i)[1] + "]");
                    i--;
                }
            }
        }
    }

    public void print(List<int[]> intervals) {
        for (int i = 0; i < intervals.size(); i++) {
            System.out.print("[" + intervals.get(i)[0] + ", " + intervals.get(i)[1] + "]");
            if (i != intervals.size() - 1) {
                System.out.println(",");
            }
        }
        System.out.println();
    }

}
