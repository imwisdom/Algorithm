import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int N;
    public static int M;
    public static int L;
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        L = scan.nextInt();

        int[] input = new int[M+2];
        input[0] = 0;
        for(int i=1;i<=M;i++) input[i] = scan.nextInt();
        input[M+1] = L;

        Arrays.sort(input);
        for(int i=0;i<N;i++){
            int cut = scan.nextInt();
            System.out.println(solution(input, cut));
        }
    }
    public static int solution(int[] input, int cut){
        int left = 0; int right = L;
        int answer = 0;
        while(left<=right){
            int mid = (left+right)/2;
            int prev = input[0];
            int numOfCut = 0;
            for(int i=1;i<=M+1;i++){
                if(input[i]-prev>=mid){
                    numOfCut++;
                    prev = input[i];
                }
            }
            if(numOfCut>cut){
                left = mid+1;
                answer = Math.max(answer, mid);
            }
            else right = mid-1;
        }
        return answer;
    }
}