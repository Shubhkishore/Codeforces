import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class RegularBracketSeq {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i,j,c1,c2,c3,c4;
		c1 = in.nextInt();
		c2 = in.nextInt();
		c3 = in.nextInt();
		c4 = in.nextInt();
		if(c3>0 && (c1==0 || c4==0 ))
				System.out.println("0");
		else if(c1!=c4)
			System.out.println("0");
		else
			System.out.println("1");
	}

}
