import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class DistinctDigits {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int l,r;
		l = in.nextInt();
		r = in.nextInt();
		int i,x,m;
		boolean flag;
		for(i=l;i<=r;i++) {
			x=i;
			flag = false;
			boolean check[] = new boolean[10];
			for(int j=x;j>0;j=j/10) {
				m=j%10;
				if(check[m]==true) {
					flag = true;
					break;
				}
				else
					check[m]=true;
			}
			if(flag==false) {
				System.out.println(x);
				return;
			}
		}
		System.out.println("-1");
	}

}
