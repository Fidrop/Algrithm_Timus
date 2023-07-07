import java.util.*;

public class T1322 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt()-1;
        char[] input = sc.next().toCharArray();
        int[][] relation = new int[input.length][2];
        for (int i = 0; i< input.length;i++){
            relation[i][0] = i;
            relation[i][1] = input[i];
        }
        Arrays.sort(relation, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        char[] ans = new char[input.length];
        for (int i = 0; i<input.length; i++){
            n = relation[n][0];
            ans[i] = input[n];
        }
        System.out.println(ans);
    }
}


