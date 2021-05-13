import java.util.Scanner;

public class era {
    static boolean[] isPrime;
    static int[] ary;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tc = scanner.nextInt();
        ary = new int[tc+1];
        isPrime = new boolean[tc+1];

        for(int i = 1; i < tc; i++){
            ary[i] = i;
            isPrime[i] = true;
        }

        for(int i = 2; i < tc+1; i++){
            if(!isPrime[i]) continue;
            for(int j = i + i; j < tc+1; j += i){
                isPrime[i] = false;
            }
        }
    }
}
