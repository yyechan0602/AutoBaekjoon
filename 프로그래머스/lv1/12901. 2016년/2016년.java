class Solution {
    public String solution(int a, int b) {
        int[] month = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] days = {"THU","FRI","SAT","SUN","MON","TUE","WED"};
        int date = b;
        
        for(int i=1; i<a; i++){
            date += month[i-1];
        }
        
        return days[date%7];
    }
}