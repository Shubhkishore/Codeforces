import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class FixingTypos2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		if(s.length()<=2)
		{
			System.out.println(s);
			return;
		}
		StringBuilder ans = new StringBuilder();
		ans.append(s.substring(0, 2));
		int n;
		for(int i=2;i<s.length();i++) {
			n = ans.length();
			if(s.charAt(i)==ans.charAt(n-1) && ans.charAt(n-2)==ans.charAt(n-1)) {
				//System.out.println(i+" "+ans+" "+ans.charAt(n-1)+" "+ans.charAt(n-2)+" "+ans.charAt(n-3));
				continue;
			}
//			if(n-3>=0)
//			System.out.println(ans+" "+ans.charAt(n-2)+" "+ans.charAt(n-3));
			if((n-3)>=0 && s.charAt(i)==ans.charAt(n-1) && ans.charAt(n-2)==ans.charAt(n-3)) {
			//	System.out.println(i+" "+ans);
				continue;
			}
			ans.append(s.charAt(i));
		}
		System.out.println(ans);
	}

}
