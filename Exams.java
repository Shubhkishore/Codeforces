import java.util.Arrays;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class Exams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		int b[] = new int[n];
		int i, j;
		for (i = 0; i < n; i++) {
			a[i] = in.nextInt();
			b[i] = in.nextInt();
		}
		sort(a, 0, n - 1, b);
	//	System.out.println(Arrays.toString(a)+"\n"+Arrays.toString(b));
		int min = b[0];
		for (i = 1; i < n; i++) {
			if (a[i] >= min && b[i] >= min)
				min = b[i];
			else if (a[i] >= min)
				min = a[i];

		}
		System.out.println(min);
	}

	private static void sort(int[] a, int l, int r, int[] b) {
		// TODO Auto-generated method stub
		if (l < r) {
			int p = partition(a, l, r, b);
			sort(a, l, p - 1, b);
			sort(a, p + 1, r, b);
		}
	}

	private static int partition(int[] a, int l, int r, int[] b) {
		// TODO Auto-generated method stub
		int pivot = a[r];
		int i = l, temp, t;
		for (int j = l; j < r; j++) {
			if (a[j] < pivot) {
				temp = a[j];
				a[j] = a[i];
				a[i] = temp;
				t = b[j];
				b[j] = b[i];
				b[i] = t;
				i++;
			}
			if(a[j]==pivot) {
				if(b[j]<b[r]) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
					t = b[j];
					b[j] = b[i];
					b[i] = t;
					i++;
				}
			}
		}
		temp = a[i];
		a[i] = a[r];
		a[r] = temp;
		t = b[i];
		b[i] = b[r];
		b[r] = t;

		return i;
	}

}
