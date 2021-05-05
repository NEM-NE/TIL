import java.io.*;
import java.util.*;

public class undirectedGraph {
    static int[][] matrixGraph; // adjacent matrix
    static ArrayList<ArrayList<Integer>> listGraph = new ArrayList<>();

	public static void main(String[] args) {
        // 입력이 0 1 / 1 2 / 1 3 등으로 주워지는 상황일 때
        Scanner scanner = new Scanner(System.in);
        matrixGraph = new int[5][5];

        while(scanner.hasNext()){
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            matrixGraph[a][b] = 1;
            matrixGraph[b][a] = 1;
        }

        while(scanner.hasNext()){
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            listGraph.get(a).add(b);
            listGraph.get(b).add(a);

        }
    }


}
