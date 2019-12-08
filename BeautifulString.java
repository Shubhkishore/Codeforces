import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BeautifulString {
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
		in.readLine();
		String str;
		char c, d;
		boolean flag;
		StringBuilder ans = new StringBuilder();
		outer: while (t-- > 0) {

			str = in.readLine();
			n = str.length() - 1;
			char s[] = str.substring(0, n).toCharArray();
			flag = false;
//			System.out.println(String.valueOf(s));
			for (i = 1; i < n; i++) {
				if (s[i] == 'a' && s[i - 1] == 'a') {
					ans.append("-1\n");
					continue outer;
				}
				if (s[i] == 'b' && s[i - 1] == 'b') {
					ans.append("-1\n");
					continue outer;
				}
				if (s[i] == 'c' && s[i - 1] == 'c') {
					ans.append("-1\n");
					continue outer;
				}
			}
			if (n == 1 && s[0] == '?') {
				s[0] = 'a';
				continue;
			}
			if (n >= 2 && s[0] == '?' && s[1] == '?')
				s[0] = 'a';
			else if (n >= 2 && s[0] == '?') {
				s[0] = s[1] == 'a' ? 'b' : 'a';
			}
			for (i = 1; i < n - 1; i++) {
				if (s[i] == '?' && s[i + 1] == '?')
					s[i] = s[i - 1] == 'a' ? 'b' : 'a';
				else if (s[i] == '?') {
//					System.out.println("asds");
					if (s[i - 1] == 'a')
						s[i] = (char) ((97 + 98 + 99 - s[i - 1] - s[i + 1]) % 100);
				}
			}
			if (s[n - 1] == '?') {
				s[n - 1] = s[n - 2] == 'a' ? 'b' : 'a';
			}
			ans.append(String.valueOf(s) + "\n");
		}
		System.out.println(ans);
	}
}
