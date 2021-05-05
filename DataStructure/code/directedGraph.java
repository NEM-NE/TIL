import java.io.*;
import java.util.*;

public class directedGraph {
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
        }

        for(int i = 0; i < end; i++){
            listGraph.add(new ArrayList<>());
        }

        while(scanner.hasNext()){
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            listGraph.get(a).add(b);
        }
    }


}
