import java.util.*;

public class Main {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        System.out.println(solution(a, b, c)%c);

    }
    public static long solution(int a, int b, int c){
        if(b==1) return a%c;
        if(b%2==1){
            return ((solution(a, b-1, c))*a)%c;
        }
        else{
            long s = solution(a, b/2, c);
            return (s*s)%c;
        }
    }
}