package graph;

import java.io.*;
import java.util.*;

public class PS_5972 {

    static class Pair implements Comparable<Pair> {
        int node, dst;
        Pair(int node, int dst) {
            this.node = node;
            this.dst = dst;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.dst, o.dst);
        }
    }

    static int N, M;
    static List<Pair>[] list = new List[50001];
    static int[] dist = new int[50001];
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int c = Integer.parseInt(line.nextToken());

            list[a].add(new Pair(b, c));
            list[b].add(new Pair(a, c));
        }
    }

    public static void main(String[] args) throws IOException {

        input();

        // init
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.add(new Pair(1, 0));

        // solve
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dst;

            if (curDist > dist[curNode])
                continue;

            for(int i = 0; i < list[curNode].size(); i++) {

                Pair next = list[curNode].get(i);
                int nextNode = next.node;
                int nextDist = next.dst;

                if (dist[nextNode] > curDist + nextDist) {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new Pair(nextNode, dist[nextNode]));
                }
            }
        }

        // output
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(dist[N]));
        bw.flush();
        bw.close();
    }
}
