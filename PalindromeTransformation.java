import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class PalindromeTransformation {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, p, l, r;
		n = in.nextInt();
		p = in.nextInt();
		in.nextLine();
		long ans = 0;
		String s = in.nextLine();
		char arr[] = s.toCharArray();
		int i;
		n--;
		p--;
		ans = 0;
		if (p > (n / 2)) {
			p = n - p;
		}
		r = p;
		for (i = p; i <= n / 2; i++) {
			if (s.charAt(i) != s.charAt(n - i))
				r = i;
		}
		l = p;
		for (i = p - 1; i >= 0; i--) {
			if (s.charAt(i) != s.charAt(n - i))
				l = i;
		}
	//	System.out.println(l+" "+r);
		if (l == r) {
			if (s.charAt(l) == s.charAt(n - l))
				System.out.println("0");
			else {
				char a = s.charAt(l), b = s.charAt(n - l);
				int max = a > b ? a : b;
				int min = a < b ? a : b;
				int x = (int) Math.abs(max - min) < (min - 97 + 122 - max)+1 ? (int) Math.abs(max - min)
						: (min - 97 + 122 - max)+1;
				System.out.println(x);
			}
			return;
		}
		ans = -1;
		int mdir = (r - p) < (p - l) ? r : l;
		if (mdir == r) {
			for (i = p; i <= r; i++) {
				ans++;
				char a = arr[i], b = arr[n-i];
				int max = a > b ? a : b;
				int min = a < b ? a : b;
				int x = (int) Math.abs(max - min) < (min - 97 + 122 - max)+1 ? (int) Math.abs(max - min)
						: (min - 97 + 122 - max)+1;
				if (a != b) { 
					arr[n-i]=arr[i];
					ans+=x;	
			//		System.out.println(i+" "+a+" "+ans+" "+x);
				}
			}
			for(i=r-1;i>=l;i--) {
				ans++;
				char a = arr[i], b = arr[n-i];
				int max = a > b ? a : b;
				int min = a < b ? a : b;
				int x = (int) Math.abs(max - min) < (min - 97 + 122 - max)+1 ? (int) Math.abs(max - min)
						: (min - 97 + 122 - max)+1;
				if (a != b) {
					arr[n-i]=arr[i];
					ans+=x;
				//	System.out.println(i+" "+a+" "+ans+" "+x);
				}
			}
		}
		else {
			for(i=p;i>=l;i--) {
				ans++;
				char a = arr[i], b = arr[n-i];
				int max = a > b ? a : b;
				int min = a < b ? a : b;
				int x = (int) Math.abs(max - min) < (min - 97 + 122 - max)+1 ? (int) Math.abs(max - min)
						: (min - 97 + 122 - max)+1;
				if (a != b) {
					ans+=x;
					arr[n-i]=arr[i];
				}
			}
			for (i = l+1; i <= r; i++) {
				ans++;
				char a = arr[i], b = arr[n-i];
				int max = a > b ? a : b;
				int min = a < b ? a : b;
				int x = (int) Math.abs(max - min) < (min - 97 + 122 - max)+1 ? (int) Math.abs(max - min)
						: (min - 97 + 122 - max)+1;
				if (a != b) {
					ans+=x;
					arr[n-i]=arr[i];
				}
			}
		}
		System.out.println(ans);
	}

}
