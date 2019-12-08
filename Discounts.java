import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class Discounts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, m, i, j;
		n = in.nextInt();
		int cost[] = new int[n];
		for (i = 0; i < n; i++) {
			cost[i] = in.nextInt();
		}
		m = in.nextInt();
		int coup[] = new int[m];
		for (i = 0; i < m; i++) {
			coup[i] = in.nextInt();
		}
		Arrays.sort(cost);
		long dp[] = new long[n];
		dp[0] = cost[0];
		for (i = 1; i < n; i++)
			dp[i] = dp[i - 1] + cost[i];
		for (i = 0; i < m; i++) {
			System.out.print((dp[n-1]-cost[n-coup[i]]) +"\n");
		}

	}

}
