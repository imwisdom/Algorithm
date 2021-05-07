import java.io.IOException;
import java.util.*;

public class Main {

    public static class Pair{
        int u;
        int v;
        public Pair(int u, int v){
            this.u = u;
            this.v = v;
        }
    }
    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();

        int max = 0;
        int caseN = 0;
        Queue<Pair> Q = new LinkedList();
        while(a>=0 && b>=0){
            if(a==0 && b==0){
                int[] numOfParent = new int[max+1];
                int lineN = Q.size();
                caseN++;
                if(lineN == 0){
                    System.out.println("Case "+caseN+" is a tree.");
                    a = scan.nextInt();
                    b = scan.nextInt();
                    continue;
                }
                Set<Integer> set = new HashSet();
                while(!Q.isEmpty()){
                    Pair p = Q.poll();
                    numOfParent[p.u] = Math.max(0, numOfParent[p.u]);
                    numOfParent[p.v] = Math.max(0, numOfParent[p.v])+1;
                    set.add(p.u); set.add(p.v);
                }
                boolean isTrue = true;
                int root = -1;
                for(int i : set){
                    if(numOfParent[i]==0){
                        if(root!=-1){isTrue = false; break;}
                        else root = i;
                    }
                    else if(numOfParent[i]>1){isTrue = false; break;}
                }
                if(lineN != set.size()-1) isTrue = false;
                if(isTrue){
                    System.out.println("Case "+caseN+" is a tree.");
                }
                else{
                    System.out.println("Case "+caseN+" is not a tree.");
                }
                Q = new LinkedList();
                max = 0;

            }else{
                max = Math.max(a, Math.max(max, b));
                Q.add(new Pair(a, b));
            }
            a = scan.nextInt();
            b = scan.nextInt();
        }
    }


}