import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class SalemAndSticks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i, n, m, sum = 0, j;
		n = in.nextInt();
		int arr[] = new int[n];
		for (i = 0; i < n; i++) {
			arr[i] = in.nextInt();
			sum += arr[i];
		}
		Arrays.sort(arr);
		ArrayList<Integer> a = new ArrayList<>();
		a.add(arr[0]);
		for (i = 1; i < n; i++) {
			if (arr[i] > arr[i - 1])
				a.add(arr[i]);
		}
		int cost, mincost = Integer.MAX_VALUE, t=sum/n;

		for (i = a.get(a.size() / 2 - 1) - 1; i <= a.get(a.size() / 2) + 1; i++) {
			cost = 0;
			for (j = 0; j < n; j++) {
				cost += (int) Math.min(Math.abs(i - arr[j]),
						Math.min(Math.abs(i - 1 - arr[j]), Math.abs(i + 1 - arr[j])));
			}
			if (cost < mincost) {
				mincost = cost;
				t = i;
			}
		}
		System.out.println(t+" "+mincost);

	}

}
