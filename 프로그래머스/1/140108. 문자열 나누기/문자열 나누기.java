class Solution {
    public int solution(String s) {
        int answer = 0;
        int xCount= 0;
        int nonCount= 0;
        char x = 'a';
        boolean flag = true;
        
        for(int i= 0; i<s.length(); i++){
            if(flag){
                flag = false;
                x = s.charAt(i);
                xCount = 1;
                nonCount = 0;
                continue;
            }
            
            if(s.charAt(i) == x){
                xCount+=1;
            }else{
                nonCount+=1;
            }
            
            if(xCount == nonCount){
                flag = true;
                answer += 1;
            }
        }
        
        if(!flag) answer+=1;
        
        return answer;
    }
}