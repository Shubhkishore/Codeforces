import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TwoArrays {
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

	static long power(long x, long y) {
		if (y == 0)
			return 1;
		long p = power(x, y / 2) % mod;
		p = (p * p) % mod;

		return (y % 2 == 0) ? p : (x * p) % mod;
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		long i, m, n, ans = 1, div = 1;
		n = in.nextLong();
		m = in.nextLong();
		n = n + 2 * m - 1;
		m = 2 * m;
		// apply ncr now
		for (i = n; i >= n - m + 1; i--)
			ans = (ans * i) % mod;
		for (i = 1; i <= m; i++)
			div = (div * i) % mod;
		ans = (ans * power(div, mod - 2)) % mod; // fermat's little theorem
		System.out.println(ans);
	}

//	private static void recur(int[] a, int[] b, int pos, int n, int m, int preva, int prevb) {
//		if (pos == m) {
//			ans = (ans % mod + 1) % mod;
////			System.out.println(Arrays.toString(a) + "," + Arrays.toString(b));
//			return;
//		}
//		for (int i = preva; i <= n; i++) {
//			a[pos] = i;
//			if (a[pos] < a[pos - 1])
//				return;
//			for (int j = prevb; j >= 1; j--) {
//				b[pos] = j;
//				if (b[pos] > b[pos - 1])
//					return;
//				if (b[pos] >= a[pos])
//					recur(a, b, pos + 1, n, m, i, j);
//			}
//		}
//	}
}
