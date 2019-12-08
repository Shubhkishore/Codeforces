import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Hamburgers {
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

	static int nr[] = new int[4];
	static int price_r[] = new int[4];
	static int initial_r[] = new int[4];
	static long ans = 0;
	static long rubel = 0;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, b = 0, s = 0, c = 0;

		String recipe = in.readLine();
		for (char ch : recipe.toCharArray()) {
			if (ch == 'B')
				nr[1]++;
			else if (ch == 'S')
				nr[2]++;
			else if (ch == 'C')
				nr[3]++;
		}

		initial_r[1] = in.nextInt();
		initial_r[2] = in.nextInt();
		initial_r[3] = in.nextInt();
		price_r[1] = in.nextInt();
		price_r[2] = in.nextInt();
		price_r[3] = in.nextInt();
		rubel = in.nextLong();
		long mid, l = 1, r = (long) 10e12;
		while (l <= r) {
			mid = (l + r) / 2;
			if (check(mid)) {
				l = mid + 1;
				ans = mid;
			} else
				r = mid - 1;
		}
		System.out.println(ans);
	}

	private static boolean check(long n) {
		// TODO Auto-generated method stub
		long tmp = rubel;
		if (n * nr[1] > initial_r[1])
			tmp -= (n * nr[1] - initial_r[1]) * price_r[1];
		if (n * nr[2] > initial_r[2])
			tmp -= (n * nr[2] - initial_r[2]) * price_r[2];
		if (n * nr[3] > initial_r[3])
			tmp -= (n * nr[3] - initial_r[3]) * price_r[3];
		if (tmp >= 0)
			return true;
		else
			return false;
	}
}
