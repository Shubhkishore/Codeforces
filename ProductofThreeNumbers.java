import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductofThreeNumbers {
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

	static class IntIntPair implements Comparable<IntIntPair> {
		public final int first;
		public final int second;

		public static IntIntPair makePair(int first, int second) {
			return new IntIntPair(first, second);
		}

		public IntIntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			IntIntPair pair = (IntIntPair) o;

			return first == pair.first && second == pair.second;
		}

		public int hashCode() {
			int result = first;
			result = 31 * result + second;
			return result;
		}

		public String toString() {
			return "(" + first + "," + second + ")";
		}

		public int compareTo(IntIntPair o) {
			int value = Integer.compare(first, o.first);
			if (value != 0) {
				return value;
			}
			return Integer.compare(second, o.second);
		}

	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, x;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		while (t-- > 0) {
			n = in.nextInt();
			List<IntIntPair> num = new ArrayList<>();

			for (i = 2; i * i <= n; i++) {
//				System.out.println(i + " " + (Math.log(n * 1.0) / Math.log(i * 1.0)));
				if (n % i == 0) {
					x = (int) (Math.log10(n * 1.0) / Math.log10(i * 1.0));
					int z = 1;
					for (int j = 1; j <= x + 1; j++) {
						if (n % (int) (Math.pow(i, j)) == 0) {
							z = j;
						}
					}
					num.add(IntIntPair.makePair(i, z));
					n = n / (int) (Math.pow(i, z));
				}
			}
//			System.out.println(n);
			if (n != 1) {
				num.add(IntIntPair.makePair(n, 1));
			}
//			System.out.println(num);
			if (num.size() == 0) {
				ans.append("NO\n");
				continue;
			}
			if (num.size() == 1) {
				if (num.get(0).second >= 6) {
					ans.append("YES\n");
					x = num.get(0).first;
					int a = x, b = x * x, c = (int) Math.pow(x, num.get(0).second - 3);
					ans.append(a + " " + b + " " + c + "\n");
				} else {
					ans.append("NO\n");
				}
				continue;
			}
			if (num.size() == 2) {
				if (num.get(0).second >= 3) {
					ans.append("YES\n");
					x = num.get(0).first;
					int a = x, b = (int) Math.pow(x, num.get(0).second - 1),
							c = (int) Math.pow(num.get(1).first, num.get(1).second);
					ans.append(a + " " + b + " " + c + "\n");
				} else if (num.get(1).second >= 3) {
					ans.append("YES\n");
					x = num.get(1).first;
					int a = x, b = (int) Math.pow(x, num.get(1).second - 1),
							c = (int) Math.pow(num.get(0).first, num.get(0).second);
					ans.append(a + " " + b + " " + c + "\n");
				} else if (num.get(0).second == 2 && num.get(1).second == 2) {
					ans.append("YES\n");
					x = num.get(1).first;
					int a = x, b = num.get(0).first, c = a * b;
					ans.append(a + " " + b + " " + c + "\n");
				} else
					ans.append("NO\n");
				continue;
			}
			int a, b, c;
			x = 1;
			for (i = 0; i < num.size() - 2; i++) {
				x = x * (int) Math.pow(num.get(i).first, num.get(i).second);
			}
			int y = num.size();
			b = (int) Math.pow(num.get(y - 2).first, num.get(y - 2).second);
			c = (int) Math.pow(num.get(y - 1).first, num.get(y - 1).second);
			ans.append("YES\n" + x + " " + b + " " + c + "\n");
		}
		System.out.println(ans);
	}
}
