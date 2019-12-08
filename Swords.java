import java.util.Arrays;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class Swords {

	/**
	 * @param args
	 */
	static int gcd(int a, int b) {
		// Everything divides 0
		while (a != 0 && b != 0) {
			if (a > b) {
				a %= b;
			} else {
				b %= a;
			}
		}
		return Math.max(a, b);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), i;
		int a[] = new int[n];
		long tsw = 0, max = 0;
		for (i = 0; i < n; i++) {
			a[i] = in.nextInt();
			max = max < a[i] ? a[i] : max;
		}
		int diff[] = new int[n - 1], min = Integer.MAX_VALUE;
		Arrays.sort(a);

		for (i = 0; i < n; i++) {
			tsw += (max - a[i]);
		}

		for (i = 0; i < n - 1; i++) {
			diff[i] = a[i + 1] - a[i];
		}
		if (n == 2) {
			System.out.println("1 " + diff[0]);
			System.exit(0);
		}
		int g = gcd(diff[0], diff[1]);
		for (i = 2; i < n - 1; i++) {
			g = gcd(g, diff[i]);
		}
		// System.out.println(tsw+" "+ min); //////// hAD TO USE GCD
//	    long aa,b;
//	    if(tsw/min < min)
//	    {
//	    	aa =  (tsw/min);
//	    	b = min;
//	    }
//	    else {
//	    	b = (tsw/min);
//	    	aa = min;
//	    }
		System.out.println(tsw / g + " " + g);
	}

}
