import java.util.Arrays;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class HackingCypher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int x, i, j, n, a, b;
		a = in.nextInt();
		b = in.nextInt();
		n = s.length();
		long rema[] = new long[n];
		long remb[] = new long[n];
		rema[0] = Integer.parseInt(String.valueOf(s.charAt(0))) % a;
		remb[n - 1] = Integer.parseInt(String.valueOf(s.charAt(n - 1))) % b;
		x=1;
		for (i = 1; i < n; i++) {
			rema[i] = (rema[i - 1] * 10 + Long.parseLong(String.valueOf(s.charAt(i)))) % a;
			x = ((x % b) * 10) % b;
			remb[n - 1 - i] = ((Long.parseLong(String.valueOf(s.charAt(n - 1 - i)))) * x % b + remb[n - i]) % b;
		}
		//System.out.println(Arrays.toString(rema));
		//System.out.println(Arrays.toString(remb));
		for (i = 0; i < n - 1; i++) {
			if (rema[i] == 0 && remb[i + 1] == 0 && s.charAt(i + 1) != '0') {
				System.out.println("YES");
				System.out.println(s.substring(0, i+1));
				System.out.println(s.substring(i+1));
				return;
			}
		}
		System.out.println("NO");
	}

}
