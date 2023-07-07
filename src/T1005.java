import java.util.*;

public class T1005 {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] w = new int[n];
            int total = 0;
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt();
                total += w[i];
            }
            Arrays.sort(w);
            int[] dp = new int[total/2+1];
            for (int i = 0; i < n; i++) {
                for (int j = total/2; j >= w[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j-w[i]] + w[i]);
                }
            }
            int diff = total - 2 * dp[total/2];
            System.out.println(diff);

        }

}

