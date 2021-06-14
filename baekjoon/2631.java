import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] input = new int[N];
        for(int i=0;i<N;i++) input[i] = scan.nextInt();

        int[] dp = new int[N];
        dp[0] = 1;

        int ans = 0;
        for(int i=1;i<N;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(input[i]>input[j]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(N-ans);

    }
}