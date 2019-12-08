import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BalancedTunnel {
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
		// StringBuilder ans = new StringBuilder();
		int entry[] = new int[n + 1];
		int pos[] = new int[n + 1];
		int exit[] = new int[n + 1];
		for (i = 1; i <= n; i++) {
			entry[i] = in.nextInt();
			pos[entry[i]] = i;
		}
		for (i = 1; i <= n; i++) {
			exit[i] = in.nextInt();
		}
		int[] posPlus = new int[n + 1];
		int initP, idealP, ans = 0;
		Set<Integer> s = new HashSet<>(); // stores loc jinka exit ni hua
		Set<Integer> e = new HashSet<>(); // stores loc jinka exit ho chuka entry ni
//		for (i = n; i >= 1; i--) {
//			if (e.contains(entry[i]) == false)
//				s.add(entry[i]);
//
//			if (s.contains(exit[i]))
//				s.remove(exit[i]);
//			posPlus[exit[i]] = s.size();
//			e.add(exit[i]);
//		}
		for (i = 1; i <= n; i++) {
			initP = pos[exit[i]];
			idealP = initP + s.size();
			if (idealP == i)
				continue;

			if (i < idealP) {
				ans++;
				s.add(exit[i]);
			} else {
				posPlus[i] = posPlus[i - 1];
			}
			e.add(entry[i]);
		}
		System.out.println(ans);
	}
}
