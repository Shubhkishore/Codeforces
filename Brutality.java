import java.util.Arrays;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class Brutality {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i, n, k, j;
		String s;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		int d[] = new int[n];
		for (i = 0; i < n; i++)
			d[i] = in.nextInt();
		in.nextLine();
		s = in.nextLine();
		s=s+" ";
		int c = 1;
		int cons[] = new int[n];
		int sum[] = new int[n];
		Arrays.fill(cons, 1);
		for (i = 1; i <= n; i++) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				c++;
			} else {
				cons[i - c] = c;
				c = 1;
			}
		}
		for(i=0;i<n-1;i++) {
			if(s.charAt(i)!=s.charAt(i+1))
				break;
		}
		if(i==(n-1))
			cons[0]=n;
	/*	for(i=0;i<n;i++) {
			System.out.print(cons[i]+" ");
		}*/
		long dmg = 0;
		for (i = 0; i < n; i++) {
			if (cons[i] <= k) {
				for (j = i; j < (i + cons[i]); j++) {
					dmg += d[j];
				}
			//	System.out.println(i+" "+cons[i]+" "+dmg);
			} else {
				Arrays.sort(d, i, i + cons[i]);
				for (j = (i + cons[i] - k); j < (i + cons[i]); j++) {
					dmg += d[j];
				}
				//System.out.println(i+" "+cons[i]+" "+dmg);
			}
			
			i = i + cons[i] - 1;
		}
		System.out.println(dmg);
	}

}
