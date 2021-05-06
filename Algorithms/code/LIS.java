public class LIS {
    static int[] dp = new int[9];
    public static void main(String[] args) {
        int[] ary = new int[9];

        ary[0] = 2;
        ary[1] = 1;
        ary[2] = 5;
        ary[3] = 4;
        ary[4] = 3;
        ary[5] = 7;
        ary[6] = 6;
        ary[7] = 8;
        ary[8] = 9;

        dp[1] = 1;
        for(int i = 0; i < 9; i++){
            int a = ary[i];
            for(int j = 0; j < i; j++){
                if(a > ary[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            dp[i] = Math.max(dp[i-1], dp[i]);
        }

        int max = 0;
        for(int i = 0; i < dp.length; i++){
            if(max < dp[i]) max = dp[i];
        }

        System.out.println(max);
    }
}
