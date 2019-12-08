import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class DigitsSequenceDividing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i, q, n;
		String s;
		q = in.nextInt();
		for (i = 0; i < q; i++) {
			n = in.nextInt();
			in.nextLine();
			s = in.nextLine();
			if (n == 2) {
				if ((s.charAt(0) >= s.charAt(1))) {
					System.out.println("NO");
					continue;
				}

			}
			System.out.println("YES\n2\n"+s.charAt(0) + " " + s.substring(1));
		}
	}

}
