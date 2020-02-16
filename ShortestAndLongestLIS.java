import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ShortestAndLongestLIS {
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
			byte[] buf = new byte[500005]; // line length
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
		String s;
		while (t-- > 0) {
			n = in.nextInt();
			s = in.readLine();
			s = s.substring(0, n - 1);
			int[] sLis = new int[n];
			int[] nLis = new int[n];
			int x = 0, l, r;
			for (i = 0; i < n - 1; i++) {
				if (s.charAt(i) == '<')
					x++;
			}
			if (s.charAt(n - 2) == '<')
				s = s + ">";
			else {
				s = s + "<";
				x++;
			}
			l = n;
			r = x;
			for (i = 0; i < n; i++) {
				if (s.charAt(i) == '>') {
					sLis[i] = l--;
					ans.append(sLis[i] + " ");
				} else {
					int y = 0, j = i, z = r;
					while (j < n && s.charAt(j) == '<') {
						y++;
						j++;
					}
					z = r + 1 - y;
					j = i;
					while (j < n && s.charAt(j) == '<') {
						sLis[j] = z++;
						ans.append(sLis[j] + " ");
						j++;
					}
					r = r - y;
					i = j - 1;
				}
			}
			ans.append("\n");
			l = n;
			r = 1;
			for (i = 0; i < n; i++) {
				if (s.charAt(i) == '>')
					nLis[i] = l--;
				else
					nLis[i] = r++;
				ans.append(nLis[i] + " ");
			}
			ans.append("\n");
		}
		System.out.println(ans);
	}
}
