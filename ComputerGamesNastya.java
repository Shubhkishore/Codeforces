import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class ComputerGamesNastya {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n,k;
		n = in.nextInt();
		k = in.nextInt();
		n--;k--;
		int s = (k<=n-k)?k:(n-k);
		int ans;
		if (s!=0)
			ans  = 1+2*(s+1)+2*s+(n-s)*3;
		else
			ans = 6 + (n-1-s)*3;
		System.out.println(ans);
	}

}
