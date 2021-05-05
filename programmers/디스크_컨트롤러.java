import java.util.*;
class Solution {
    
    public int solution(int[][] jobs) {
    
        PriorityQueue<int[]> PQ = new PriorityQueue(new Comparator<int[]>(){
            @Override
            public int compare(int[] a1, int[] a2){
                return Integer.compare(a1[0], a2[0]);
            }
        });
        for(int[] job : jobs){
            PQ.add(job);
        }
        PriorityQueue<int[]> scheduleQ = new PriorityQueue(new Comparator<int[]>(){
            @Override
            public int compare(int[] a1, int[] a2){
                return Integer.compare(a1[1], a2[1]);
            }
        });
        
        int N = PQ.size();
        int time = 0;
        int answer = 0;
        
        while(!PQ.isEmpty() || !scheduleQ.isEmpty()){
            while(!PQ.isEmpty() && time>=PQ.peek()[0]){
                scheduleQ.add(PQ.poll());
            }
            if(scheduleQ.isEmpty()){
                time ++;
                continue;
            }
            if(!scheduleQ.isEmpty()){
                int[] curArr = scheduleQ.poll();
                answer = answer + (time-curArr[0]) + curArr[1];
                time = time + curArr[1];
            }
        }
        answer = answer/N;
        return answer;
    }
}