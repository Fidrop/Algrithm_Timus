import java.util.*;

public class T1080 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            int country = sc.nextInt();
            while (country != 0) {
                map[i][country - 1] = 1;
                map[country - 1][i] = 1;
                country = sc.nextInt();
            }
        }
        int[] colors = new int[n];
        Arrays.fill(colors, 2);
        colors[0] = 1;
        boolean isValid = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int country = queue.poll();
            int color = colors[country];
            for (int i = 0; i < n; i++) {
                if (map[country][i] == 1) {
                    if (colors[i] == 2) {
                        colors[i] = -color;
                        queue.offer(i);
                    } else if (colors[i] == color) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (!isValid) {
                break;
            }
        }
        if (isValid) {
            for (int i = 0; i < n; i++) {
                System.out.print((colors[i] == 1) ? "0" : "1");
            }
        } else {
            System.out.println(-1);
        }
    }
}
