import java.util.*;
class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> freq = new HashMap<>();
        
        int maxi = 0;
        
        for(int i = 0; i < tasks.length; i++){
            freq.put(tasks[i], (freq.getOrDefault(tasks[i], 0)) + 1);
            maxi = Math.max(maxi, freq.get(tasks[i]));
        }
        int cnt = 0;
        for(Map.Entry<Character, Integer>  e : freq.entrySet()){
            if(e.getValue() == maxi){
                cnt++;
            }
        }

        int x = (maxi-1)*n + cnt-1 + maxi;
        int total = tasks.length;
        return total > x ? total : x;

    }
}


public class TaskScheduler {
    
}
