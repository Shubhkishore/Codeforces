import java.util.Scanner;


/**
 * @author johnny16
 *
 */
public class IntegerSequenceDividing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n;
		n = in.nextInt();
		int mod = n%4;
		if(mod==1 || mod==2)
			System.out.println("1");
		else
			System.out.println("0");
	}

}
