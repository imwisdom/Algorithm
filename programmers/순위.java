class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        boolean[][] adj = new boolean[n+1][n+1];
        for(int[] result : results){
            adj[result[0]][result[1]] = true;
        }
        for(int i=1;i<=n;i++){
            int win = searchWin(n, adj, i, new boolean[n+1]);
            int lose = searchLose(n, adj, i, new boolean[n+1]);
            if(win + lose == n-1) answer++;
        }
        return answer;
    }
    public int searchWin(int n, boolean[][] adj, int start, boolean[] visited){
        int sum = 0;
        for(int i=1;i<=n;i++){
            if(visited[i]) continue;
            if(adj[start][i]){
                visited[i] = true;
                sum += 1+searchWin(n, adj, i, visited);   
            }
        }
        return sum;
    }
    public int searchLose(int n, boolean[][] adj, int start, boolean[] visited){
        int sum = 0;
        
        for(int i=1;i<=n;i++){
            if(visited[i]) continue;
            if(adj[i][start]){
                visited[i] = true;
                sum += 1+searchLose(n, adj, i, visited);   
            }
        }
        return sum;
    }
}