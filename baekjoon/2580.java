import java.util.*;

public class Main {
    public static class Point{
        int y;
        int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static int[][] sudocu;
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        sudocu = new int[9][9];
        List<Point> nullList = new LinkedList();

        for(int i=0;i<9;i++){
            boolean[] visited = new boolean[10];
            for(int j=0;j<9;j++){
                sudocu[i][j] = scan.nextInt();
                if(sudocu[i][j]==0){
                    nullList.add(new Point(i, j));
                }else{
                    visited[sudocu[i][j]] = true;
                }
            }
        }

        updateSudocu(nullList, 0);

    }
    public static boolean updateSudocu(List<Point> nullList, int count){
        if(count==nullList.size()){
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    System.out.print(sudocu[i][j]+" ");
                }
                System.out.println();
            }
            return true;
        }else{
            Point curPoint = nullList.get(count);
            boolean[] visited = new boolean[10];
            for(int i=0;i<9;i++){
                visited[sudocu[curPoint.y][i]] = true;
            }
            for(int i=0;i<9;i++){
                visited[sudocu[i][curPoint.x]] = true;
            }
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    visited[sudocu[i+(curPoint.y/3)*3][j+(curPoint.x/3)*3]] = true;
                }
            }
            for(int i=1;i<10;i++){
                if(!visited[i]) {
                    sudocu[curPoint.y][curPoint.x] = i;
                    if(updateSudocu(nullList, count + 1)) return true;
                    sudocu[curPoint.y][curPoint.x] = 0;
                }
            }
            return false;
        }
    }

}