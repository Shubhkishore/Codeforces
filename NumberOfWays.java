import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class NumberOfWays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, i;
		long s,sum=0, total=0;
		n = in.nextInt();
		int a[] = new int[n];
		int cnt[] = new int[n];
		for (i = 0; i < n; i++) {
			a[i] = in.nextInt();
			total+=a[i];
		}
		if(total%3!=0)
		{
			System.out.println("0");
			return;
		}
		s= total/3;
		sum=a[n-1];
		if(sum==s)
			cnt[n-1]++;
		
		for(i=n-2;i>=0;i--)
		{	
			sum+=a[i];
			if(sum==s)
				cnt[i]++;
			cnt[i] = cnt[i]+cnt[i+1];
		}
		
		int s1;
		long ans=0;
		sum=0;
		for(i=0;i<n-2;i++) {
			sum+=a[i];
			if(sum==s) {
				ans+=cnt[i+2];
			}
		}
		System.out.println(ans);
	}

}
