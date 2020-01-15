import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Garland {
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
		int i, j, t, n, ans = 0;
		n = in.nextInt();
		int arr[] = new int[n + 1];
		boolean f[] = new boolean[n + 1];
		ArrayList<Integer> pos = new ArrayList<>();
		for (i = 1; i <= n; i++) {
			arr[i] = in.nextInt();
			f[arr[i]] = true;
			if (arr[i] == 0)
				pos.add(i);
		}
		int even = 0, odd = 0;
		if (pos.size() == n && n != 1) {
			System.out.println("1");
			return;
		} else if (n == 1) {
			System.out.println("0");
			return;
		}
		for (i = 1; i <= n; i++) {
			if (!f[i]) {
				if ((i & 1) == 0)
					even++;
				else
					odd++;
			}
		}
		ArrayList<Integer> needOdd = new ArrayList<>();
		ArrayList<Integer> needEven = new ArrayList<>();
		int totalBuckets = 0;
		for (i = 1; i <= n; i++) {
			boolean oflag = false, eflag = false;
			if (arr[i] == 0) {
				totalBuckets++;
				if (i - 1 >= 1) {
					if ((arr[i - 1] & 1) == 0)
						eflag = true;
					else
						oflag = true;
				}
				for (j = i + 1; j <= n && arr[j] == 0; j++) {
				}
//				System.out.println(j + " " + i);
				if (oflag && j <= n) {
					if ((arr[j] & 1) == 1) {
						needOdd.add(j - i);
					}
				} else if (eflag && j <= n) {
					if ((arr[j] & 1) == 0) {
//						System.out.println("asas");
						needEven.add(j - i);
					}
				}
//					else if (j > n) {
//					if (oflag)
//						needOdd.add(j - i);
//					else if (eflag)
//						needEven.add(j - i);
//				} else if (!oflag && !eflag && j <= n) {
//					if ((arr[j] & 1) == 0)
//						needEven.add(j - i);
//					else
//						needOdd.add(j - i);
//				}
				i = j - 1;
			}
		}
		Collections.sort(needOdd);
		Collections.sort(needEven);
//		System.out.println(totalBuckets + " \n" + needOdd + " \n" + needEven + " num odd\n" + odd + " " + even);
		for (i = 0; i < needOdd.size(); i++) {
			if (odd == 0)
				break;
			int x = needOdd.get(i);
			if (x <= odd) {
				odd -= x;
				totalBuckets--;
				needOdd.set(i, -1);
			} else {

			}
		}
		for (i = 0; i < needEven.size(); i++) {
			if (even == 0)
				break;
			int x = needEven.get(i);
			if (x <= even) {
				even -= x;
				totalBuckets--;
				needEven.set(i, -1);
			} else {

			}
		}
//		System.out.println(odd + " " + even);
		if (arr[1] == 0) {
			for (i = 1; arr[i] == 0; i++) {
			}
			if (arr[i] % 2 == 0) {
				if (even >= i - 1) {
					even -= i - 1;
				} else {
					odd -= (i - 1) - even;
					ans++;
				}
				totalBuckets--;
			} else {
				if (odd >= i - 1)
					odd -= i - 1;
				else {
					even -= (i - 1) - odd;
					ans++;
				}
				totalBuckets--;
			}
		}
		if (arr[n] == 0) {
			for (i = n; arr[i] == 0; i--) {
			}
			if (arr[i] % 2 == 0) {
				if (even >= n - i)
					even -= n - i;
				else {
					odd -= (n - i) - even;
					ans++;
				}
				totalBuckets--;
			} else {
				if (odd >= n - i)
					odd -= n - i;
				else {
					even -= (n - i) - odd;
					ans++;
				}
				totalBuckets--;
			}
		}
		for (i = 1; i < n; i++) {
			if (arr[i] != 0 && arr[i + 1] != 0 && (arr[i] % 2 != arr[i + 1] % 2))
				ans++;
		}
		t = 0;
		for (i = 0; i < needOdd.size(); i++) {
			if (needOdd.get(i) == -1)
				t++;
		}
		totalBuckets -= needOdd.size() - t;
		t = needOdd.size() - t;
		int w = 0;
		for (i = 0; i < needEven.size(); i++) {
			if (needEven.get(i) == -1)
				w++;
		}
		totalBuckets -= needEven.size() - w;
		w = needEven.size() - w;
//		System.out.println(totalBuckets + " " + needEven.size() + " " + needOdd.size() + " " + needOdd);
		System.out.println(ans + (t + w) * 2 + totalBuckets);
	}
}
