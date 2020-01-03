import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class NoToPalindromes {
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
		int i, t, n, p;
		n = in.nextInt();
		p = in.nextInt();
		in.readLine();
		String s = in.readLine().substring(0, n);
		if (p == 1 || (p == 2 && n > 2)) {
			System.out.println("NO");
			return;
		}
		if (p == 2 && n == 2) {
			String st = new String("ab");
			if (Objects.equals(s, st))
				System.out.println("ba");
			else
				System.out.println("NO");
			return;
		}
		char delimit = (char) (96 + p);
		if (n == 1 && p > 1) {
			if (s.charAt(0) == delimit)
				System.out.println("NO");
			else
				System.out.println((char) (s.charAt(0) + 1));
			return;
		}
		StringBuilder ans = new StringBuilder(s);

		boolean flag = true;

		for (i = n - 1; i >= 0; i--) {
//			if (s.charAt(i - 1) == s.charAt(i)) {
//				System.out.println("NO");
//				return;
//			}
//			if (i - 2 >= 0 && s.charAt(i - 2) == s.charAt(i)) {
//				System.out.println("NO");
//				return;
//			}
//			System.out.println("  i  " + i);
			flag = true;
			char orig = s.charAt(i), j;
			for (j = (char) (orig + 1); j <= delimit; j++) {
//				System.out.println(j + " *** " + i);
				boolean c1, c2;
				c1 = c2 = true;
				if ((i - 1) >= 0 && s.charAt(i - 1) == j)
					c1 = false;
				if ((i - 2) >= 0 && s.charAt(i - 2) == j)
					c2 = false;
				if (c1 && c2) {
					flag = false;
//					System.out.println(j + " " + i);
					ans.replace(i, i + 1, String.valueOf(j));
					String rep = getString(j, ans, n, i);
					for (int k = i + 1; k < (n / 3) * 3; k = k + 3)
						ans.replace(k, k + 3, rep);
					int y = n - i - 1;
					if (y % 3 == 0)
						break;
					else if (y % 3 == 1)
						y = 1;
					else if (y % 3 == 2)
						y = 2;
					for (int k = n - y, x = 0; k > i && k < n; k++) {
						ans.replace(k, k + 1, String.valueOf(rep.charAt(x)));
						x++;
					}
//					if (i + 1 < n && j == ans.charAt(i + 1))
//						ans.replace(i + 1, i + 2, "a");
//					else if (i + 2 < n && j == ans.charAt(i + 2))
//						ans.replace(i + 2, i + 3, "a");
					break;
				}
			}
			if (flag == false) {
				System.out.println(ans.substring(0, n));
				break;
			}
		}
		if (flag)
			System.out.println("NO");

	}

	private static String getString(char j, StringBuilder ans, int n, int i) {
		// TODO Auto-generated method stub
		String s = "abc";
		if (j == 'b') {
			if ((i - 1 >= 0 && ans.charAt(i - 1) != 'a') || i == 0)
				s = "acb";
			else if (i - 1 >= 0)
				s = "cab";
		} else if (j == 'a') {
			if ((i - 1 >= 0 && ans.charAt(i - 1) != 'b') || i == 0)
				s = "bca";
			else if (i - 1 >= 0)
				s = "cba";
		} else if (j == 'c') {
			if ((i - 1 >= 0 && ans.charAt(i - 1) != 'a') || i == 0)
				s = "abc";
			else if (i - 1 >= 0)
				s = "bac";
		} else {
			if (i - 1 >= 0 && ans.charAt(i - 1) == 'a')
				s = "bac";
			else
				s = "abc";

		}
		return s;
	}
}
