import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ObtainTheString {
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
			byte[] buf = new byte[999995]; // line length
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
		int i, n, x, T, j;
		T = in.nextInt();
		in.readLine();
		StringBuilder ans = new StringBuilder();
		tests: while (T-- > 0) {
			String s = in.readLine();
			String t = in.readLine();
			s = s.substring(0, s.length() - 1);
			t = t.substring(0, t.length() - 1);
//			System.out.println(s + " " + t);
			ArrayList<Integer> smp[] = new ArrayList[26];
			for (i = 0; i < 26; i++) {
				smp[i] = new ArrayList<>();
			}
			for (i = 0; i < s.length(); i++)
				smp[s.charAt(i) - 'a'].add(i);

			char ch;
//			for (i = 0; i < 26; i++)
//				System.out.println(smp[i]);
			ArrayList<Integer> temp;
			i = 0;
			int rep = 0, mxsLoc = 0;
			for (char c : t.toCharArray()) {
				temp = smp[c - 'a'];
				if (temp.size() == 0) {
					ans.append("-1\n");
					continue tests;
				}
				x = lowerBound(temp, 0, temp.size(), mxsLoc);
//				System.out.println(c + " " + x);
				if (x == temp.size()) {
					rep++;
					mxsLoc = 0;
					x = lowerBound(temp, 0, temp.size(), mxsLoc);
				}

				mxsLoc = temp.get(x) + 1;
//				mxsLoc = temp.get(mxsLoc);
//

//				System.out.println(mxsLoc);
//				if (mxsLoc >= s.length()) {
//					rep++;
//					mxsLoc = -1;
//				}
//				System.out.println(c + " " + mxsLoc);
			}
			ans.append(rep + ((mxsLoc > 0) ? 1 : 0) + "\n");
		}
		System.out.println(ans);
	}

	private static int lowerBound(ArrayList<Integer> a, int low, int high, int need) {
		while (low < high) {
			int middle = low + (high - low) / 2;
			if (need > a.get(middle))
				low = middle + 1;
			else
				high = middle;
		}
		return low;
	}
}
