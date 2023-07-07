import java.util.Scanner;

public class T1155 {
    static int[] points = new int[8];
    static int set1, set2;

    static void Adj(int x, int y, int tmp) {
        while(points[x] != 0) {
            if(points[tmp] == 0) {
                points[tmp]++;
                points[y]++;
                System.out.printf("%c%c+\n", 'A' + y, 'A' + tmp);
            }
            points[tmp]--;
            points[x]--;
            System.out.printf("%c%c-\n", 'A' + x, 'A' + tmp);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 0;
        while(n < 8) {
            points[n] = in.nextInt();
            n++;
        }

        set1 = points[0] + points[2] + points[5] + points[7];
        set2 = points[1] + points[3] + points[4] + points[6];
        if(set1 != set2) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        Adj(2, 0, 1);
        Adj(5, 0, 4);
        Adj(7, 0, 4);
        Adj(6, 4, 5);
        Adj(1, 4, 0);
        Adj(3, 4, 0);
        while(points[0] != 0) {
            System.out.printf("%c%c-\n", 'A', 'E');
            points[0]--;
        }
    }
}

