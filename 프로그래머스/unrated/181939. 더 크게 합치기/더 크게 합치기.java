class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        String sa = Integer.toString(a);
        String sb = Integer.toString(b);
        answer = Math.max(Integer.parseInt(sa + sb), Integer.parseInt(sb + sa));
        return answer;
    }
}