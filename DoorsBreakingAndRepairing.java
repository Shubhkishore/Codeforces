import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class DoorsBreakingAndRepairing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, x, y, i, oneStrides=0;
		n = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		int arr[] = new int[n];
		for (i = 0; i < n; i++) {
			arr[i] = in.nextInt();
			if(arr[i]<=x)
				oneStrides++;
		}
		if(x>y)
			System.out.println(n);
		else
			System.out.println((int)Math.ceil(1.0*oneStrides/2));
	}

}
