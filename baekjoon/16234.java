import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static class Pair{
        int y;
        int x;
        public Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static int N;
    static int L;
    static int R;

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        L = scan.nextInt();
        R = scan.nextInt();

        int[][] A = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                A[i][j] = scan.nextInt();
            }
        }
        int answer = 0;

        boolean isUpdated = false;
        while(true){
            boolean[][] visited = new boolean[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(update(A, i, j, visited)){
                        isUpdated = true;
                    }
                }
            }
            if(!isUpdated) break;
            answer ++;
            isUpdated = false;
        }
        System.out.println(answer);
    }
    public static boolean update(int[][] A, int i, int j, boolean[][] visited){
        Queue<Pair> Q = new LinkedList();

        Q.add(new Pair(i, j));
        int[][] dist = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        Queue<Pair> openedQ = new LinkedList(); //국경이 열린 좌표들의 모음
        int sum = 0;

        while(!Q.isEmpty()) {
            Pair p = Q.poll();
            int curY = p.y;
            int curX = p.x;

            if (visited[curY][curX]) continue;
            visited[curY][curX] = true;
            openedQ.add(p);
            sum += A[curY][curX];

            for (int[] curDist : dist) {
                int nextY = curY + curDist[0];
                int nextX = curX + curDist[1];

                if (nextY >= N || nextY < 0 || nextX >= N || nextX < 0) continue;
                if (visited[nextY][nextX]) continue;

                int sub = Math.abs(A[curY][curX] - A[nextY][nextX]);
                if (sub >= L && sub <= R) {
                    Q.add(new Pair(nextY, nextX));
                }
            }
        }
        if(openedQ.size()<=1) return false;
        sum = sum/openedQ.size();
        while(!openedQ.isEmpty()){
            Pair curP = openedQ.poll();
            A[curP.y][curP.x] = sum;
        }
        return true;
    }
}