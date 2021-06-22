import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        maxDp[0] = minDp[0] = a;
        maxDp[1] = minDp[1] = b;
        maxDp[2] = minDp[2] = c;

        for(int i=1;i<N;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            int tmp0 = maxDp[0];
            int tmp1 = maxDp[1];
            int tmp2 = maxDp[2];

            maxDp[0] = Math.max(tmp0, tmp1) + a;
            maxDp[1] = Math.max(tmp0, Math.max(tmp1, tmp2)) + b;
            maxDp[2] = Math.max(tmp2, tmp1) + c;

            tmp0 = minDp[0];
            tmp1 = minDp[1];
            tmp2 = minDp[2];

            minDp[0] = Math.min(tmp0, tmp1) + a;
            minDp[1] = Math.min(tmp0, Math.min(tmp1, tmp2)) + b;
            minDp[2] = Math.min(tmp2, tmp1) + c;
        }
        int max = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int min = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));
        bw.write(max+" "+min+"\n");
        br.close();
        bw.close();
    }
}