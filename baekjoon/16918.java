import java.io.*;
import java.util.*;

public class Main {

    public static class Point{
        int y;
        int x;
        int start;
        public Point(int y, int x, int start){
            this.y = y;
            this.x = x;
            this.start = start;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        int[][] bombTime = new int[R][C];

        Queue<Point> bombQ = new LinkedList();
        Queue<Point> emptyQ = new LinkedList();

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='.'){
                    emptyQ.add(new Point(i, j, 0));
                }
                else
                    bombQ.add(new Point(i, j, 0));
            }
        }

        int[][] dists = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i=2;i<=N;i++){
            if(i%2==0){
                while(!emptyQ.isEmpty()){
                    Point p = emptyQ.poll();
                    int curY = p.y;
                    int curX = p.x;
                    map[curY][curX] = 'O';
                    bombQ.add(new Point(curY, curX, i));
                    bombTime[curY][curX] = i;
                }
            }else{
                Queue<Point> tmpBombQ = new LinkedList();
                while(!bombQ.isEmpty() && bombQ.peek().start+3==i){
                    Point p = bombQ.poll();
                    int curY = p.y;
                    int curX = p.x;

                    if(map[curY][curX]=='.') continue;
                    if(bombTime[curY][curX]>p.start) continue;
                    map[curY][curX] = '.';
                    emptyQ.add(new Point(curY, curX, 0));

                    for(int[] dist : dists){
                        int nextY = curY + dist[0];
                        int nextX = curX + dist[1];

                        if(nextY>=R || nextY<0 || nextX>=C || nextX<0) continue;
                        if(map[nextY][nextX] == '.') continue;
                        tmpBombQ.add(new Point(nextY, nextX, 0));
                    }
                }
                while(!tmpBombQ.isEmpty()){
                    Point p = tmpBombQ.poll();
                    int curY = p.y;
                    int curX = p.x;

                    if(map[curY][curX]=='.') continue;

                    map[curY][curX] = '.';
                    emptyQ.add(new Point(curY, curX, 0));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();

    }
}
