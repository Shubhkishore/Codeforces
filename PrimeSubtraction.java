import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class PrimeSubtraction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		long diff, x, y;
		int t;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		while (t-- > 0) {
			x = in.nextLong();
			y = in.nextLong();
			diff = x - y;
			int arr[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
					97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
					197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311,
					313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433,
					439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569,
					571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683,
					691, 701, 709, 719, 727, 733 };
			boolean flag = false;
			if (diff == 1) {
				ans.append("NO\n");
				continue;
			}
			for (int i = 0; i < arr.length; i++) {
				if (diff % arr[i] == 0) {
					flag = true;
					break;
				}
			}
			if (flag)
				ans.append("YES\n");
			else
				ans.append("NO\n");
		}
		System.out.println(ans);
	}

}
