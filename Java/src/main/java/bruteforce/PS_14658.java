package bruteforce;

import java.io.*;
import java.util.*;
import static java.lang.Math.min;

public class PS_14658 {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int countStars(int sx, int sy, int ex, int ey, int k, Point[] points) {

        int cnt = 0;

        for(Point p : points) {
            int x = p.x;
            int y = p.y;

            if (x >= sx && x <= ex && y >= sy && y <= ey)
                cnt++;
        }
        return (k - cnt);
    }

    // L*L 트램펄린
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int ans = 0x3f3f3f3f;

        Point[] points = new Point[K];

        for (int i = 0; i < K; i++) {

            StringTokenizer line = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());

            points[i] = new Point(x, y);
        }

        // solve
        for (int i = 0; i < K; i++) {
            for(int j = 0; j < K; j++) {

                int sx = points[i].x;
                int sy = points[j].y;

                int cnt = countStars(sx, sy, sx + L, sy + L, K, points);
                ans = min(ans, cnt);
            }
        }
        bw.write(Integer.toString(ans));

        bw.flush();
        bw.close();
    }
}
