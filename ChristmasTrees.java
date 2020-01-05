import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class ChristmasTrees {
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
		int i, t, n, m;
		n = in.nextInt();
		m = in.nextInt();
		Set<Integer> people = new HashSet<>();
		Set<Integer> occupied = new HashSet<>();
		StringBuilder ans = new StringBuilder();
		int arr[] = new int[n];
		long d = 0;
		PriorityQueue<IntIntPair> queue = new PriorityQueue<>(Comparator.comparing(pair -> pair.first));
		for (i = 0; i < n; i++) {
			arr[i] = in.nextInt();
			occupied.add(arr[i]);
			queue.add(IntIntPair.makePair(1, arr[i] + 1));
			queue.add(IntIntPair.makePair(1, arr[i] - 1));
		}
		int placed = 0;
		while (people.size() < m) {
			IntIntPair curr = queue.poll();
			if (!occupied.contains(curr.second)) {
				d += curr.first;
				occupied.add(curr.second);
				people.add(curr.second);
				queue.add(IntIntPair.makePair(curr.first + 1, curr.second + 1));
				queue.add(IntIntPair.makePair(curr.first + 1, curr.second - 1));
			}
		}
		ans.append(d + "\n");
		for (int h : people) {
			ans.append(h + " ");
		}
		System.out.println(ans);
	}
}
