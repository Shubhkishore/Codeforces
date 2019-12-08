import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class Remainder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i,p,n,x,y;
		n = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		in.nextLine();
		String num = in.nextLine();
		StringBuilder s = new StringBuilder(num);
		s = s.reverse();
		//System.out.println(num +" " + s);
		int count =0;
		//int num =n;
		for(i=0;i<x;i++) {
		//	p = (int) Math.pow(10, i);
			char r = s.charAt(i);
			if(y==i && r=='0' ) {
				count++;
				//System.out.print("asdas");
			}
			else if(y!=i && r=='1') {
			//	System.out.print("sad");
				count++;
			}
		//	System.out.println(s +" "+count);
		}
		System.out.println(count);
	}

}
