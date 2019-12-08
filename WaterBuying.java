import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class WaterBuying {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		long n, q, i, a, b;
		q = in.nextLong();
		while (q-- > 0) {
			n = in.nextLong();
			a = in.nextLong();
			b = in.nextLong();
			long cost = 0;
			if (2 * a <= b) {
				cost = (long) n * a;
			} else {
				cost = (long) (n / 2 * b) + (n % 2) * a;
			}
			System.out.println(cost);
		}
	}

}
