DFS & BFS
=======================

## 1. DFS(깊이 우선 탐색) & BFS(너비 우선 탐색)이란?
<br>

>  DFS & BFS는 그래프에서 '탐색'을 위해 사용하는 탐색 알고리즘입니다. <br>
> 따라서 해당 알고리즘에 대해 알기 전에 비선형 자료구조인 그래프에 대해 이해를 먼저해야합니다.

<br>

[자료구조 : Graph](../DataStructure/Graph.md)

<br>

그래프 탐색에서 사용되는 DFS & BFS 알고리즘은 하나씩 하나씩 끝까지 탐색을 하는가, 깊지 않는 선에서 넓게 탐색하는가로 나눌 수 있습니다.

<br>

![탐색](./img/BFSDFS.png)

### DFS

<br>

DFS는 깊이 우선 탐색 알고리즘입니다. 즉, 깊이를 우선시하게 탐색하는 알고리즘입니다. 위의 그래프를 보면 루트인 A를 아래로 여러 정점들이 있습니다. 깊이 우선 탐색은 연결된 정점을 끝까지 탐색합니다.
예를 들어 A부터 DFS를 하면 A -> D -> F -> C -> E -> B 순으로 탐색합니다.

<br>

### BFS

<br>

BFS는 너비 우선 탐색 알고리즘입니다. DFS와는 다르게 연결된 정점을 따라 끝까지 탐색하는 것이 아니라 연결된 여러 정점들을 고루고루 탐색하는 방법입니다. A부터 DFS를 하면 A -> B -> C -> D -> E -> F 순으로 탐색합니다.

<br>

## 2. 언제 사용해야되나?
<br>

    DFS & BFS 알고리즘은 그래프 탐색 알고리즘입니다. 따라서 해당 알고리즘 문제에 그래프 관련 문제여야되고 정점들을 확인해야 할 때 사용됩니다. 
    
두 알고리즘에 차이점은 모든 정점을 탐색을 하는가? 최단 경로를 빠리게 구할 수 있는가? 경로의 특징을 저장해하는가? 등으로 나눌 수 있습니다. DFS는 모든 정점을 탐색, 경로의 특징을 저장할 때 사용하고 BFS는 최단 경로를 구할 때 사용합니다.

<Br>

## 3. 사용 방법
<br>

### DFS

- 스택이나 재귀 함수를 이용하여 구현
- 구현 하기 쉬움

<br>

### BFS

- 큐를 이용하여 구현
- 구현 하기 쉬움

<br>

## 4. 효율성
<br>

    DFS, BFS 모두 간선의 수와 정점의 수로 결정 된다. O(V + E)로 동일합니다.

<br>

## 5. 예시
<br>

### DFS

``` java

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


```

<br>

### BFS

<br>

``` java

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


```

## 6. 기타
<br>

    자주 출제되는 알고리즘이지만 다양한 유형이 존재한다. 반드시 숙지하고 어떻게 응용되는지 확인 하면 좋습니다!

<br>

## 7. 참고
<br>

1. [ries 블로그](https://blog.naver.com/kks227/220785747864)
2. [이것이 코딩 테스트다]()