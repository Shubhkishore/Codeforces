import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class IvanTheFoolAndTheProbabilityTheory {
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

	static long ans;

	static long power(long x, long y) {
		// Initialize result
		long res = 1;

		// Update x if it is more
		// than or equal to p
		x = x % mod;

		while (y > 0) {
			// If y is odd, multiply x
			// with result
			if ((y & 1) == 1)
				res = (res * x) % mod;

			// y must be even now
			// y = y / 2
			y = y >> 1;
			x = (x * x) % mod;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, n, m;
		n = in.nextInt();
		m = in.nextInt();
		ans = 0;
		// build(0,0,n-,m)
//		if (m == 1) {
//			ans = power(2, n / 2 + 1);
//			System.out.println(ans);
//			System.exit(0);
//		}
		ans = power(2, m);
		// System.out.println(ans);
		for (i = 3; i <= m; i++)
			ans -= power(2, i - 2);
		// System.out.println(ans);
		if (n == 1) {
			System.out.println(ans);
			System.exit(0);
		}
		if (m == 1)
			ans = (ans % mod + power(2, n / 2)) % mod;
		else
			ans = (ans % mod + power(2, n / 2)) % mod;
		System.out.println(ans);
	}
}
