import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n==0){
            System.out.println(0);
            return;
        }
        int[] PI = new int [n];
        for(int i = 0; i < n; i++ ){
            PI[i] = sc.nextInt();
        }
        int sum = 0;
        int max = PI[0];
        for(int i : PI) {
            if(sum > 0){
                sum += i;
            }else{
                sum = i;
            }
            max = Math.max(sum,max);
        }
        if(max<0){
            max=0;
        }
        System.out.println(max);
    }
}