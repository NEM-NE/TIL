import java.util.ArrayList;
import java.util.Scanner;

public class DFS {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    static void dfs(int x){
        visited[x] = true;

        for(int i = 0; i < graph.get(x).size(); i++){
            if(!visited[graph.get(x).get(i)]){
                dfs(graph.get(x).get(i));
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
            dfs(i);
        }
    }
}
