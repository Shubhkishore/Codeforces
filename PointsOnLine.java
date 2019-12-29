import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PointsOnLine {
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

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i;
		int t, n, d;
		n = in.nextInt();
		d = in.nextInt();
		long arr[] = new long[n];
		for (i = 0; i < n; i++)
			arr[i] = in.nextLong();
		long ans = 0, l, r;
		for (i = 0; i < n - 2; i++) {
			l = arr[i];
			t = search(i, l + d, arr);
			if (t <= i)
				continue;
			long x = t - i;
//			System.out.println(t + " " + x);
			ans += (x * (x - 1)) / 2;
//			System.out.println("ans: " + ans);
		}
		System.out.println(ans);
	}

	private static int search(int l, long d, long[] arr) {
		int i, n = arr.length - 1, m;
		int r = n;
//		System.out.println("in with: " + l);
		while (l < r) {
			m = (l + r) / 2;
			if (arr[m] == d)
				return m;
			else if (m - 1 >= 0 && arr[m - 1] <= d && arr[m] > d)
				return m - 1;
			else if (arr[m] < d && m + 1 <= n && arr[m + 1] > d)
				return m;
			else if (arr[m] > d)
				r = m;
			else if (arr[m] < d)
				l = m + 1;
//			System.out.println(l + " " + m + " " + r);
		}
		return l;
	}
}
