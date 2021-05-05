import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int W;
    static int H;
    static int[][] map;
    static final int MAX_MOVEMENT = 40000;
    static final int[][] dist = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static final int[][] horseDist = {{-1, -2}, {-2, -1}, {1, -2}, {-2, 1}, {-1, 2}, {2, -1}, {1, 2}, {2, 1}};

    static class Point{
        int y;
        int x;
        int kCount;
        int sum;
        public Point(int y, int x, int kCount, int sum){
            this.y = y;
            this.x = x;
            this.kCount = kCount;
            this.sum = sum;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for(int i=0;i<H;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());

        br.close();
        bw.close();
    }
    public static int solution(){
        boolean[][][] visited = new boolean[H][W][K+1];
        Queue<Point> Q = new LinkedList();

        Q.add(new Point(0, 0, 0, 0));
        while(!Q.isEmpty()){
            Point curPoint = Q.poll();
            int curY = curPoint.y;
            int curX = curPoint.x;
            int curK = curPoint.kCount;
            int curSum = curPoint.sum;

            if(visited[curY][curX][curK]) continue;
            if(curY==H-1 && curX==W-1) return curSum;
            visited[curY][curX][curK] = true;

            for(int[] aDist : dist){
                int nextY = curY+aDist[0];
                int nextX = curX+aDist[1];

                if(nextY>=H || nextY<0 || nextX>=W || nextX<0) continue;
                if(!visited[nextY][nextX][curK] && map[nextY][nextX]==0){
                    Q.add(new Point(nextY, nextX, curK, curSum+1));
                }
            }
            if(curK+1<=K){
                for(int[] aDist : horseDist){
                    int nextY = curY+aDist[0];
                    int nextX = curX+aDist[1];

                    if(nextY>=H || nextY<0 || nextX>=W || nextX<0) continue;
                    if(!visited[nextY][nextX][curK+1] && map[nextY][nextX]==0){
                        Q.add(new Point(nextY, nextX, curK+1, curSum+1));
                    }
                }
            }
        }
        return -1;
    }
}