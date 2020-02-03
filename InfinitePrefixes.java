import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InfinitePrefixes {
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
			byte[] buf = new byte[999999]; // line length
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
		int i, t, n, x;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		String s;
		while (t-- > 0) {
			n = in.nextInt();
			x = in.nextInt();
			in.readLine();
			s = in.readLine().substring(0, n);
			int lastCnt, zeroes, cnts, no, nz;
			lastCnt = zeroes = cnts = no = nz = 0;
			Map<Integer, Integer> mp = new HashMap<>();
			for (char ch : s.toCharArray()) {
				if (ch == '0')
					nz++;
				else
					no++;
				mp.put(nz - no, mp.getOrDefault(nz - no, 0) + 1);
			}

			lastCnt = nz - no;
//			System.out.println(mp + " " + lastCnt);
			if (lastCnt == 0) {
//				System.out.println(mp.keySet());
				if (mp.keySet().contains(x))
					ans.append("-1\n");
				else
					ans.append((x == 0 ? 1 : 0) + "\n");
				continue;

			}
			int answer = 0;
			for (Map.Entry<Integer, Integer> iterator : mp.entrySet()) {
				int k = iterator.getKey();
				int v = iterator.getValue();
				if ((x - k) % lastCnt == 0 && (x - k) / lastCnt >= 0) {
					answer += v;
				}
			}

			if (x == 0) {
				answer++;
			}
			ans.append(answer + "\n");
		}
		System.out.println(ans);
	}
}
