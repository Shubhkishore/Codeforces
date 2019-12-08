import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class ArrayKColoring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, k, i,distinctColor=1,flag=0;
		n = in.nextInt();
		k = in.nextInt();
		int arr[] = new int[n];
		Map<Integer, Integer> colorCount = new HashMap<Integer, Integer>();
		Map<Integer, Integer> currColor = new HashMap<Integer, Integer>();
		for (i = 0; i < n; i++) {
			arr[i] = in.nextInt();
			if (colorCount.get(arr[i]) == null) {
				if(distinctColor>k)
					{
						flag=1;
						distinctColor=1;
					}
				colorCount.put(arr[i], 1);
				currColor.put(arr[i], distinctColor);
				if(flag==0)
					distinctColor++;
			} else
				colorCount.put(arr[i], colorCount.get(arr[i]) + 1);
			if (colorCount.get(arr[i]) > k) {
				System.out.println("NO");
				return;
			}
		}
		// System.out.println(colorCount + "\n" + currColor);
		int output[] = new int[n];
		int color;
		for (i = 0; i < n; i++) {
			color = currColor.get(arr[i]);
			if(color==k)
				currColor.put(arr[i], 1);
			else
				currColor.put(arr[i], currColor.get(arr[i]) + 1);
			output[i] = color;
		}
		System.out.println("YES");
		for (i = 0; i < n; i++) {
			System.out.print(output[i] + " ");
		}
	}

}
