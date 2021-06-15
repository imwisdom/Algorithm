import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static class Num{
        long cur;
        int numOfOper;
        public Num(long cur, int numOfOper){
            this.cur = cur;
            this.numOfOper = numOfOper;
        }
    }
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int A = scan.nextInt();
        int B = scan.nextInt();
        System.out.println(solution(A, B));
    }
    public static int solution(int A, int B){
        Queue<Num> Q = new LinkedList();
        Q.add(new Num(A, 1));
        HashSet<Long> set = new HashSet();
        while(!Q.isEmpty()){
            Num curNum = Q.poll();
            long cur = curNum.cur;
            int numOfOper = curNum.numOfOper;
            if(cur == B) return numOfOper;
            if(set.contains(cur)) continue;
            set.add(cur);

            long next = cur*2;
            if(!set.contains(next) && next<=B) Q.add(new Num(next, numOfOper+1));
            next = cur*10+1;
            if(!set.contains(next) && next<=B) Q.add(new Num(next, numOfOper+1));
        }
        return -1;
    }

}