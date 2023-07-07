import java.util.*;

class T1806 {
    static Map<String, List<String>> graph;
    static Map<Integer, Integer> times;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        times = new HashMap<>();

        String[] timeInfo = scanner.nextLine().split(" ");
        for (int i = 0; i < 10; i++) {
            times.put(i, Integer.parseInt(timeInfo[i]));
        }

        graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String telegraph = scanner.nextLine();
            graph.put(telegraph, new ArrayList<>());
        }

        String anka = scanner.nextLine();
        String chapaev = scanner.nextLine();

        // 更新连接图
        for (String telegraph1 : graph.keySet()) {
            for (String telegraph2 : graph.keySet()) {
                if (telegraph1.equals(telegraph2)) {
                    continue;
                }

                if (canSendMessage(telegraph1, telegraph2)) {
                    graph.get(telegraph1).add(telegraph2);
                    graph.get(telegraph2).add(telegraph1);  // 添加反向连接
                }
            }
        }

        List<String> path = bfs(anka, chapaev);

        if (path == null) {
            System.out.println(-1);
        } else {
            int time = calculateTime(path);
            System.out.println(time);
            System.out.println(path.size());
            System.out.println(String.join(" ", path));
        }
    }

    static boolean canSendMessage(String telegraph1, String telegraph2) {
        int diffCount = 0;

        for (int i = 0; i < telegraph1.length(); i++) {
            if (telegraph1.charAt(i) != telegraph2.charAt(i)) {
                diffCount++;

                if (diffCount > 2) {
                    return false;
                }
            }
        }

        return true;
    }

    static List<String> bfs(String start, String target) {
        Queue<String> queue = new LinkedList<>();
        Queue<List<String>> pathQueue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        pathQueue.offer(new ArrayList<>(Arrays.asList(start)));
        visited.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            List<String> path = pathQueue.poll();

            if (node.equals(target)) {
                return path;
            }

            List<String> adjNodes = graph.get(node);
            if (adjNodes != null) {
                for (String adjNode : adjNodes) {
                    if (!visited.contains(adjNode)) {
                        queue.offer(adjNode);
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(adjNode);
                        pathQueue.offer(newPath);
                        visited.add(adjNode);
                    }
                }
            }
        }

        return null;
    }

    static int calculateTime(List<String> path) {
        int time = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            String telegraph1 = path.get(i);
            String telegraph2 = path.get(i + 1);
            int prefixLength = getCommonPrefixLength(telegraph1, telegraph2);
            time += times.get(prefixLength);
        }

        return time;
    }

    static int getCommonPrefixLength(String s1, String s2) {
        int length = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                length++;
            } else {
                break;
            }
        }

        return length;
    }
}
