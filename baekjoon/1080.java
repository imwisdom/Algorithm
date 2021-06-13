import java.util.Scanner;

public class Main {

    static int n;
    static int m;
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        char[][] init = new char[n][m];
        char[][] complete = new char[n][m];
        for(int i=0;i<n;i++){
            String str = scan.next();
            for(int j=0;j<m;j++){
                init[i][j] = str.charAt(j);
            }
        }
        for(int i=0;i<n;i++){
            String str = scan.next();
            for(int j=0;j<m;j++){
                complete[i][j] = str.charAt(j);
            }
        }
        int answer = 0;

        for(int i=0;i<=n-3;i++){
            for(int j=0;j<=m-3;j++){
                if(init[i][j]!=complete[i][j]){
                    change(i, j, init);
                    answer++;
                }
            }
        }
        if(!check(init, complete)) answer = -1;
        System.out.println(answer);
    }
    public static void change(int startY, int startX, char[][] a){
        for(int i=startY;i<startY+3;i++){
            for(int j=startX;j<startX+3;j++){
                a[i][j] = a[i][j] == '1' ? '0' : '1';
            }
        }
    }
    public static boolean check(char[][] a, char[][] b){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(a[i][j]!=b[i][j]) return false;
            }
        }
        return true;
    }

}