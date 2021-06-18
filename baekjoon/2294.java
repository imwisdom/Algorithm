import java.util.*;

public class Main {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();

        int[] coins = new int[n];
        for(int i=0;i<n;i++){
            coins[i] = scan.nextInt();
        }
        int[] needCoins = new int[k+1];
        Arrays.fill(needCoins, Integer.MAX_VALUE);
        for(int c : coins){
            for(int i=1;c*i<=k;i++){
                needCoins[c*i] = Math.min(needCoins[c*i], i);
            }
        }
        for(int i=2;i<=k;i++){
            for(int j=i-1;j>=1;j--){
                if(needCoins[j]==Integer.MAX_VALUE || needCoins[i-j]==Integer.MAX_VALUE) continue;
                needCoins[i] = Math.min(needCoins[i], needCoins[j]+needCoins[i-j]);
            }
        }
        if(needCoins[k]==Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(needCoins[k]);
    }

}