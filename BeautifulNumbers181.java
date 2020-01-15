import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BeautifulNumbers181 {
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

	static long fact[];

	static long power(long x, long y) {
		if (y == 0)
			return 1;
		long p = power(x, y / 2) % mod;
		p = (p * p) % mod;

		return (y % 2 == 0) ? p : (x * p) % mod;
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int a, b, i, t, n;
		long as, bs, sum, ans = 0;
		a = in.nextInt();
		b = in.nextInt();
		n = in.nextInt();
		fact = new long[n + 1];
		fact[1] = fact[0] = 1;
		for (i = 2; i <= n; i++) {
			fact[i] = (i * fact[i - 1]) % mod;
		}
//		System.out.println(Arrays.toString(fact));
		for (i = 0; i <= n; i++) {
			as = i;
			bs = n - i;
			sum = (as * a) + (bs * b);
			if (check(sum, a, b)) {
				ans += nCr(n, i);
				ans = ans % mod;
			}
		}
		System.out.println(ans);
	}

	private static long nCr(int n, int i) {
		long num = fact[n];
		long den = (fact[n - i] * fact[i]) % mod;
		return (num * power(den, mod - 2)) % mod;
	}

	private static boolean check(long sum, int a, int b) {
		boolean flag = true;
		while (sum > 0) {
			if (sum % 10 == a || sum % 10 == b) {
				sum = sum / 10;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
