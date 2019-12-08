import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class EscapeFromStones {
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
			byte[] buf = new byte[1000005]; // line length
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

	static class LL {
		int val;
		LL next;
		LL prev;

		public LL(int val) {
			super();
			this.val = val;
		}

	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n;
		String s = in.readLine();
		StringBuilder ans = new StringBuilder();
		n = s.length();
		n--;
		LinkedList<Integer> seq = new LinkedList<>();
		seq.add(-1);
		seq.add(-1);
		LL head = new LL(-1);
		LL tail = new LL(-1);
		head.next = tail;
		head.prev = null;
		tail.prev = head;
		tail.next = null;
		LL l, r;
		l = head;
		r = tail;

//		int l = 0, r = 1;
//		int pos = 0;
//		double arr[] = new double[n];
//		Map<Double, Integer> mp = new HashMap<>();
////		System.out.println(s);
		for (int j = 0; j < n; j++) {
//			System.out.println(c);
			char c = s.charAt(j);
			if (c == 'l') {
				LL temp = new LL(j + 1);
//				seq.add(r, j + 1);
				temp.prev = r.prev;
				r.prev.next = temp;
				r.prev = temp;
				temp.next = r;
				r = temp;
			} else if (c == 'r') {
				LL temp = new LL(j + 1);
				temp.next = l.next;
				l.next.prev = temp;
				temp.prev = l;
				l.next = temp;
				l = temp;
//				seq.add(l + 1, j + 1);
//				l = l + 1;
//				r++;
			}
		}
//		System.out.println(Arrays.toString(arr));
//		Arrays.sort(arr);
//		System.out.println(n + " " + Arrays.toString(arr));
//		System.out.println(mp);
//		System.out.println(n + " " + seq);
		LL temp = head.next;
		for (; temp != tail;) {
			ans.append(temp.val + "\n");
			temp = temp.next;
		}
		System.out.println(ans);
	}
}
