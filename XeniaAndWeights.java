import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class XeniaAndWeights {
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

	static int dp[][][];

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, m, k = 0;

		String s = in.readLine();
		m = in.nextInt();

		dp = new int[11][11][1001];
		int ans[] = new int[1001];
		int a = build(0, 0, m, ans, s, 0);

		System.out.println("NO");
	}

	private static int build(int diff, int last, int steps, int[] ans, String s, int pos) {
		// TODO Auto-generated method stub
		if (dp[diff][last][steps] != 0)
			return 1;

		if (steps == 0) {
			System.out.println("YES");
			for (int i = 0; ans[i] != 0; i++)
				System.out.print(ans[i] + " ");
			System.exit(0);
		} else {
			dp[diff][last][steps] = 1;
			for (int i = 0; i < 10; i++) {
				if (s.charAt(i) == '1' && last != (i + 1) && (i + 1) > diff) {
					ans[pos] = i + 1;
					build((i + 1) - diff, i + 1, steps - 1, ans, s, pos + 1);
				}
			}

		}
		return 0;
	}
}
