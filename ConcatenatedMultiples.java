import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConcatenatedMultiples {
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

	public static int nod(int x) {
		int cnt = 0;
		while (x > 0) {
			cnt++;
			x /= 10;
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int n = in.nextInt(), K = in.nextInt();
		long[] ten = new long[11];
		ten[0] = 1;
		for (int i = 1; i < 11; i++) {
			ten[i] = ten[i - 1] * 10 % K;
		}
		int[] a = new int[n];
		int[] b = new int[n];
		Map<Integer, Integer>[] map = new Map[11];
		for (int i = 0; i < 11; i++) {
			map[i] = new HashMap<>();
		}
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
			b[i] = nod(a[i]);
			map[b[i]].put(a[i] % K, map[b[i]].getOrDefault(a[i] % K, 0) + 1);
		}
		long ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < 11; j++) {
				int v = (int) ((K - a[i] * ten[j] % K) % K);
				ans += map[j].getOrDefault(v, 0);
			}
			if ((a[i] * ten[b[i]] + a[i]) % K == 0)
				ans--;
		}
		System.out.println(ans);
	}
}
