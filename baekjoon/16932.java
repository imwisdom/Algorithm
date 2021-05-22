import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Shape{
        int y;
        int x;
        public Shape(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static int n;
    public static int m;
    public static final int[][] dists = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int groupN = 2;
        int[] sumOfGroup = new int[500002];
        Queue<Shape> zeroQ = new LinkedList();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j]==1){
                    makeGroup(map, new Shape(i, j), groupN, sumOfGroup);
                    groupN++;
                }else if(map[i][j]==0) zeroQ.add(new Shape(i, j));
            }
        }
        int solution = 0;
        while(!zeroQ.isEmpty()){
            Shape curShape = zeroQ.poll();
            solution = Math.max(solution, sumOfMerge(map, new Shape(curShape.y, curShape.x), sumOfGroup));
        }
        System.out.println(solution);

    }
    public static int sumOfMerge(int[][] map, Shape startShape, int[] sumOfGroup){
        int i = startShape.y;
        int j = startShape.x;

        int solution = 1;

        HashSet<Integer> set = new HashSet();
        for(int[] dist : dists){
            int nextY = i+dist[0];
            int nextX = j+dist[1];

            if(nextY>=n || nextY<0 || nextX>=m || nextX<0) continue;
            int curGroup = map[nextY][nextX];
            if(curGroup>1 && !set.contains(curGroup)){
                solution += sumOfGroup[curGroup];
                set.add(curGroup);
            }
        }
        return solution;
    }
    public static void makeGroup(int[][] map, Shape startShape, int groupN,
                                 int[] sumOfGroup){
        Queue<Shape> Q = new LinkedList();
        Q.add(startShape);

        int sum = 0;
        while(!Q.isEmpty()){
            Shape curShape = Q.poll();
            int curY = curShape.y;
            int curX = curShape.x;

            if(map[curY][curX]!=1) continue;
            map[curY][curX] = groupN;
            sum++;

            for(int[] dist : dists){
                int nextY = curY+dist[0];
                int nextX = curX+dist[1];

                if(nextY>=n || nextY<0 || nextX>=m || nextX<0) continue;
                if(map[nextY][nextX]==1) {
                    Q.add(new Shape(nextY, nextX));
                }
            }
        }
        sumOfGroup[groupN] = sum;
    }
}