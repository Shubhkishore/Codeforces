import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SinglePush {
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
		// n = in.nextInt();
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		outer: while (t-- > 0) {
			n = in.nextInt();
			int a[] = new int[n];
			int b[] = new int[n];
			for (i = 0; i < n; i++)
				a[i] = in.nextInt();
			for (i = 0; i < n; i++)
				b[i] = in.nextInt();
			int diff[] = new int[n];
			Set<Integer> s = new HashSet<>();

			for (i = 0; i < n; i++) {
				diff[i] = b[i] - a[i];
				if (diff[i] < 0) {
					ans.append("NO\n");
					continue outer;
				}
				s.add(diff[i]);
			}
			// System.out.println(s);
			if (s.size() == 1)
				ans.append("YES\n");
			else if (s.size() >= 3)
				ans.append("NO\n");
			else {
				for (i = 0; i < n; i++) {
					if (diff[i] == 0)
						continue;
					else {
						// System.out.println(i);
						int x = diff[i];
						i = i + 1;
						boolean flag = true;
						while (i < n) {
							if (diff[i] == x)
								i++;
							else if (diff[i] == 0)
								break;
							else {
								flag = false;
								break;
							}
						}
						if (flag == false) {
							ans.append("NO\n");
							break;
						}
						while (i < n && diff[i] == 0)
							i++;
						if (i == n)
							ans.append("YES\n");
						else {
							ans.append("NO\n");
							break;
						}
					}
				}
			}
		}
		System.out.println(ans);
	}
}
