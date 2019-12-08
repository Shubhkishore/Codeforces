import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Bombs {
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
			byte[] buf = new byte[64]; // line length
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
			int value = Integer.compare(Math.abs(first), Math.abs(o.first));
			if (value != 0) {
				return value;
			}
			return Integer.compare(Math.abs(second), Math.abs(o.second));
		}

	}

	static int max = (int) (10e5 + 1);

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int x, y, i, n;
		n = in.nextInt();
		IntIntPair cod[] = new IntIntPair[n];
		StringBuilder ans = new StringBuilder();
		for (i = 0; i < n; i++) {
			x = in.nextInt();
			y = in.nextInt();
			cod[i] = new IntIntPair(x, y);
		}
		Arrays.sort(cod);
		// System.out.println(Arrays.toString(cod));
		long moves = 0;
		for (i = 0; i < n; i++) {
			x = cod[i].first;
			y = cod[i].second;
			if (x == 0 || y == 0)
				moves += 4;
			else
				moves += 6;
			if (x < 0)
				ans.append("1 " + Math.abs(x) + " L\n");
			else if (x > 0)
				ans.append("1 " + Math.abs(x) + " R\n");
			if (y < 0)
				ans.append("1 " + Math.abs(y) + " D\n");
			else if (y > 0)
				ans.append("1 " + Math.abs(y) + " U\n");
			ans.append("2\n");
			if (x < 0)
				ans.append("1 " + Math.abs(x) + " R\n");
			else if (x > 0)
				ans.append("1 " + Math.abs(x) + " L\n");
			if (y < 0)
				ans.append("1 " + Math.abs(y) + " U\n");
			else if (y > 0)
				ans.append("1 " + Math.abs(y) + " D\n");
			ans.append("3\n");

		}
		System.out.println(moves + "\n" + ans);
	}
}
