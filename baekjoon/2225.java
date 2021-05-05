import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] DP = new int[M+1][N+1];
        for(int i=0;i<=N;i++){
            DP[1][i] = 1;
        }
        for(int i=2;i<=M;i++){
            for(int j=0;j<=N;j++){
                for(int h=0;h<=j;h++){
                    DP[i][j] += DP[i-1][h]%1000000000;
                    DP[i][j] %= 1000000000;
                }
            }
        }
        System.out.println(DP[M][N]%1000000000);
    }

}