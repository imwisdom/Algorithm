import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int T;
    static int[][] map;
    static final int[][] dist = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    static class Point{
        int y;
        int x;
        int gram;
        int sum;
        public Point(int y, int x, int gram, int sum){
            this.y = y;
            this.x = x;
            this.gram = gram;
            this.sum = sum;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = solution();
        if(ans>T) bw.write("Fail\n");
        else bw.write(ans+"\n");

        br.close();
        bw.close();
    }
    public static int solution(){
        boolean[][][] visited = new boolean[N][M][2];
        Queue<Point> Q = new LinkedList();

        Q.add(new Point(0, 0, 0, 0));
        while(!Q.isEmpty()){
            Point curPoint = Q.poll();
            int curY = curPoint.y;
            int curX = curPoint.x;
            int curGram = curPoint.gram;
            int curSum = curPoint.sum;

            if(visited[curY][curX][curGram]) continue;
            if(curY==N-1 && curX==M-1) return curSum;
            visited[curY][curX][curGram] = true;

            for(int[] aDist : dist){
                int nextY = curY+aDist[0];
                int nextX = curX+aDist[1];

                if(nextY>=N || nextY<0 || nextX>=M || nextX<0) continue;
                if(visited[nextY][nextX][curGram]) continue;
                if(map[nextY][nextX]==2)
                    Q.add(new Point(nextY, nextX, 1, curSum+1));
                else if(map[nextY][nextX]==0 || curGram==1)
                    Q.add(new Point(nextY, nextX, curGram,curSum+1));
            }
        }
        return Integer.MAX_VALUE;
    }
}