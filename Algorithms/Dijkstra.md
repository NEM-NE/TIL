# 다익스트라 알고리즘

    그래프의 어떤 정점 하나를 시작점으로 선택하고, 나머지 정점들로의 최단 거리를 모두 구한다.

정점 개수가 V, 간선 개수가 E일 때 기본적인 최적화를 거치면 O(ElogV)의 시간복잡도를 갖는다.
그래프는 유향인 경우가 많고 간선마다 이동거리가 존재하며 모든 거리값이 음수가 아닐때만 사용가능하다. (만약 음수라면 벨만-포드 알고리즘 사용)

## 동작원리

1. 아직 방문하지 않은 정점들 중 거리가 가장 짧은 정점을 하나 선택해 방문한다.
2. 해당 정점에 인접하고 아직 방문하지 않은 정점들의 거리를 갱신한다.

### 세부 흐름

![ㄴㄴ](./img/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC1.png)

해당 그래프에서 0번을 시작으로 최단 거리를 구할 때 아래와 같이 적용한다.

1. 아직 방문하지 않은 정점들 중 거리가 가장 짧은 정점을 하나 선택해 방문한다.

즉, 0에서 인접하면서 거리가 짧은 정점들 부터 먼저 넣어준다. (지속적으로 최단 거리 테이블을 최신화 시켜준다.)

![ㄴㄴ](./img/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC2.png)

2. 1번, 2번, 5번 순으로 들어간다.

3. 그 다음으로 1번 정점에 인접하고 아직 방문하지 않은 정점들의 거리를 갱신한다. (지속적으로 최단 거리 테이블을 최신화 시켜준다.)

4. 1 -> 2, 1 -> 3 순으로 들어간다.

![ㄴㄴ](./img/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC3.png)

5. 1번에서 2번으로 가는 길이 제일 짧았음으로 이제 2번 정점을 기준으로 다시 반복한다. (지속적으로 최단 거리 테이블을 최신화 시켜준다.)

이후 동작이 완료되면 최단 거리 테이블안에 있는 값들이 정점까지의 최단거리를 나타낸다.

## 구현하기

이를 완전 탐색 방식으로 진행한다면 하나의 정점에 대해 비교 O(V-1) 그리고 V개의 정점에 대해 진행하므로 O(V^2)가 소요된다.
따라서 우선순위 큐를 통해 구현한다.

dist[v], v를 쌍으로 우선순위 큐에 넣으며 우선 순위에 대한 기준은 dist[v] 값이 작은 기준으로 한다.
이렇게 진행할 경우 동일한 정점에 대한 쌍이 들어갈 수 있는데 이는 방문여부에 따라 걸러주면된다.

    이를 통해 시간복잡도는 O(ElogV)가 된다. (인접 리스트로 구현했을 경우)

또한 예외처리를 할 부분이 있는데 그래프 자체가 연결 그래프가 아니거나 유향 그래프에서 시작점에서 어떤 정점으로 못가는 경우가 있다.

## 예제를 통해 이해하기

[백준 1753](https://www.acmicpc.net/problem/1753)

```java

import java.io.*;
import java.util.*;

public class Practice {
    // 11:30
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>(); // 그래프
    static int[] dist; // 최단 거리를 저장하는 배열
    static boolean[] visited; // 방문했는지 확인하는

    static class Node {
        int idx, w; // 목적지, 비용

        public Node(int idx, int w){
            this.idx = idx;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        dist = new int[v+1];
        visited = new boolean[v+1];

        // 기본적인 초기화
        for(int i = 0; i <= v; i++){
            map.add(new ArrayList<Node>());
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // 값들 넣어준다.
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map.get(u).add(new Node(x, w)); // Node로 넣어주고
        }

        solve(k);

        for(int i=1; i<=v; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            }
            else {
                System.out.println(dist[i]);
            }
        }
    }

    private static void solve(int k){
        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.w - o2.w);  // 우선순위 기준은 적은 비용부터 나오도록
        pq.add(new Node(k, 0)); // 시작점에서 시작점으로 가는 걸 넣는다. 그래서 비용은 0

        while(!pq.isEmpty()){
            Node a = pq.poll();
            if(visited[a.idx]) continue; // 방문했는지 체크(한번이라도 방문을 했다면 그 정점에 대해 모든 경로를 다 경험 것)

            visited[a.idx] = true;
            for(Node o : map.get(a.idx)){
                if(dist[o.idx] > dist[a.idx] + o.w){    // 기존에 잇는 값이 최적이냐 아니면 지금 뽑은 값에 대한 결과가 최적이냐 비교
                    dist[o.idx] = dist[a.idx] + o.w;   // 새로운게 최적이면 dist에 최신화 해주고
                    pq.add(new Node(o.idx, dist[o.idx])); // 최적을 기점으로 다음 정점을 찾을 수 있도록 넣어준다.
                }
            }
        }
    }

    private static void solve(int k){
        PriorityQueue<Node> pq = new PriorityQueue<Node>(o1, o2 -> o1.w - o2.w);
        pq.add(new Node(k, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(visited[node.idx]) continue;
            visited[node.idx] = true;

            for(Node next : map.get(node.idx)){
                if(dist[next.idx] > dist[node.idx] + next.w){
                    dist[next.idx] = dist[node.idx] + next.w;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }

}

```
