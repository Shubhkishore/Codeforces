import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class Boredom {

	/**
	 * @param args
	 */
	static final int maxn = 100005;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, i;
		n = in.nextInt();
		int a[] = new int[maxn];

		int max = 0;
		for (i = 0; i < n; i++) {
			a[i] = in.nextInt();
			if (max < a[i])
				max = a[i];
		}
		int count[] = new int[max + 1];
		for (i = 0; i < n; i++) {
			count[a[i]]++;
		}
		long dp[] = new long[max + 1];
		dp[1] = count[1];
		for (i = 2; i <= max; i++) {
			dp[i] = dp[i - 1];
			if (count[i] == 0)
				continue;

			dp[i] = Math.max(dp[i - 1], dp[i - 2] + count[i] * Long.valueOf(i));
		}
		System.out.println(dp[max]);
	}

}
