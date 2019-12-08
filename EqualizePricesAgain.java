import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class EqualizePricesAgain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int q,n;
		q = in.nextInt();
		StringBuilder ans  = new StringBuilder();
		while(q-->0) {
			n = in.nextInt();
			long sum = 0;
			int a[] = new int[n];
			for(int i=0;i<n;i++) {
				a[i] = in.nextInt();
				sum+=a[i];
			}
			//System.out.println(sum+" "+n+" "+Math.ceil(sum/(n*1.0)));
			long x = (long)Math.ceil(sum/(n*1.0));
			ans.append(x+"\n");
		}
		System.out.println(ans);
	}

}
