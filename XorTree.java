import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XorTree {
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

	static List<Integer> adjList[];
	static int init[];
	static int goal[];
	static int ans = 0;
	static boolean visited[];
	static List<Integer> flippers;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int u, v, i, t, n;
		n = in.nextInt();
		adjList = new List[n + 1];
		for (i = 0; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (i = 1; i < n; i++) {
			u = in.nextInt();
			v = in.nextInt();
			adjList[v].add(u);
			adjList[u].add(v);
		}
		init = new int[n + 1];
		goal = new int[n + 1];
		visited = new boolean[n + 1];
		for (i = 1; i <= n; i++)
			init[i] = in.nextInt();
		for (i = 1; i <= n; i++)
			goal[i] = in.nextInt();
//		for (i = 1; i <= n; i++)
//			System.out.println(adjList[i]);
		flippers = new ArrayList<>();
		dfs(1, false, false, 1);
		System.out.println(ans);

		StringBuilder s = new StringBuilder();
		for (i = 0; i < flippers.size(); i++) {
			s.append(flippers.get(i) + "\n");
		}
		System.out.println(s);
	}

	private static void dfs(int curr, boolean oddFlip, boolean evenFlip, int level) {
//		System.out.println(curr + " " + init[curr] + " " + goal[curr] + " o " + oddFlip + " " + evenFlip);
		visited[curr] = true;
		if ((level & 1) == 0 && evenFlip)
			init[curr] = 1 - init[curr];
		else if ((level & 1) == 1 && oddFlip)
			init[curr] = 1 - init[curr];
		if (adjList[curr].size() == 0) {
			if (init[curr] != goal[curr]) {
				ans++;
				flippers.add(curr);
			}
			return;
		}
		boolean flag = false;
		if (init[curr] != goal[curr]) {
//			System.out.println(curr);
			ans++;
			flag = true;
			flippers.add(curr);
		}
		for (int x : adjList[curr]) {
			if (!visited[x]) {
				if ((level & 1) == 0 && flag)
					dfs(x, oddFlip, !evenFlip, level + 1);
				else if ((level & 1) == 1 && flag)
					dfs(x, !oddFlip, evenFlip, level + 1);
				else
					dfs(x, oddFlip, evenFlip, level + 1);
			}
		}
	}
}
