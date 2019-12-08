import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


/**
 * @author johnny16
 *
 */
public class PaintingTheFence {

	/**
	 * @param args
	 */
	static class info{
		int l,r,size;
	}
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
		int i, j, n, q;
		n = in.nextInt();
		q = in.nextInt();
		List<IntIntPair> sec = new ArrayList<>();
		for (i = 0; i < q; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			sec.add(new IntIntPair(x, y));
		}
		Collections.sort(sec);
		int l=-1,r=-1;
		for(i=0;i<q;i++) {
			for(j=i;j<q;j++) {
				
			}
		}
	}
}
