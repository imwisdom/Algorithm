import java.io.IOException;
import java.util.*;
 
public class Main {
 
    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
 
        String[] words = new String[a];
        for(int i=0;i<a;i++){
            words[i] = scan.next();
        }
        System.out.println(solution(words, new boolean[26], 0, 0, b));
    }
    public static int solution(String[] words, boolean[] alpha, int cur, int count, int b){
        if(count==b){
            int sum = words.length;
            for(int i=0;i<words.length;i++){
                String curWord = words[i];
                for(int j=0;j<curWord.length();j++){
                    if(!alpha[(int)curWord.charAt(j)-97]){
                        sum--; break;
                    }
                }
            }
            return sum;
        }else{
            if(cur==26) return 0;
            alpha[cur] = true;
            int case1 = solution(words, alpha, cur+1, count+1, b);
            alpha[cur] = false;
            int case2 = solution(words, alpha, cur+1, count, b);
            return Math.max(case1, case2);
        }
    }
 
 
}
