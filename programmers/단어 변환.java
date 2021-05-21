import java.util.*;
class Solution {
    public class Word{
        int wordNum;
        int sum;
        public Word(int wordNum, int sum){
            this.wordNum = wordNum;
            this.sum = sum;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int N = words.length;
        HashMap<Integer, String> map = new HashMap();
        for(int i=0;i<N;i++){
            map.put(i, words[i]);
        }
        boolean[][] isPossible = new boolean[N][N];
        
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(isPossible(map.get(i), map.get(j))){
                    isPossible[i][j] = isPossible[j][i] = true;
                }
            }
        }
        Queue<Word> Q = new LinkedList();
        for(int i=0;i<N;i++){
            if(isPossible(begin, map.get(i))) Q.add(new Word(i, 1));
        }
        boolean[] visited = new boolean[N];
        while(!Q.isEmpty()){
            Word curWord = Q.poll();
            int cur = curWord.wordNum;
            int sum = curWord.sum;

            if(visited[cur]) continue;
            visited[cur] = true;

            if(map.get(cur).equals(target)) return sum;
            
            for(int i=0;i<N;i++){
                if(isPossible[cur][i] && !visited[i]){
                    Q.add(new Word(i, sum+1));
                }
            }
        }
        
        return 0;
    }
    
    public boolean isPossible(String str1, String str2){
        boolean ret = false;
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i) != str2.charAt(i)){
                if(!ret) ret = true;
                else return false;
            }
        }
        return true;
    }
    
}