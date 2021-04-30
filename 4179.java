import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Person{
        int y;
        int x;
        int count;
        public Person(int y, int x, int count){
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
    static int N;
    static int M;
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        Queue<Person> personQ = new LinkedList();
        Queue<Integer> fireQ = new LinkedList();
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J') personQ.add(new Person(i, j, 0));
                if(map[i][j] == 'F'){
                    fireQ.add(i); fireQ.add(j);
                }
            }
        }
        int answer = solution(fireQ, personQ, map);
        if(answer==-1) System.out.println("IMPOSSIBLE");
        else System.out.println(answer);
    }
    public static int solution(Queue<Integer> fireQ, Queue<Person> personQ, char[][] map){
        boolean[][] visited = new boolean[N][M];
        int[][] dist = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while(!personQ.isEmpty()){
            int fireN = fireQ.size();
            int fireI = 0;
            while(fireI < fireN){
                int fireY = fireQ.poll();
                int fireX = fireQ.poll();

                for(int[] curDist : dist){
                    int nextY = fireY+curDist[0];
                    int nextX = fireX+curDist[1];

                    if(nextY>=N || nextY<0 || nextX>=M || nextX<0) continue;
                    if(map[nextY][nextX] == '#' || map[nextY][nextX] == 'F') continue;
                    map[nextY][nextX] = 'F';
                    fireQ.add(nextY); fireQ.add(nextX);
                }
                fireI = fireI+2;
            }
            int personN = personQ.size();
            int personI = 0;
            while(personI<personN){
                Person curPerson = personQ.poll();
                int curY = curPerson.y;
                int curX = curPerson.x;
                int curCount = curPerson.count;

                personI++;
                if(visited[curY][curX]) continue;
                visited[curY][curX] = true;

                if(curY==0 || curX==0 || curY==N-1 || curX==M-1){
                    return curCount+1;
                }

                for(int[] curDist : dist){
                    int nextY = curY+curDist[0];
                    int nextX = curX+curDist[1];

                    if(nextY>=N || nextY<0 || nextX>=M || nextX<0) continue;
                    if(map[nextY][nextX] == '#' || map[nextY][nextX] == 'F') continue;
                    if(visited[nextY][nextX]) continue;
                    personQ.add(new Person(nextY, nextX, curCount+1));
                }
            }
        }
        return -1;
    }
}