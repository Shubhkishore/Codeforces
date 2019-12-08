import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ToAddOrNotToAdd {
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

	static final int maxn = 100000;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int l, r, m, k, i, t, n;
		n = in.nextInt();
		k = in.nextInt();
		int arr[] = new int[n];
		long psum[] = new long[n];
		Map<Integer, Integer> mp = new HashMap<>();
		int max = 0;
		for (i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		Arrays.sort(arr);
		for (i = 0; i < n; i++) {
			if (mp.containsKey(arr[i]) == false)
				mp.put(arr[i], 0);
			mp.put(arr[i], mp.get(arr[i]) + 1);
			max = mp.get(arr[max]) < mp.get(arr[i]) ? i : max;
		}
//		System.out.println(mp + " " + max);
		if (k == 0) {
			System.out.println(mp.get(arr[max]) + " " + arr[max]);
			return;
		}
		psum[0] = arr[0];
		for (i = 1; i < n; i++) {
			psum[i] = psum[i - 1] + arr[i];
		}
		int min = Integer.MAX_VALUE, times = 0;
//		System.out.println(Arrays.toString(psum));
		for (i = 0; i < n; i++) {
			l = 0;
			r = m = i;
			long sum;
			while (l < r) {
				m = (l + r) / 2;
				long x, y;
				if (m - 1 < 0)
					x = (arr[i] * (i - m)) - (psum[i - 1]);
				else
					x = (arr[i] * (i - m)) - (psum[i - 1] - psum[m - 1]);
//				System.out.println(x + "      " + l + "  " + r);
				if (x > k && (arr[i] * (i - m - 1)) - (psum[i - 1] - psum[m]) <= k) {
					m = m + 1;
					break;
				}
				if (x == k)
					break;
				else if (x < k)
					r = m;
				else
					l = m;
			}
			if (i - m + 1 > times) {

				times = i - m + 1;
				min = arr[i];
//				System.out.println(arr[i] + " " + times);
			}
//			System.out.println(times + " " + min);
		}
		System.out.println(times + " " + min);
	}
}
