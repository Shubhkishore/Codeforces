import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BeautifulRegionalContest {
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
		int i, t, n;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		while (t-- > 0) {
//			System.out.println("tttttttttttt " + t);
			n = in.nextInt();
			int arr[] = new int[n];
			for (i = 0; i < n; i++)
				arr[i] = in.nextInt();
			int maxMedals = n / 2;
//			System.out.println("ds");
			if (maxMedals < 5) {
				ans.append("0 0 0\n");
				continue;
			}
//			System.out.println(maxMedals);
			int effMaxMedals = maxMedals;
			i = effMaxMedals;
			while (i - 1 > 0 && arr[i - 1] == arr[i])
				i--;
			effMaxMedals = i;
			if (effMaxMedals < 5) {
				ans.append("0 0 0\n");
				continue;
			}
//			System.out.println("asdgfdg");
			int uniques = 1, g = 0, s = 0, b = 1;
			for (i = 1; i < effMaxMedals; i++) {
				if (arr[i] != arr[i - 1])
					uniques++;
			}
//			System.out.println("dfsdf");
			if (uniques < 3) {
				ans.append("0 0 0\n");
				continue;
			}
			for (i = 0; i < effMaxMedals; i++) {
				if (arr[i] != arr[i + 1])
					break;
			}
			g = i + 1;
//			System.out.println(g);
			i = g;
			while (s <= g) {
				for (; i < effMaxMedals; i++) {
					if (s > g)
						break;
					while (i + 1 < effMaxMedals) {
//						if (s > g)
//							break;
						s++;
						i++;
						if (arr[i] != arr[i + 1])
							break;
//						System.out.println(arr[i] + " " + g);

					}
//					i--;
//					System.out.println("fgfs " + i);
				}
//				System.out.println("saas");
			}
//			System.out.println("ASa");
			if (effMaxMedals - g - s <= g) {
				ans.append("0 0 0\n");
				continue;
			} else {
				ans.append(g + " " + s + " " + (effMaxMedals - g - s) + "\n");
			}
//			uniques--;
//			for (i = effMaxMedals - 2; i >= 0; i--) {
//				if (arr[i] == arr[i + 1])
//					b++;
//				else {
//					uniques--;
//					if (uniques == 2)
//						break;
//				}
//			}
//			for (int j = 0; j < i; j++) {
//				if (arr[j] != arr[j + 1])
//					break;
//				g++;
//			}
		}
//		System.out.println("asd");
		System.out.println(ans);
	}
}