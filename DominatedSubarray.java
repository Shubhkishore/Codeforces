import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DominatedSubarray {
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
		int i, t, n, j;
		// n = in.nextInt();
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		boolean flag;
		while (t-- > 0) {
			flag = false;
			n = in.nextInt();
			int arr[] = new int[n];
			Map<Integer, List<Integer>> mp = new HashMap<>();
			List<Integer> temp;
			for (i = 0; i < n; i++) {
				arr[i] = in.nextInt();
				if (mp.containsKey(arr[i]) == false)
					mp.put(arr[i], new ArrayList<Integer>());
				temp = mp.get(arr[i]);
				temp.add(i);
				mp.put(arr[i], temp);

			}
			if (n == 1) {
				ans.append("-1\n");
				continue;
			}
			// System.out.println(mp);
			int slen = n + 1;
			for (Map.Entry<Integer, List<Integer>> e : mp.entrySet()) {
				temp = e.getValue();
				Collections.sort(temp);
				for (i = 1; i < temp.size(); i++) {
					int diff = temp.get(i) - temp.get(i - 1) + 1;
					if (diff < slen)
						slen = diff;
				}
			}
			if (slen == n + 1)
				ans.append("-1\n");
			else
				ans.append(slen + "\n");
//			Set<Integer> s = new HashSet<>();
//			int arr[] = new int[n];
//			arr[0] = in.nextInt();
//			s.add(arr[0]);
//			if (n == 1) {
//				ans.append("-1\n");
//				continue;
//			}
//
//			for (i = 1; i < n; i++) {
//				arr[i] = in.nextInt();
//				s.add(arr[i]);
//				if (arr[i] == arr[i - 1])
//					flag = true;
//			}
//			if (s.size() == n) {
//				ans.append("-1\n");
//				continue;
//			}
//			if (flag) {
//				ans.append("2\n");
//				continue;
//			}
//			

//			flag = false;
//			int slen = n;
//			for (i = 0; i < n - 1; i++) {
//				s = new HashSet<>();
//				for (j = i; j < i + slen && j < n; j++) {
//					if (s.contains(arr[j])) {
//						slen = j - i + 1;
//						break;
//					} else
//						s.add(arr[j]);
//				}
//				if (slen == 3) {
//					flag = true;
//					ans.append("3\n");
//					break;
//				}
//				if (i + slen > n)
//					break;
//			}
//			if (flag == false)
//				ans.append(slen + "\n");
		}
		System.out.println(ans);
	}
}
