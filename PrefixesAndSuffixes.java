import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author johnny16
 *
 */
public class PrefixesAndSuffixes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, i;
		n = in.nextInt();
		in.nextLine();
		String a = new String();
		String b = new String();
		String[] ps = new String[2 * n - 2];
		for (i = 0; i < (2 * n - 2); i++) {
			ps[i] = in.nextLine();
			if (ps[i].length() == n - 1) {
				if (a.length() != 0)
					b = ps[i];
				else
					a = ps[i];
			}
		}
		if(n==2) {
			System.out.println("PS");
			return;
		}
		String s = new String();
		if (a.charAt(1) == b.charAt(0))
			s = a + b.charAt(n - 2);
		else
			s = b + a.charAt(n - 2);
		Map<String, String> map = new HashMap<String, String>();
		StringBuilder ans = new StringBuilder();
		for (i = 0; i < (2 * n - 2); i++) {
			if (map.get(ps[i]) == null) {
				if (s.indexOf(ps[i]) == 0) {
					ans.append("P");
					map.put(ps[i], "P");
				} else {
					ans.append("S");
					map.put(ps[i], "S");
				}
			}
			else if(map.get(ps[i])=="P") {
				ans.append("S");
			}
			else
				ans.append("P");
		}
		System.out.println(ans);
	}

}
