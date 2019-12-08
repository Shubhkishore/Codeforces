import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class PalindromicMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, i, j, size;
		n = in.nextInt();
		size = n * n;
		int a[] = new int[n * n];
		Map<Integer, Integer> mp = new HashMap<>();
		for (i = 0; i < n * n; i++) {
			a[i] = in.nextInt();
			if (mp.containsKey(a[i]) == false)
				mp.put(a[i], 1);
			else
				mp.put(a[i], mp.get(a[i]) + 1);
		}
		if (n == 1) {
			System.out.println("YES");
			System.out.println(a[0]);
			return;
		}
		if (n == 2) {
			if (mp.entrySet().size() == 1) {
				System.out.println("YES");
				for (i = 0; i < 2; i++) {
					for (j = 0; j < 2; j++)
						System.out.print(a[0]);
					System.out.println();
				}
				return;
			} else {
				System.out.println("NO");
				return;
			}
		}
		int mat[][] = new int[n][n];
		Iterator it = mp.entrySet().iterator();
		int num[] = new int[n];
		if (n % 2 == 0) {
			i = 0;
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				if ((int) pair.getValue() % 4 != 0) {
					System.out.println("NO");
					return;
				} else {
					int x = (int) pair.getValue();
					while (x != 0) {
						num[i] = (int) pair.getKey();
						i++;
						x -= 4;
					}
				}
			}
			i = -1;
			int c = 0;
			for (j = 0; j < size / 4; j++) {
				if (j % (n / 2) == 0) {
					i++;
				}
				mat[i][j % (n / 2)] = num[c];
				mat[n - 1 - i][j % (n / 2)] = num[c];
				mat[i][n - 1 - j % (n / 2)] = num[c];
				mat[n - 1 - i][n - 1 - j % (n / 2)] = num[c];
				c++;
			}
		} else {
			int c = 0,x=-1;
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				if ((int) pair.getValue() % 4 != 0) {
					if ((int) pair.getValue() % 4 != 1) {
						System.out.println("NO");
						return;
					}
					else {
						c++;
						x=(int)pair.getKey();
					}
				}
			}
			if (c != 1) {
				System.out.println("NO");
				return;
			}
			
		}
		System.out.println("YES");
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(mat[i][j]);
			System.out.println();
		}
	}

}
