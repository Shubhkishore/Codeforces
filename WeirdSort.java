import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WeirdSort {
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
		int i, t, n, m, j;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		outer: while (t-- > 0) {
			n = in.nextInt();
			m = in.nextInt();
			int a[] = new int[n];
			int p[] = new int[m];
			for (i = 0; i < n; i++)
				a[i] = in.nextInt();
			Set<Integer> st = new HashSet<>();
			for (i = 0; i < m; i++) {
				p[i] = in.nextInt() - 1;
				st.add(p[i]);
			}
			Arrays.sort(p);
//			System.out.println(Arrays.toString(p));
			while (!check(a)) {
				for (i = 0; i < n - 1; i++) {
					if (a[i] > a[i + 1] && st.contains(i) == false) {
						ans.append("NO\n");
						continue outer;
					} else if (a[i] > a[i + 1] && st.contains(i) == true) {
						j = a[i];
						a[i] = a[i + 1];
						a[i + 1] = j;
					}
				}
			}
			ans.append("YES\n");
		}
		System.out.println(ans);
	}

	private static boolean check(int[] a) {
		// TODO Auto-generated method stub
		int n = a.length;
		for (int i = 0; i < n - 1; i++) {
			if (a[i] > a[i + 1])
				return false;
		}
		return true;
	}
}
