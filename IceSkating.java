import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IceSkating {
	static int mod = 1000000007;

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[10005]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

	static class UnionFind {

		private int count; // num of components
		private int id[]; // id[i] = root of i
		private int rank[]; // rank[i] = size of subtree rooted at i (cant be more than 31)
		private int numNodes[];

		UnionFind(int n) {
			count = n;
			id = new int[n];
			rank = new int[n];
			numNodes = new int[n];
			int i;
			for (i = 0; i < n; i++) {
				id[i] = i; // each tree is its own root
				rank[i] = 0;
				numNodes[i] = 1;
			}
		}

		/**
		 * 
		 * @param p
		 * @return the root of the parameter p
		 */
		int root(int p) {
			while (p != id[p]) {
				id[p] = id[id[p]]; // path compression
				p = id[p];
			}
			return p;
		}

		/**
		 * 
		 * @param a
		 * @param b
		 * @return true if a and b belong to same subtree
		 */
		boolean find(int a, int b) {
			if (root(a) == root(b))
				return true;
			return false;
		}

		/**
		 * union of subtree a with b according to rank
		 * 
		 * @param a
		 * @param b
		 */
		void union(int a, int b) {
			int root_a = root(a);
			int root_b = root(b);
			if (root_a == root_b)
				return;
			if (rank[root_a] > rank[root_b]) {
				id[root_b] = root_a;
				numNodes[root_a] += numNodes[root_b];
			} else if (rank[root_b] > rank[root_a]) {
				id[root_a] = root_b;
				numNodes[root_b] += numNodes[root_a];
			} else {
				id[root_a] = root_b;
				numNodes[root_b] += numNodes[root_a];
				rank[root_b]++;
			}

			count--;
		}

		/**
		 * 
		 * @return number of components in tree
		 */
		int componentSize(int a) {
			int root_a = root(a);
			return numNodes[root_a];
		}

		int numOfComponents() {
			return count;
		}

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

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, x, y;
		n = in.nextInt();
		IntIntPair arr[] = new IntIntPair[n];
		Map<Integer, List<Integer>> xmp = new HashMap<>();
		Map<Integer, List<Integer>> ymp = new HashMap<>();
		// List<Integer> temp;
		for (i = 0; i < n; i++) {
			x = in.nextInt();
			y = in.nextInt();
			arr[i] = new IntIntPair(x, y);
			if (xmp.containsKey(x) == false)
				xmp.put(x, new ArrayList<Integer>());
			xmp.get(x).add(i);
			if (ymp.containsKey(y) == false)
				ymp.put(y, new ArrayList<Integer>());
			ymp.get(y).add(i);
		}
		if (n == 1) {
			System.out.println("0");
			System.exit(0);
		}
		UnionFind uf = new UnionFind(n);
		for (List<Integer> temp : xmp.values()) {
			for (i = 0; i < temp.size(); i++)
				uf.union(temp.get(0), temp.get(i));
		}
		for (List<Integer> temp : ymp.values()) {
			for (i = 0; i < temp.size(); i++)
				uf.union(temp.get(0), temp.get(i));
		}
		System.out.println(uf.numOfComponents() - 1);
	}
}
