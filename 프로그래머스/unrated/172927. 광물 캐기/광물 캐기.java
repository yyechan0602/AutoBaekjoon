import java.util.*;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[][] sequence = new int[10][3];
        int index = -1;
        int length = minerals.length;
        length = Math.min(length, (picks[0] + picks[1] + picks[2]) * 5);
        
        for(int i=0; i<length;i++){
            if(i%5 == 0) { index++; }
            
            if(minerals[i].equals("diamond")){
                sequence[index][0]+= 25; // 돌
                sequence[index][1]+= 5; // 철
                sequence[index][2]+= 1; // 다이아
            }else if(minerals[i].equals("iron")){
                sequence[index][0]+= 5;
                sequence[index][1]+= 1;
                sequence[index][2]+= 1;
            }else{
                sequence[index][0]+= 1;
                sequence[index][1]+= 1;
                sequence[index][2]+= 1;
            }
        }
        
        for(int j=0; j<10;j++){
            int max = 0;
            int maxIndex = -1;
            for (int i = 0; i<10; i++){
                if(max <= sequence[i][0]){
                    maxIndex = i;
                    max = sequence[i][0];
                }
            }
            
            if(picks[0] != 0){
                answer += sequence[maxIndex][2];
                picks[0]--;
            }else if(picks[1] != 0){
                answer += sequence[maxIndex][1];
                picks[1]--;
            }else {
                answer += sequence[maxIndex][0];
                picks[2]--;
            }
            sequence[maxIndex][0] = 0;
        }
        
        
        return answer;
    }
}