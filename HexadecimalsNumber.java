import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class HexadecimalsNumber {
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

	static int ans;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, n;
		ans = 0;
		n = in.nextInt();
		int digits = (int) Math.log10(n);
//		StringBuilder num = new StringBuilder();
//		num.append("1");
//		for (i = 1; i < digits; i++) {
//			ans += Math.pow(2, i);
//			num.append("1");
//		}
//		num.append("1");
//		if (Integer.parseInt(num.toString()) <= n) {
//			ans += Math.pow(2, digits);
//			System.out.println(ans);
//			return;
//		}
//		int x = (int) Math.pow(2, digits);
//		for(i=digits;i>0;i--) {
//			num.replace(i, i+1, "0");
//			if(Integer.parseInt(num.toString())<n) {
//				break;
//			}
//		}
//		ans= ans-Math.pow(2, b)
		if (n == 1) {
			System.out.println("1");
			return;
		}
		char arr[] = new char[digits + 1];
		Arrays.fill(arr, '0');
		// System.out.println(Integer.parseInt(String.valueOf(arr)));
		// System.out.println(digits);
		fill(0, arr, n, digits + 1);
		System.out.println(ans - 1);
	}

	private static void fill(int i, char[] arr, int n, int d) {
		// TODO Auto-generated method stub
		if (i == d) {

			if (Integer.parseInt(String.valueOf(arr)) <= n) {
				// System.out.println(Integer.parseInt(String.valueOf(arr)));
				ans++;
			}
			return;
		}
		arr[i] = '1';
		fill(i + 1, arr, n, d);
		arr[i] = '0';
		fill(i + 1, arr, n, d);
	}
}
