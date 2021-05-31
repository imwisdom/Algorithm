import java.util.*;

public class Main {
    static int n;
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        boolean[][] friend = new boolean[n][n];
        int[] numOfFriend = new int[n];

        for(int i=0;i<n;i++){
            String str = scan.next();
            for(int j=0;j<n;j++){
                if(str.charAt(j)=='Y') friend[i][j] = friend[j][i] = true;
            }
        }

        for(int i=0;i<n;i++){
            Queue<Integer> friendQ = new LinkedList();
            boolean[] visited = new boolean[n];
            visited[i] = true;
            for(int j=0;j<n;j++){
                if(friend[i][j]){
                    numOfFriend[i]++;
                    friendQ.add(j);
                    visited[j] = true;
                }
            }
            while(!friendQ.isEmpty()){
                int f = friendQ.poll();
                for(int j=0;j<n;j++){
                    if(friend[f][j] && !visited[j]){
                        numOfFriend[i]++;
                        visited[j] = true;
                    }
                }
            }
        }
        int max = 0;
        for(int f : numOfFriend){
            max = Math.max(f, max);
        }
        System.out.println(max);
    }
}