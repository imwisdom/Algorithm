import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] input = new int[N];
        for(int i=0;i<N;i++) input[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(input);

        int left = 0;
        int right = N-1;

        int curSum = Integer.MAX_VALUE;
        int ansLeft = input[left];
        int ansRight = input[right];
        while(left<right){
            int sum = input[left]+input[right];

            if(curSum>Math.abs(sum)){
                curSum = Math.abs(sum);
                ansLeft = input[left];
                ansRight = input[right];
            }
            if(sum<0){
                left++;
            }
            else if(sum>0){
                right--;
            }
            else break;
        }
        System.out.println(ansLeft+" "+ansRight);
    }
}