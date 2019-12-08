import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ArithmeticProgression {
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

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, n;
		n = in.nextInt();

		long arr[] = new long[n];
		for (i = 0; i < n; i++)
			arr[i] = in.nextLong();
		if (n == 1) {
			System.out.println("-1");
			return;
		}
		Arrays.sort(arr);
		if (arr[0] == arr[n - 1]) {
			System.out.println("1\n" + arr[0]);
			return;
		}
		long diff[] = new long[n - 1];
		for (i = 0; i < n - 1; i++)
			diff[i] = arr[i + 1] - arr[i];
		if (n == 2) {
			if ((diff[0] & 1) == 0) {
				System.out
						.println("3\n" + (arr[0] - diff[0]) + " " + (arr[0] + diff[0] / 2) + " " + (arr[1] + diff[0]));
				return;
			} else {
				System.out.println("2\n" + (arr[0] - diff[0]) + " " + (arr[1] + diff[0]));
				return;
			}
		}
		long dif1, dif2, cnt1 = 0, cnt2 = 0;
		dif1 = dif2 = diff[0];
		for (i = 0; i < n - 1; i++) {
			if (diff[i] == dif1) {
				cnt1++;
			} else if (dif1 == dif2) {
				dif2 = diff[i];
				cnt2++;
			} else if (diff[i] == dif2) {
				cnt2++;
			} else {
				System.out.println("0");
				return;
			}
		}
		if (dif1 == dif2) {
//			if ((dif1 & 1) == 0) {
//				StringBuilder ans = new StringBuilder();
//				ans.append((n + 1) + "\n");
//				for (i = 0; i < n; i++)
//					ans.append(arr[i] - (dif1 / 2) + " ");
//				ans.append(arr[n - 1] + dif1 / 2);
//				System.out.println(ans);
//				return;
//			} else 
			System.out.println("2\n" + (arr[0] - dif1) + " " + (arr[n - 1] + diff[0]));
			return;

		}
		long max, c1, min, c2;
		if (dif1 > dif2) {
			max = dif1;
			c1 = cnt1;
			min = dif2;
			c2 = cnt2;
		} else {
			max = dif2;
			c1 = cnt2;
			min = dif1;
			c2 = cnt1;
		}
		if (min == 0) {
			System.out.println("0");
			return;
		}
		if (c1 == 1 && (max & 1) == 0 && (max / 2) == min) {
			for (i = 0; i < n - 1; i++) {
				if (diff[i] == max) {
					System.out.println("1\n" + (arr[i] + max / 2));
					return;
				}
			}
		} else {
			System.out.println("0");
		}
	}
}
