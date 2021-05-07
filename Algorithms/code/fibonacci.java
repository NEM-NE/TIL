import java.util.Scanner;

public class fibonacci {
    static int[] dp;

    // recurison
    static int fibo(int x){
        if(x <= 1) return 1;
        return fibo(x-1) + fibo(x-2);
    }

    //top-down
    static int fiboDP(int x){
        if(x <= 1) {
            dp[1] = 1;   
            return dp[1];
        }else if(dp[x] != 0){
            return dp[x];
        }else {
            dp[x] = fiboDP(x-1) + fiboDP(x-2);
        }
        return fiboDP(x-1) * x;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tc = scanner.nextInt();
        dp = new int[tc+1];

        fibo(tc);

        fiboDP(tc);

        // bottom-up
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= tc; i++){
            dp[i] = dp[i-2] + dp[i-1];
        }
    }
}
