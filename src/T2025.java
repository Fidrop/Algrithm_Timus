import java.util.Scanner;

public class T2025 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[]res = new int[t];
        for (int i = 0;i < t;i++){
            int n = sc.nextInt();
            int k = sc.nextInt();
            res[i] = solve(k,n);
        }
        for (int i = 0;i<t;i++){
            System.out.println(res[i]);
        }
    }
    public static int solve(int k,int n){
        int[]team = new int[k];
        int total = 0;
        int inner = 0;
        for (int i = 0;i < k;i++){
           team[i] = n/k;
        }
        for(int i = 0; i < n%k;i++){
            team[i]+=1;
        }
         total = n*(n-1)/2;
        for(int i = 0; i<k;i++){
            inner+=team[i]*(team[i]-1)/2;
        }
        return total-inner;

    }
}
