import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int[][] map = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = scan.nextInt();
            }
        }
        int[][] dp = new int[N][M]; //i줄에서 0부터 j컬럼까지의 합
        for(int i=0;i<N;i++){
            dp[i][0] += map[i][0];
        }
        for(int i=0;i<N;i++){
            for(int j=1;j<M;j++){
                dp[i][j] += (dp[i][j-1]+map[i][j]);
            }
        }
        int K = scan.nextInt();
        for(int i=0;i<K;i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            int d = scan.nextInt();
            System.out.println(solution(dp, a, b, c, d));
        }
    }
    public static int solution(int[][] dp, int a, int b, int c, int d){
        int sum = 0;
        if(b>=2){
            for(int i=a-1;i<c;i++){
                sum += (dp[i][d-1]-dp[i][b-2]);
            }
        }
        else{
            for(int i=a-1;i<c;i++){
                sum += dp[i][d-1];
            }
        }

        return sum;
    }

}