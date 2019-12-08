import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class ReadingABook {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n,k,i;
		n = in.nextInt();
		int l[] = new int[n];
		int r[] = new int[n];
		for(i=0;i<n;i++) {
			l[i] = in.nextInt();
			r[i] = in.nextInt();
		}
		
		k = in.nextInt();
		for(i=0;i<n;i++) {
			
			if(l[i]<=k && k<=r[i]) {
				System.out.println(n-i);
				return;
			}
		}
		
	}

}
