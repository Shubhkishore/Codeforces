import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author johnny16
 *
 */
public class TeamsForming {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, i;
		n = in.nextInt();
		int[] skills = new int[n];
		for (i = 0; i < n; i++)
			skills[i] = in.nextInt();
		Arrays.sort(skills);
		int probsReqd = 0;
		for (i = 0; i < n; i = i + 2)
			probsReqd += skills[i + 1] - skills[i];
		System.out.println(probsReqd);
	}

}
