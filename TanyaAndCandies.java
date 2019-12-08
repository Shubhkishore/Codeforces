import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class TanyaAndCandies {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, i;
		n = in.nextInt();
		int sum[] = new int[n];
		int a[] = new int[n];
		for (i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}
		if (n == 1) {
			System.out.println("1");
			return;
		}
		sum[0] = a[0];
		sum[1] = a[1];
		for (i = 2; i < n; i++) {
			sum[i] = a[i] + sum[i - 2];
			System.out.print(sum[i]+" ");
		}
		System.out.println();
		int e, o,se,so,ans=0;
		
		if (n % 2 == 0) {
			if((sum[n-2]-a[0])==sum[n-1])
				ans++;
			//if((sum[n-2]))
			for (i = 2; i < n; i++) {
				if (i % 2 == 1) {
					e = sum[i - 1];
					o = sum[i - 2];
					se = sum[n-2]-e;
					so = sum[n-1]-o-a[i];
					e+=so;
					o+=se;
				}
				else {
					o=sum[i-1];
					e = sum[i-2];
					se = sum[n-2]-e-a[i];
					so = sum[n-1]-o;
					e+=so;
					o+=se;
				}
				if(e==0)
					ans++;
			}
		} else {
			for (i = 2; i < n; i++) {
				if (i % 2 == 1) {
					e = sum[i - 1];
					o = sum[i - 2];
					se = sum[n-1]-e;
					so = sum[n-2]-o-a[i];
					e+=so;
					o+=se;
				}
				else {
					o=sum[i-1];
					e = sum[i-2];
					se = sum[n-1]-e-a[i];
					so = sum[n-2]-o;
					e+=so;
					o+=se;
				}
				if(e==0)
					ans++;
			}

		}
		System.out.println(ans);
	}

}
