import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author johnny16
 *
 */
public class PalindromicMatrixMusin {

	/**
	 * @param args
	 */
	static class IntIntPair implements Comparable<IntIntPair> {
		public final int first;
		public final int second;

		public static IntIntPair makePair(int first, int second) {
			return new IntIntPair(first, second);
		}

		public IntIntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			IntIntPair pair = (IntIntPair) o;

			return first == pair.first && second == pair.second;
		}

		public int hashCode() {
			int result = first;
			result = 31 * result + second;
			return result;
		}

		public String toString() {
			return "(" + first + "," + second + ")";
		}

		public int compareTo(IntIntPair o) {
			int value = Integer.compare(first, o.first);
			if (value != 0) {
				return value;
			}
			return Integer.compare(second, o.second);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, i, j;
		n = in.nextInt();
		int a[] = new int[n * n];
		int count[] = new int[1001];
		for (i = 0; i < n * n; i++) {
			a[i] = in.nextInt();
			count[a[i]]++;
		}
		List<Set<IntIntPair>> groups = new ArrayList<>();
		boolean filled[][] = new boolean[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (!filled[i][j]) {
					int i1 = n - 1 - i;
					int j1 = n - 1 - j;
					Set<IntIntPair> group = new HashSet<>();
					group.add(IntIntPair.makePair(i, j));
					group.add(IntIntPair.makePair(i1, j));
					group.add(IntIntPair.makePair(i, j1));
					group.add(IntIntPair.makePair(i1, j1));
					groups.add(group);
					filled[i][j] = filled[i1][j] = filled[i][j1] = filled[i1][j1] = true;
				}
			}
		}
		groups.sort(Comparator.comparingInt(g -> -g.size()));

		int ans[][] = new int[n][n];
		for (Set<IntIntPair> group : groups) {
			boolean flag = false;
			for (i = 0; i < count.length; i++) {
				if (count[i] >= group.size()) {
					for (IntIntPair p : group) {
						ans[p.first][p.second] = i;
						count[i]--;
					}
					flag = true;
					break;
				}
			}
			if (!flag) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
	}

}
