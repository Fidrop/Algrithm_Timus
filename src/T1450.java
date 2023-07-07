import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class T1450 {
    static class Node {
        ArrayList<Pair<Integer, Integer>> edges;
        int distance;
        int indegree;

        Node() {
            edges = new ArrayList<>();
            distance = Integer.MIN_VALUE;
            indegree = 0;
        }
    }

    static class Pair<T, U> {
        T first;
        U second;

        Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int readVal() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = readVal();
        int M = readVal();

        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < M; i++) {
            int x = readVal();
            int y = readVal();
            int c = readVal();
            nodes[x].edges.add(new Pair<>(y, c));
            nodes[y].indegree++;
        }

        int S = readVal();
        int F = readVal();
        nodes[S].distance = 0;

        Queue<Node> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (nodes[i].indegree == 0) {
                queue.add(nodes[i]);
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Pair<Integer, Integer> edge : node.edges) {
                int v = edge.first;
                int c = edge.second;
                nodes[v].distance = Math.max(nodes[v].distance, node.distance + c);
                nodes[v].indegree--;
                if (nodes[v].indegree == 0) {
                    queue.add(nodes[v]);
                }
            }
        }

        if (nodes[F].distance >= 0) {
            System.out.println(nodes[F].distance);
        } else {
            System.out.println("No solution");
        }
    }
}
