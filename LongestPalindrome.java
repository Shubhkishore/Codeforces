import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestPalindrome {
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

	static Map<String, Integer> mp;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, m;
		n = in.nextInt();
		m = in.nextInt();
		in.readLine();
		mp = new HashMap<String, Integer>();
		StringBuilder ans = new StringBuilder();
		List<String> so = new ArrayList<>();
		List<String> arr = new ArrayList<>();

		for (i = 0; i < n; i++) {
			String x = in.readLine().substring(0, m);
			mp.put(x, mp.getOrDefault(x, 0) + 1);
			if (!checkPalin(x))
				arr.add(x);
			else
				so.add(x);
		}
//		System.out.println(mp);
//		System.out.println(arr);
		int idx = 0;
		for (i = 0; i < arr.size(); i++) {
			if (checkOpp(arr.get(i))) {
				String r = (new StringBuilder(arr.get(i)).reverse().toString());
				if (mp.get(arr.get(i)) > 0 && mp.getOrDefault(r, 0) > 0) {
					String temp = arr.get(i) + r;
					ans.insert(idx, temp);
					idx = idx + m;
					mp.put(arr.get(i), mp.get(arr.get(i)) - 1);
					mp.put(r, mp.get(r) - 1);
				}
			}
		}
//		for (String tp : so) {
//			ans.insert(idx, tp);
//			idx += m;
//		}
		if (so.size() > 0)
			ans.insert(idx, so.get(0));

		System.out.println(ans.length() + "\n" + ans);
	}

	private static boolean checkOpp(String s) {
		String r = new StringBuilder(s).reverse().toString();
		if (mp.containsKey(r))
			return true;
		return false;
	}

	private static boolean checkPalin(String s) {
		int l = s.length();
		for (int i = 0; i < l / 2; i++) {
			if (s.charAt(i) != s.charAt(l - 1 - i))
				return false;
		}
		return true;
	}
}
