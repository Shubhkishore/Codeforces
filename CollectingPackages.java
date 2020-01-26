import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CollectingPackages {
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

	static class IntIntPair implements Comparable<IntIntPair> {
		public final int first;
		public final int second;

		public static IntIntPair makePair(int first, int second) {
			return new IntIntPair(first, second);
		}

		public IntIntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			IntIntPair pair = (IntIntPair) o;

			return first == pair.first && second == pair.second;
		}

		public int hashCode() {
			int result = first;
			result = 31 * result + second;
			return result;
		}

		public String toString() {
			return "(" + first + "," + second + ")";
		}

		public int compareTo(IntIntPair o) {
			int value = Integer.compare(first, o.first);
			if (value != 0) {
				return value;
			}
			return Integer.compare(second, o.second);
		}

	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, j, x, y;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		while (t-- > 0) {
			n = in.nextInt();
			IntIntPair p[] = new IntIntPair[n];
			int rs[] = new int[1001];
			int maxu = 0, maxr = 0, maxx, maxy;
			for (i = 0; i < n; i++) {
				x = in.nextInt();
				y = in.nextInt();
				p[i] = new IntIntPair(x, y);
//				rs[p[i].first]++;
//				maxu=Math.max(y[i], maxu);
//				maxr=Math.max(maxr, x[i]);
			}
			Arrays.sort(p);
//			System.out.println(Arrays.toString(p));
			StringBuilder a = new StringBuilder();
			maxx = maxy = 0;
			boolean flag = false;
			for (i = 0; i < n; i++) {
				if (p[i].first < maxx || p[i].second < maxy) {
//					System.out.println(p[i] + " " + a.toString());
					flag = true;
					break;
				}
				for (j = maxx; j < p[i].first; maxx++, j++) {
					a.append("R");
				}

				for (j = maxy; j < p[i].second; maxy++, j++)
					a.append("U");
			}
			if (flag) {
				ans.append("NO\n");
			} else {
				ans.append("YES\n" + String.valueOf(a) + "\n");
			}
		}
		System.out.println(ans);
	}
}
