import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class T1604 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int [][]type = new int[k][2];
        int sum = 0;
        for (int i = 0; i<k;i++){
            type[i][0] = i+1;
            type[i][1] = sc.nextInt();
            sum+=type[i][1];
        }
        Arrays.sort(type, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
        int[][]buckets = new int[k][type[0][1]];
        int current_i = 0;
        int current_j = 0;
        for (int i = 0; i < k; i++){
            for(int j = 0; j < type[i][1]; j++){
                buckets[current_i][current_j] = type[i][0];
                current_j++;
                if (current_j>= type[0][1]){
                    current_j = 0;
                    current_i++;
                }
            }
        }
        for (int j = 0; j<type[0][1];j++){
            for (int i = 0; i<k;i++){
                if (buckets[i][j] == 0){
                    continue;
                }
                System.out.print(buckets[i][j]+" ");
            }
        }
     //   System.out.println();
     //   int[] result = new int[sum];
     //   int result_i = 0;
     //   for (int j = 0; j < type[0][1]; j++){
     //       for (int i = 0; i < k; i++){
     //           if (buckets[i][j] == 0)
     //               continue;
     //           result[result_i] = buckets[i][j];
     //           result_i++;
     //       }
     //   }
     //   for (int i = 0; i < (result.length)-1; i++){
     //       System.out.print(result[i]+" ");
     //   }
     //   System.out.print(result[(result.length)-1]);
    }
}
