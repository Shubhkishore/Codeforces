import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class TelephoneNumer {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int t,n, i;
		t = in.nextInt();
		while(t-->0) {
			n = in.nextInt();
			in.nextLine();
			String s = in.nextLine();
			if(n<11) {
				System.out.println("NO");
				continue;
			}
			else if(n==11 && s.charAt(0)=='8') {
				System.out.println("YES");
				continue;
			}
			else if(n==11 && s.charAt(0)!='8') {
				System.out.println("NO");
				continue;
			}
			boolean f = false;
			for(i=0;i<=(n-11);i++) {
				if(s.charAt(i)=='8')
					{f = true;
					break;
					}
				}
			if(f)
				System.out.println("YES");
			else
				System.out.println("NO");
			}
		
		}
	}

