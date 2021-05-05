import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        char[] init1 = new char[N];
        char[] init2 = new char[N];
        String str = scan.next();
        for(int i=0;i<N;i++){
            init1[i] = str.charAt(i);
            init2[i] = str.charAt(i);
        }

        char[] correct = new char[N];
        str = scan.next();
        for(int i=0;i<N;i++) correct[i] = str.charAt(i);

        int num1 = 0;
        int num2 = 0;

        init1[0] = switchChar(init1[0]);
        init1[1] = switchChar(init1[1]);
        num1++;

        for(int i=1;i<N-1;i++){
            if(correct[i-1]!=init1[i-1]){
                init1[i-1] = switchChar(init1[i-1]);
                init1[i] = switchChar(init1[i]);
                init1[i+1] = switchChar(init1[i+1]);
                num1++;
            }
            if(correct[i-1]!=init2[i-1]){
                init2[i-1] = switchChar(init2[i-1]);
                init2[i] = switchChar(init2[i]);
                init2[i+1] = switchChar(init2[i+1]);
                num2++;
            }
        }
        if(correct[N-2]!=init1[N-2]){
            init1[N-1] = switchChar(init1[N-1]);
            init1[N-2] = switchChar(init1[N-2]);
            num1++;
        }
        if(correct[N-2]!=init2[N-2]){
            init2[N-1] = switchChar(init2[N-1]);
            init2[N-2] = switchChar(init2[N-2]);
            num2++;
        }
        String init1Str = new String(init1);
        String init2Str = new String(init2);
        String correctStr = new String(correct);
        if(!init1Str.equals(correctStr)) num1 = Integer.MAX_VALUE;
        if(!init2Str.equals(correctStr)) num2 = Integer.MAX_VALUE;

        if(Math.min(num1, num2)==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(Math.min(num1, num2));

    }
    public static char switchChar(char a){
        if(a=='0') return '1';
        return '0';
    }
}