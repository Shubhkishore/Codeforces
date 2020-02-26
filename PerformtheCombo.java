import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PerformtheCombo {
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
			byte[] buf = new byte[300005]; // line length
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
		int i, t, n, j, m;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		String s;
		char c;
		while (t-- > 0) {
			n = in.nextInt();
			m = in.nextInt();
			in.readLine();
			s = in.readLine();
			int[] p = new int[m];
			for (i = 0; i < m; i++)
				p[i] = in.nextInt() - 1;
			int[][] mis = new int[26][n];
			for (i = 0; i < n; i++) {
				c = s.charAt(i);
				mis[c - 'a'][i]++;
			}
			for (i = 0; i < 26; i++) {
				for (j = 1; j < n; j++)
					mis[i][j] += mis[i][j - 1];
			}
//			for (i = 0; i < 26; i++) {
//				for (j = 0; j < n; j++) {
//					System.out.print(mis[i][j] + " ");
//				}
//				System.out.println();
//			}
			int out[] = new int[26];
			for (i = 0; i < m; i++) {
				for (j = 0; j < 26; j++) {
					out[j] += mis[j][p[i]];
				}
			}
			for (i = 0; i < 26; i++) {
				out[i] += mis[i][n - 1];
			}
			for (i = 0; i < 26; i++)
				ans.append(out[i] + " ");
			ans.append("\n");
		}
		System.out.println(ans);
	}
}
