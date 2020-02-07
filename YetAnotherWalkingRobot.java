import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YetAnotherWalkingRobot {
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
			byte[] buf = new byte[500005]; // line length
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
		public final int x;
		public final int y;

		public static IntIntPair makePair(int first, int second) {
			return new IntIntPair(first, second);
		}

		public IntIntPair(int first, int second) {
			this.x = first;
			this.y = second;
		}

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			IntIntPair pair = (IntIntPair) o;

			return x == pair.x && y == pair.y;
		}

		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
		}

		public String toString() {
			return "(" + x + "," + y + ")";
		}

		public int compareTo(IntIntPair o) {
			int value = Integer.compare(x, o.x);
			if (value != 0) {
				return value;
			}
			return Integer.compare(y, o.y);
		}

	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n;
		t = in.nextInt();
		StringBuilder ans = new StringBuilder();
		String s, temp;
		outer: while (t-- > 0) {
			n = in.nextInt();
			in.readLine();
			s = in.readLine().substring(0, n);
//			for (i = 0; i < n - 1; i++) {
//				temp = s.substring(i, i + 2);
//				if (temp.equals("UD") || temp.equals("DU") || temp.equals("LR") || temp.equals("RL")) {
//					ans.append(i + 1 + " " + (i + 2) + "\n");
//					continue outer;
//				}
//			}
			Map<IntIntPair, ArrayList<Integer>> mp = new HashMap<>();
			IntIntPair curr = IntIntPair.makePair(0, 0);
			IntIntPair zero = IntIntPair.makePair(0, 0);
			i = 0;
			mp.put(curr, new ArrayList<>());
			mp.get(curr).add(0);
			int x = Integer.MAX_VALUE;
			int ml = -1, mr = -1;
			for (char c : s.toCharArray()) {
				IntIntPair ne = null;
				if (c == 'L') {
					ne = IntIntPair.makePair(curr.x - 1, curr.y);
					if (!mp.containsKey(ne))
						mp.put(ne, new ArrayList<>());
					mp.get(ne).add(i + 1);
				} else if (c == 'R') {
					ne = IntIntPair.makePair(curr.x + 1, curr.y);
					if (!mp.containsKey(ne))
						mp.put(ne, new ArrayList<>());
					mp.get(ne).add(i + 1);
				} else if (c == 'U') {
					ne = IntIntPair.makePair(curr.x, curr.y + 1);
					if (!mp.containsKey(ne))
						mp.put(ne, new ArrayList<>());
					mp.get(ne).add(i + 1);
				} else if (c == 'D') {
					ne = IntIntPair.makePair(curr.x, curr.y - 1);
					if (!mp.containsKey(ne))
						mp.put(ne, new ArrayList<>());
					mp.get(ne).add(i + 1);
				}
				curr = ne;
				i++;
			}
//			System.out.println(mp);

			for (Map.Entry<IntIntPair, ArrayList<Integer>> e : mp.entrySet()) {
				IntIntPair k = e.getKey();
				ArrayList<Integer> v = e.getValue();
				if (v.size() == 1)
					continue;
				else {
					for (int j = 0; j < v.size() - 1; j++) {
						int diff = v.get(j + 1) - v.get(j);
						if (diff < x) {
							x = diff;
							ml = v.get(j) + 1;
							mr = v.get(j + 1);
						}
					}
				}
			}
			if (x == Integer.MAX_VALUE)
				ans.append("-1\n");
			else
				ans.append(ml + " " + mr + "\n");
		}
		System.out.println(ans);
	}
}
