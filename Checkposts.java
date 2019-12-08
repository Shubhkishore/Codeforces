import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Checkposts {
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

	static class TarjanSCCs {

		/** number of vertices **/
		private int V;

		/** preorder number counter **/
		private int preCount;

		/** low number of v **/
		private int[] low;

		/** to check if v is visited **/
		private boolean[] visited;

		/** to store given graph **/
		private List<Integer>[] graph;

		/** to store all scc **/
		private List<List<Integer>> sccComp;

		private Stack<Integer> stack;

		/** function to get all strongly connected components **/
		public List<List<Integer>> getSCComponents(List<Integer>[] graph) {
			V = graph.length;
			this.graph = graph;
			low = new int[V];
			visited = new boolean[V];
			stack = new Stack<Integer>();
			sccComp = new ArrayList<>();

			for (int v = 0; v < V; v++)
				if (!visited[v])
					dfs(v);

			return sccComp;
		}

		/** function dfs **/
		public void dfs(int v) {
			low[v] = preCount++;
			visited[v] = true;
			stack.push(v);
			int min = low[v];
			for (int w : graph[v]) {
				if (!visited[w])
					dfs(w);
				if (low[w] < min)
					min = low[w];
			}
			if (min < low[v]) {
				low[v] = min;
				return;
			}
			List<Integer> component = new ArrayList<Integer>();
			int w;
			do {
				w = stack.pop();
				component.add(w);
				low[w] = V;
			} while (w != v);
			sccComp.add(component);
		}
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int m, i, t, n, x, y;
		n = in.nextInt();
		int cost[] = new int[n];
		List<Integer> g[] = new List[n];
		for (i = 0; i < n; i++) {
			cost[i] = in.nextInt();
			g[i] = new ArrayList<>();
		}
		m = in.nextInt();

		for (i = 0; i < m; i++) {
			x = in.nextInt();
			y = in.nextInt();
			x--;
			y--;
			g[x].add(y);
		}
		TarjanSCCs tar = new TarjanSCCs();
		List<List<Integer>> scComponents = tar.getSCComponents(g);
		int min = -1;
		long ways = 1;
		long weight = 0;
//		System.out.println(scComponents);
		for (List<Integer> l : scComponents) {
			int s = l.size();
			int w[] = new int[s];
			for (i = 0; i < s; i++)
				w[i] = cost[l.get(i)];
			Arrays.sort(w);
			min = w[0];
			int same = 1;
			for (i = 1; i < s; i++) {
				if (w[i] == min)
					same++;
				else
					break;
			}
			weight += min;
			ways = ((ways % mod) * (same % mod)) % mod;
		}
		System.out.println(weight + " " + ways);
	}
}
