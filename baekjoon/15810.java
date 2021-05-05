import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int[] timeOfStaff = new int[N];
        for(int i=0;i<N;i++){
            timeOfStaff[i] = scan.nextInt();
        }
        Arrays.sort(timeOfStaff);

        long left = 0L;
        long right = (long)timeOfStaff[0]*(long)M;

        while(left<=right){
            long mid = (left+right)/2;
            long rem = 0;
            for(int i=0;i<N;i++){
                rem += mid/(long)timeOfStaff[i];
            }
            if(rem<M){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        System.out.println(left);
    }
}