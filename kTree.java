import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class kTree {

	/**
	 * @param args
	 */
	static final int mod = 1000000007;
//	static long n;
//	static long k;
//	static long d;
	static long ans;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, k, d;
		n = in.nextInt();
		k = in.nextInt();
		d = in.nextInt();
		ans = 0;
		long dp[][] = new long[n+1][2];
		dp[0][0]=1;
		dp[0][1]=0;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=k;j++) {
				if(i-j<0)
					break;
				if(j<d) {
					dp[i][0] = ((dp[i][0]%mod)+(dp[i-j][0])%mod)%mod;
					dp[i][1] = ((dp[i][1]%mod)+(dp[i-j][1])%mod)%mod;
				}
				else {
					dp[i][1] = ((dp[i][1]%mod)+(dp[i-j][0])%mod)%mod;
					dp[i][1] = ((dp[i][1]%mod)+(dp[i-j][1])%mod)%mod;
				}
			}
		}
		System.out.println((dp[(int) n][1]%mod));
		//recur(n, k, d, 0, false);
		//System.out.println(ans);
	}

//	private static void recur(long n, long k, long d, long curr, boolean state) {
//		// TODO Auto-generated method stub
//		
//		if (state == true && curr == n) {
//			ans = ((ans % mod) + 1) % mod;
//			return;
//		}
//		for (int i = 1; i <= k; i++) {
//			if ((curr + i) > n)
//				return;
////			if(state==false && (curr+i)>=n)
////				return;
//			if (i >= d) {
//				// System.out.println("aa "+curr+" "+i);
//				recur(n, k, d, curr + i, true);
//			} else {
//				// System.out.println("bb "+curr+" "+i);
//				recur(n, k, d, curr + i, state);
//			}
//		}
//	}

}
