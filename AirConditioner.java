import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class AirConditioner {
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

	static class Customer implements Comparable<Customer> {
		int t, l, h;

		Customer(int t, int l, int h) {
			this.t = t;
			this.l = l;
			this.h = h;
		}

		public int compareTo(Customer o) {
			int value = Integer.compare(t, o.t);
			return -value;
		}

		public String toString() {
			return t + " " + l + " " + h + "\n";
		}
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, q, m, l, h;
		q = in.nextInt();
		StringBuilder ans = new StringBuilder();
		while (q-- > 0) {
			n = in.nextInt();
			m = in.nextInt();
			Customer cust[] = new Customer[n + 1];
			cust[0] = new Customer(0, m, m);
			for (i = 1; i <= n; i++) {
				t = in.nextInt();
				l = in.nextInt();
				h = in.nextInt();
				cust[i] = new Customer(t, l, h);
//				System.out.println(cust[i]);
			}
			Arrays.sort(cust);
			int diff[] = new int[n];
			for (i = 0; i < n; i++) {
				diff[i] = cust[i].t - cust[i + 1].t;
			}
//			System.out.println(Arrays.toString(diff));
//			System.out.println(Arrays.toString(cust));
			int curl, curh, nextl, nexth, fl, fh;
			curl = cust[0].l;
			curh = cust[0].h;
			boolean flag = true;
			for (i = 1; i <= n; i++) {
				nextl = cust[i].l;
				nexth = cust[i].h;
				if (nextl > curh) {// go right
					if (nextl - curh > diff[i - 1]) {
						flag = false;
						break;
					}
					fh = curh + diff[i - 1];
					fl = nextl;
					fh = Math.min(fh, nexth);
				} else if (nexth < curl) {// go left
					if (curl - nexth > diff[i - 1]) {
						flag = false;
						break;
					}
					fh = nexth;
					fl = curl - diff[i - 1];
					fl = Math.max(fl, nextl);
				} else if (nextl < curl) {
					fl = curl - diff[i - 1];
					fh = curh + diff[i - 1];
					fl = Math.max(fl, nextl);
					fh = Math.min(fh, nexth);
				} else {
					fl = curl - diff[i - 1];
					fh = curh + diff[i - 1];
					fl = Math.max(fl, nextl);
					fh = Math.min(fh, nexth);
				}
				curl = fl;
				curh = fh;
			}
			if (flag)
				ans.append("YES\n");
			else
				ans.append("NO\n");
		}
		System.out.println(ans);
	}
}
