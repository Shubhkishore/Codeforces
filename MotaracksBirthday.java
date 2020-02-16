import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MotaracksBirthday {
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
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		while (t-- > 0) {
			n = in.nextInt();
			int arr[] = new int[n + 1];
			for (i = 1; i <= n; i++)
				arr[i] = in.nextInt();
//			arr[0] = -1;
			int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, diff = Integer.MIN_VALUE;
			for (i = 1; i <= n - 1; i++) {
				if (arr[i] == -1 && arr[i + 1] != -1) {
					min = arr[i + 1] < min ? arr[i + 1] : min;
					max = arr[i + 1] > max ? arr[i + 1] : max;
				} else if (arr[i] != -1 && arr[i + 1] == -1) {
					min = arr[i] < min ? arr[i] : min;
					max = arr[i] > max ? arr[i] : max;
				}
				if (arr[i] != -1 && arr[i + 1] != -1)
					diff = Math.max(Math.abs(arr[i] - arr[i + 1]), diff);
			}
//			System.out.println(min + " " + max);
			if (arr[n] == -1 && arr[n - 1] != -1) {
				min = arr[n - 1] < min ? arr[n - 1] : min;
				max = arr[n - 1] > max ? arr[n - 1] : max;
			} else if (arr[n] != -1 && arr[n - 1] == -1) {
				min = arr[n] < min ? arr[n] : min;
				max = arr[n] > max ? arr[n] : max;
			} else if (arr[n] != -1 && arr[n - 1] != -1) {
				diff = Math.max(Math.abs(arr[n] - arr[n - 1]), diff);
			}
//			System.out.println(diff);
			if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
				ans.append("0 96\n");
				continue;
			}
//			System.out.println(min + " " + max);
			int x = (min + max) / 2;
			if (diff == Integer.MAX_VALUE)
				diff = max - x;
			else
				diff = (Math.max(diff, max - x));
			ans.append(diff + " " + x + "\n");
		}
		System.out.println(ans);
	}
}
