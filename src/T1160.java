import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Connection implements Comparable<Connection> {
    int hub1;
    int hub2;
    int length;

    public Connection(int hub1, int hub2, int length) {
        this.hub1 = hub1;
        this.hub2 = hub2;
        this.length = length;
    }
    @Override
    public int compareTo(Connection other) {
        return Integer.compare(this.length, other.length);
    }
}
public class T1160 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();


        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int hub1 = scanner.nextInt();
            int hub2 = scanner.nextInt();
            int length = scanner.nextInt();
            connections.add(new Connection(hub1, hub2, length));
        }

        scanner.close();


        List<Connection> minSpanningTree = prim(N, connections);

        int maxCableLength = 0;
        for (Connection connection : minSpanningTree) {
            maxCableLength = Math.max(maxCableLength, connection.length);
        }

        System.out.println(maxCableLength);
        System.out.println(minSpanningTree.size());
        for (Connection connection : minSpanningTree) {
            System.out.println(connection.hub1 + " " + connection.hub2);
        }
    }
    public static List<Connection> prim(int N, List<Connection> connections) {
        List<List<Connection>> adjacencyList = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (Connection connection : connections) {
            adjacencyList.get(connection.hub1).add(connection);
            adjacencyList.get(connection.hub2).add(connection);
        }

        List<Connection> minSpanningTree = new ArrayList<>();
        PriorityQueue<Connection> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        pq.addAll(adjacencyList.get(1));

        while (!pq.isEmpty()) {
            Connection minEdge = pq.poll();
            int hub1 = minEdge.hub1;
            int hub2 = minEdge.hub2;

            if (visited[hub1] && visited[hub2]) {
                continue;
            }

            minSpanningTree.add(minEdge);

            int newHub = visited[hub1] ? hub2 : hub1;
            visited[newHub] = true;


            for (Connection connection : adjacencyList.get(newHub)) {
                if (!visited[connection.hub1] || !visited[connection.hub2]) {
                    pq.add(connection);
                }
            }
        }

        return minSpanningTree;
    }
}
