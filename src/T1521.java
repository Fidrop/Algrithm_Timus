import java.util.Scanner;
public class T1521 {
    static int[] tree = new int[500000];
    static int answer = 0;
    public static void pushUp(int node) {
        tree[node] = tree[node << 1 | 1] + tree[node << 1];
    }
    public static void buildSegmentTree(int left, int right, int node) {
        tree[node] = right - left + 1;
        if (left == right) return;
        int mid = (left + right) >> 1;
        buildSegmentTree(left, mid, node << 1);
        buildSegmentTree(mid + 1, right, node << 1 | 1);
    }

    public static int getSum(int queryLeft, int queryRight, int rangeLeft, int rangeRight, int node) {
        if (queryLeft <= rangeLeft && queryRight >= rangeRight) {
            return tree[node];
        }
        int mid = (rangeLeft + rangeRight) >> 1;
        int sum = 0;
        if (queryLeft <= mid) sum += getSum(queryLeft, queryRight, rangeLeft, mid, node << 1);
        if (queryRight > mid) sum += getSum(queryLeft, queryRight, mid + 1, rangeRight, node << 1 | 1);
        return sum;
    }

    public static void remove(int position, int rangeLeft, int rangeRight, int node) {
        if (rangeLeft == rangeRight) {
            tree[node] = 0;
            answer = rangeLeft;
            return;
        }
        int mid = (rangeLeft + rangeRight) >> 1;
        if (position <= tree[node << 1]) {
            remove(position, rangeLeft, mid, node << 1);
        } else {
            remove(position - tree[node << 1], mid + 1, rangeRight, node << 1 | 1);
        }
        pushUp(node);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            answer = 0;
            buildSegmentTree(1, n, 1);
            for (int i = 0; i < n; i++) {
                int k;
                if (answer == 0) {
                    k = m % tree[1];
                } else {
                    int sum = getSum(1, answer, 1, n, 1);
                    k = (sum + m) % tree[1];
                }
                if (k == 0) k = tree[1];
                remove(k, 1, n, 1);
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(answer);
            }
            System.out.println();
        }
    }
}







