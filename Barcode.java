import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Barcode {
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
		int i, j, t, n, m, x, y;
		n = in.nextInt();
		m = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		in.readLine();
		StringBuilder ans = new StringBuilder();
		char[][] barcode = new char[n][m];
		int hashes[] = new int[m + 1];
		for (i = 0; i < n; i++) {
			String s = in.readLine();
			barcode[i] = s.substring(0, m).toCharArray();
			for (j = 0; j < m; j++)
				hashes[j + 1] += barcode[i][j] == '#' ? 1 : 0;
		}
		long dp[][] = new long[2][m + 1];

		Arrays.fill(dp[0], Integer.MAX_VALUE);
		Arrays.fill(dp[1], Integer.MAX_VALUE);
		dp[0][0] = 0;
		dp[1][0] = 0;
		// dp[i][0]-> if last ith state '.'
		for (i = 1; i <= m; i++)
			hashes[i] += hashes[i - 1];
		dp[0][x] = hashes[x];
		dp[1][x] = n * x - hashes[x];
		for (i = 0; i <= m - x; i++) {
			for (j = i + x; j <= i + y && j <= m; j++) {
				dp[0][j] = Math.min(dp[1][i] + hashes[j] - hashes[i], dp[0][j]);
				dp[1][j] = Math.min(dp[0][i] + ((j - i) * n - (hashes[j] - hashes[i])), dp[1][j]);
			}
		}
//		System.out.println(Arrays.toString(hashes));
//		System.out.println(Arrays.toString(dp[0]) + "\n " + Arrays.toString(dp[1]));
		System.out.println(Math.min(dp[0][m], dp[1][m]));
	}
}
