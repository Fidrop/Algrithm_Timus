import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class T1207 {
    static class Point {
        int x, y;
        double tan(Point p) {
            if (p.x == x) {
                if (p.y > y) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            return (double)(p.y-y) / (double)(p.x-x);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] points = new Point[n];
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            Point point = new Point();
            point.x = scanner.nextInt();
            point.y = scanner.nextInt();
            points[i] = point;
            if (points[i].x < points[minIndex].x) {
                minIndex = i;
            }
        }

        Point origin = points[minIndex];
        double[][] tanArr = new double[n-1][2];
        int current = 0;
        for (int i = 0; i < n; i++) {
            if (i == minIndex) {
                continue;
            }
            Point point = points[i];
            tanArr[current][0] = origin.tan(point);
            if (point == points[minIndex]) {
                tanArr[current][1] = minIndex;
            } else if (point == origin) {
                tanArr[current][1] = -1;
            } else {
                tanArr[current][1] = i;
            }

            current++;
        }

        Arrays.sort(tanArr, new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });

        System.out.print(minIndex + 1);
        System.out.print(" ");
        System.out.print((int) tanArr[n/2-1][1] + 1);

    }
}
