import java.util.*;
import java.lang.*;

class Solution {
    static int[] start;
    
    static final int empty = 0;
    static final int wall = -1;
    static final int laver = 1;
    static final int end = 2;
    
    
    static int[][] field;
    
    static final int[] DR = {0, 1, 0 , -1};
    static final int[] DC = {1, 0 , -1, 0};
    
    public int solution(String[] maps) {
        int answer = 0;
        start = new int[2];
        
        field = new int[maps.length][maps[0].length()];
    
        
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[0].length();j++){
                if(maps[i].charAt(j)=='X'){
                    field[i][j] = wall;
                } else{
                    field[i][j] = empty;
                }
                
                if(maps[i].charAt(j)=='S'){
                    start[0] = i;
                    start[1] = j;
                }else if(maps[i].charAt(j)=='E'){
                    field[i][j] = end;
                }else if(maps[i].charAt(j)=='L'){
                    field[i][j] = laver;
                }
            }
        }
        
        answer = BFS(maps.length, maps[0].length());
        
        return answer;
    }
    
    public static int BFS(int r, int c){
        Queue<Integer[]> q = new LinkedList();
        boolean[][][] visited = new boolean[r][c][2];
        
        q.add(new Integer[]{start[0], start[1], 0, 0});
        
        while(!q.isEmpty()){
            Integer[] cur = q.poll();
            
            for(int i = 0; i<DR.length;i++){
                int nextR = cur[0] + DR[i];
                int nextC = cur[1] + DC[i];
                
                
                if(nextR<0||nextR>=r||nextC<0||nextC>=c||field[nextR][nextC]==wall)
                    continue;
                
                
                if(cur[2]==0 && field[nextR][nextC] == laver && !visited[nextR][nextC][0]) {
                    q.add(new Integer[]{nextR, nextC, 1, cur[3] + 1});
                    visited[nextR][nextC][0] = true;
                    visited[nextR][nextC][1] = true;
                    continue;
                }
                
                if(cur[2]==1 && field[nextR][nextC] == end ){
                    return cur[3] + 1;
                }
                
                if(cur[2]==0 && !visited[nextR][nextC][0]){
                    q.add(new Integer[]{nextR, nextC, 0, cur[3] + 1});
                    visited[nextR][nextC][0] = true;
                    continue;
                }
                
                if(cur[2]==1 && !visited[nextR][nextC][1]){
                    q.add(new Integer[]{nextR, nextC, 1, cur[3] + 1});
                    visited[nextR][nextC][1] = true;
                    continue;
                }
            }
        }
        
        return -1;
    }
}