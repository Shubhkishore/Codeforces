import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Maze {
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
		int i, j, n, m, k;
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		in.readLine();
		StringBuilder ans = new StringBuilder();
		char maze[][] = new char[n][m];
		int free = 0;
		Queue<IntIntPair> p = new LinkedList<>();
		int x[] = { 0, 0, 1, -1 };
		int y[] = { 1, -1, 0, 0 };
		boolean visited[][] = new boolean[n][m];
		for (i = 0; i < n; i++) {
			maze[i] = in.readLine().toCharArray();
			for (j = 0; j < m; j++) {
				if (maze[i][j] == '.') {
					free++;
					p.add(new IntIntPair(i, j));
				}
			}
		}
		// System.out.println(p + "\n" + p.size());
//		for (i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(maze[i]));
//		}
		int curx, cury, size = free - k;
		i = 0;
		IntIntPair curr;
		Queue<IntIntPair> q = new LinkedList<>();
		q.add(p.peek());
		// System.out.println(size);
		Set<IntIntPair> s = new HashSet<>();
		while (i < size) {
			curr = q.poll();
			if (s.contains(curr))
				continue;
			s.add(curr);
			i++;
			// System.out.println(curr);
			curx = curr.first;
			cury = curr.second;
			visited[curx][cury] = true;
			for (j = 0; j < 4; j++) {
				int nx = curx + x[j];
				int ny = cury + y[j];
				if (valid(nx, ny, n, m) && visited[nx][ny] == false && maze[nx][ny] == '.') {
					q.add(new IntIntPair(nx, ny));
				}
			}
		}

		// System.out.println(q);
//		for (i = 0; i < n; i++)
//			System.out.println(Arrays.toString(visited[i]));
//		for (i = 0; i < n; i++)
//			System.out.println(Arrays.toString(maze[i]));
//		System.out.println(p);
		k = p.size();
		for (i = 0; i < k; i++) {
			curr = p.poll();
			curx = curr.first;
			cury = curr.second;

			if (visited[curx][cury] == false && maze[curx][cury] == '.') {
				// System.out.println("stupid u");
				maze[curx][cury] = 'X';
			}
		}

		for (i = 0; i < n; i++) {
			// System.out.println(Arrays.toString(maze[i]));
			for (j = 0; j < m; j++)
				ans.append(maze[i][j]);
			ans.append("\n");
		}
		System.out.println(ans);
	}

	private static boolean valid(int i, int j, int n, int m) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
}
