import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GoodNumbers {
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

	static ArrayList<Long> good;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, num, t;
		t = in.nextInt();
		good = new ArrayList<Long>();
		StringBuilder ans = new StringBuilder();
		int bits[] = new int[38];
		generate(0, bits, 38);
		Collections.sort(good);
		System.out.println(good.size());
		System.out.println(good.get(good.size() - 1));
		// System.out.println(good);
		while (t-- > 0) {
			num = in.nextInt();
			int start = 0, end = good.size() - 1;
			while (start < end) {
				int mid = (start + end) / 2;
				if (good.get(mid) == num) {
					ans.append(num + "\n");
					break;
				} else if ((mid + 1) <= end && good.get(mid) < num && good.get(mid + 1) >= num) {
					ans.append(good.get(mid + 1) + "\n");
					break;
				} else if (good.get(mid) < num) {
					start = mid + 1;
				} else
					end = mid - 1;
			}
		}
		System.out.println(ans);
	}

	private static void generate(int pos, int[] bits, int n) {
		// TODO Auto-generated method stub
		if (pos == n) {
			long x = 0;
			for (int i = 0; i < n; i++) {
				if (bits[i] == 1)
					x += (long) Math.pow(3.0, i);

			}
			good.add(x);
			return;
		}
		bits[pos] = 1;
		generate(pos + 1, bits, n);
		bits[pos] = 0;
		generate(pos + 1, bits, n);
	}
}
