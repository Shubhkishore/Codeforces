import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MaximalIntersection {
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
			int value = Math.abs(first - second) - Math.abs(o.first - o.second);
			return -value;
		}

	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, ans = 0;
		n = in.nextInt();
		int x[] = new int[n];
		int y[] = new int[n];
		for (i = 0; i < n; i++) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
		}
		int prl[] = new int[n + 1];
		int prr[] = new int[n + 1];
		int sul[] = new int[n + 1];
		int sur[] = new int[n + 1];

		prl[0] = x[0];
		sul[n] = 0;
		prr[0] = y[0];
		sur[n] = Integer.MAX_VALUE;

		for (i = 1; i < n; i++) {
			prl[i] = Math.max(x[i], prl[i - 1]);
			prr[i] = Math.min(y[i], prr[i - 1]);
		}
//		System.out.println(Arrays.toString(prl) + " " + Arrays.toString(prr));
		for (i = n - 1; i >= 0; i--) {
			sul[i] = Math.max(x[i], sul[i + 1]);
			sur[i] = Math.min(y[i], sur[i + 1]);
		}
//		System.out.println(Arrays.toString(sul) + " " + Arrays.toString(sur));
		for (i = 1; i < n; i++) {
			ans = Math.max(ans, Math.min(sur[i + 1], prr[i - 1]) - Math.max(prl[i - 1], sul[i + 1]));
		}
		System.out.println(Math.max(ans, sur[1] - sul[1]));
	}
}
