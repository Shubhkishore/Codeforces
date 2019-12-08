import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CowsAndSequence {
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

	static final int maxn = 500005;
	static List<Integer> seq = new ArrayList<>();
	static double diff[] = new double[maxn];
	static double last;
	static int nums = 0;
	static double sum = 0;
	private static DecimalFormat df = new DecimalFormat("0.000000000");

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, x, a;
		n = in.nextInt();
		StringBuilder ans = new StringBuilder();
		seq.add(0);
		nums = 0;
		while (n-- > 0) {
			t = in.nextInt();
//			System.out.println(seq);
			switch (t) {
			case 1:
				a = in.nextInt();
				x = in.nextInt();
				diff[a] -= x;
				sum += a * x;
				break;
			case 2:
				x = in.nextInt();
				seq.add(x);
				last = x;
				sum += x;
				nums++;
				diff[nums] += x - seq.get(nums - 1);
				break;
			case 3:
				sum -= last;
//				System.out.println(seq);
				x = seq.remove(nums);
				last = x - diff[nums];
				nums--;
				break;
			}
//			System.out.println(Arrays.toString(diff));
			ans.append(df.format(sum / (nums + 1.0)) + "\n");
		}
//		System.out.println(seq);
		System.out.println(ans);
	}

}
