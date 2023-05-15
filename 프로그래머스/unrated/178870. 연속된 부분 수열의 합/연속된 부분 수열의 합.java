class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, sequence.length};
        int sum = 0;
        int j = 0;
        
        for(int i=0; i< sequence.length; i++){
            sum += sequence[i];
            
            while(sum > k){
                sum -= sequence[j++];
            }
            
            if(sum == k){
                if(answer[1] - answer[0]> i - j){
                    answer[1] = i;
                    answer[0] = j;
                }
            }
        }
        
        return answer;
    }
}