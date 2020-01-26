import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CreatingtheContest {
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
		int l, m, h, i, t, n, ans = 1;
		n = in.nextInt();
		int arr[] = new int[n];
		for (i = 0; i < n; i++)
			arr[i] = in.nextInt();
		int diff[] = new int[n - 1];
		Arrays.fill(diff, -1);
		for (i = 0; i < n - 1; i++) {
//			l = i;
//			h = n - 1;
//			t = 2 * arr[i];
//			m = (l + h) / 2;
//			while (l < h) {
//				m = (l + h) / 2;
//				if (m - 1 >= 0 && arr[m - 1] <= t && arr[m] > t) {
//					m--;
//					break;
//				}
//				if (arr[m] == t || (arr[m] <= t && m + 1 < n && arr[m + 1] > t))
//					break;
//				else if (arr[m] > t)
//					h = m - 1;
//				else
//					l = m + 1;
//			}
//			System.out.println(m + " " + i);
//			ans = Math.max(ans, m - i + 1);
			if (arr[i + 1] <= 2 * arr[i])
				diff[i] = 1;
		}
		for (i = 0; i < n - 1; i++) {
			t = 1;
			int j = i;
			while (j < n - 1 && diff[j] > 0) {
				j++;
			}
			ans = Math.max(ans, j - i + 1);
			if (diff[i] > 0)
				i = j;
		}
		System.out.println(ans);
	}
}
