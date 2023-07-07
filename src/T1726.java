import java.util.*;
public class T1726{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long[] x = new long[(int) n];
        long[] y = new long[(int) n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        Arrays.sort(x);
        Arrays.sort(y);
        long dist = 0;
        for (int i = 1; i<n;i++){
            dist+= (x[i]-x[i-1])*i*(n-i)*2;
            dist+= (y[i]-y[i-1])*i*(n-i)*2;
        }
        System.out.println(dist/(n*(n-1)));
    }
}
