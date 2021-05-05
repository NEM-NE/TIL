import java.io.*;
import java.util.*;

class Edge<W, V> {
    private W weight;
    private V v;

    public void setEdge(W weight, V v) {
        this.weight = weight;
        this.v = v;
    }
}

public class weightGraph {
    static int[][] matrixGraph; // adjacent matrix
    static ArrayList<Edge<Integer, Integer>> listGraph = new ArrayList<>();

	public static void main(String[] args) {
        // 입력이 0 1 3/ 1 2 1/ 1 3 4등으로 주워지는 상황일 때
        Scanner scanner = new Scanner(System.in);
        matrixGraph = new int[5][5];

        while(scanner.hasNext()){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int weight = scanner.nextInt();

            matrixGraph[a][b] = weight;
            matrixGraph[b][a] = weight;
        }

        

        for (int i = 0; i < end; i++) {
            listGraph.add(new Edge<>());
        }

        for (int i = 0; i < m; i++) { // 간선의 갯수만큼 반복
            int n1 = scanner.nextInt(); // 노드 1
            int n2 = scanner.nextInt(); // 노드 2

            int weight = scanner.nextInt();

            listGraph.get(n1).setEdge(n2, weight);
            listGraph.get(n2).setEdge(n1, weight);
        }
    }


}
