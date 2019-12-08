import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class DigitalRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i, n, x;
		long num,k;
		n = in.nextInt();
		for (i = 0; i < n; i++) {
			k = in.nextLong();
			x = in.nextInt();
			num = x + (k - 1) * 9;
			System.out.println(num);
		}
	}

}
