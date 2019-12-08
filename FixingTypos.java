import java.util.Scanner;

/**
 * @author johnny16
 * gives TLE 
 */
public class FixingTypos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i, j;
		String str = in.nextLine();
		StringBuilder s = new StringBuilder(str);
		for (i = 0; i < s.length(); i++) {
			j = i;
			while ((j + 1) < s.length() && s.charAt(j) == s.charAt(j + 1)) {
				j++;
			}
			if ((j - i) > 1) {
				s.replace(i + 2, j + 1, "");
//				j = i + 2;
//				while (j<s.length() && s.charAt(j) == s.charAt(i))
//					s.replace(j, j + 1, "");
				i = i + 1;
			}
		}
		for (i = 0; i < s.length() - 1; i++) {

//			if((j-i)>1) {
//				j=i+2;
//				while(s.charAt(j)==s.charAt(i))
//					s.replace(j, j+1, "");
//				if((i+3)<s.length() && s.charAt(i+2)==s.charAt(i+3))
//					{
//					s.replace(i+3, i+4, "");
//					i=i+2;
//					}
//				else
//					i=i+1;
//			}
//			else 
			if ((i + 3) < s.length() && s.charAt(i) == s.charAt(i + 1) && s.charAt(i + 2) == s.charAt(i + 3)) {
				s.replace(i + 3, i + 4, "");
				i = i + 2;
			} 

		}
		System.out.println(s);
	}

}
