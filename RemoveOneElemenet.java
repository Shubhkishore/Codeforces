import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class RemoveOneElemenet {
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
		int i, t, n;
		n = in.nextInt();
		int arr[] = new int[n];
		for (i = 0; i < n; i++)
			arr[i] = in.nextInt();
		int inc[] = new int[n];
		int dec[] = new int[n];
		inc[0] = 1;
		for (i = 1; i < n; i++) {
			if (arr[i] > arr[i - 1])
				inc[i] = 1 + inc[i - 1];
			else
				inc[i] = 1;
		}
		dec[n - 1] = 1;
		for (i = n - 2; i >= 0; i--) {
			if (arr[i] < arr[i + 1])
				dec[i] = 1 + dec[i + 1];
			else
				dec[i] = 1;
		}
		if (inc[n - 1] == n) {
			System.out.println(n);
			return;
		}
		int max = 1, x = 0;
		for (i = 1; i < n - 1; i++) {
			x = 0;
			max = max < inc[i] ? inc[i] : max;
			max = max < dec[i] ? dec[i] : max;
			if (arr[i - 1] < arr[i + 1]) {
				x += inc[i - 1];
				x += dec[i + 1];
			}
//			if (max < x)
//				System.out.println(i);
			max = max < x ? x : max;
		}
//		System.out.println(Arrays.toString(inc) + "\n" + Arrays.toString(dec));
		System.out.println(max);
	}
}
