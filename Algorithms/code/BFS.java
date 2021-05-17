import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    static void bfs(int x){
        Queue<Integer> que = new LinkedList<>();
        visited[x] = true;
        que.offer(x);

        while(!que.isEmpty()){
            int num = que.poll();

            for(int i = 0; i < graph.get(num).size(); i++){
                if(!visited[graph.get(num).get(i)]){
                    visited[graph.get(num).get(i)] = true;
                    que.offer(graph.get(num).get(i));
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int v = scanner.nextInt();
        int e = scanner.nextInt();

        graph = new ArrayList<>();

        for(int i = 0; i < v+1; i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < e; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i = 1; i < v+1; i++){
            visited = new boolean[v+1];
            bfs(i);
        }
    }
}
