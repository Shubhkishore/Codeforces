import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ManyEqualSubstrings {
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
		int i, j, n, k;
		n = in.nextInt();
		k = in.nextInt();
		in.readLine();
		String t = in.readLine().substring(0, n);
		StringBuilder ans = new StringBuilder();
		boolean flag = false;
		for (i = 0, j = n - 1; i < j; i++, j--) {
			if (t.charAt(i) == t.charAt(j) && j - 1 < i + 1)
				break;
			if (t.charAt(i) != t.charAt(j)) {
				flag = true;
				break;
			}
		}
		String x = "", y;
//		if (flag == false) {
//			x = t.substring(0, i + 1);
//			y = t.substring(i+1);
//		}
//		else if (i != 0) {
//			x = t.substring(0, i);
//			y=
//		}
//		else
//			x = t;
//		if(flag==false)
//			x=y=t.substring(0,i+1);
//		else
//			y=t.substring(i+1);
//		System.out.println(x);
//		ans.append(t);

//		if (i == 0)
//			x = t;
//		else
//			x = t.substring(i);
//		
		k--;
		for (i = 0; i < n; i++) {
			x = t.substring(i + 1);
			if (t.substring(0, n - i - 1).compareTo(x) == 0)
				break;
		}
		ans.append(t);
		x = t.substring(n - i - 1);
//		System.out.println(i);
		while (k-- > 0) {
			ans.append(x);
		}

		System.out.println(ans);
	}
}
