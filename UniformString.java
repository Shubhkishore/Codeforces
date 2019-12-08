
/**
 * @author johnny16
 *
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UniformString {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int i, t, n, k;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		while (t-- > 0) {
			n = in.nextInt();
			k = in.nextInt();
			int min_freq = n/k;
			StringBuilder s = new StringBuilder();
			char c = 'a';
			int numOfLetters = k;
			while((numOfLetters--)>0) {
				for(i=0;i<min_freq;i++) {
					s.append(c);
				}
				c++;
			}
			c--;
			while(s.length()!=n) {
				s.append(c);
			}
			ans.append(s+"\n");
		}
		System.out.println(ans);
	}
}