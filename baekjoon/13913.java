import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();
        solution(N, K);
    }
    public static void solution(int N, int K) throws IOException {
        int MAX_SIZE = 2*Math.max(N, K)+1;
        int[] prev = new int[MAX_SIZE];
        Arrays.fill(prev, -1);
        prev[N] = -2;   //-2 : 시작점을 표현

        Queue<Integer> Q = new LinkedList();
        Q.add(N);
        boolean[] visited = new boolean[MAX_SIZE];

        while(!Q.isEmpty()){
            int cur = Q.poll();

            if(visited[cur]) continue;
            visited[cur] = true;

            if(cur == K){
                print(cur, prev);
                return;
            }
            if(cur-1 >= 0 && prev[cur-1]==-1) {
                Q.add(cur-1);
                prev[cur-1] = cur;
            }
            if(cur+1 < MAX_SIZE && prev[cur+1]==-1) {
                Q.add(cur+1);
                prev[cur+1] = cur;
            }
            if(cur*2 < MAX_SIZE && prev[cur*2]==-1) {
                Q.add(cur*2);
                prev[cur*2] = cur;
            }
        }
    }
    public static void print(int cur, int[] prev) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tmp = cur;
        int sec = 0;
        List<Integer> outputList = new LinkedList();
        while(tmp>=0){
            outputList.add(tmp);
            tmp = prev[tmp];
            sec++;
        }
        bw.write((sec-1)+"\n");
        for(int i=outputList.size()-1;i>=0;i--){
            bw.write(outputList.get(i)+" ");
        }
        bw.close();
    }
}