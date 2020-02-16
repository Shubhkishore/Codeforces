import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class PerfectKeyboard {
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

	static class Node {
		char data;
		Node previous;
		Node next;

		public Node(char data) {
			this.data = data;
		}

	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n;
		t = in.nextInt();
		in.readLine();
		StringBuilder ans = new StringBuilder();
		String s;
		outer: while (t-- > 0) {
			s = in.readLine();
			s = s.substring(0, s.length() - 1);
			n = s.length();
			Node head = new Node(s.charAt(0));
			LinkedList<Character> ll = new LinkedList<>();
			Node pre[] = new Node[26];
			pre[s.charAt(0) - 'a'] = head;
			char c;
			for (i = 1; i < n - 1; i++) {
				c = s.charAt(i);
//				System.out.println(c);
				int idx = c - 'a';
				if (pre[idx] == null)
					pre[idx] = new Node(c);
				if (pre[idx].previous == null && pre[idx].next != null && pre[idx].next.data != s.charAt(i - 1))
					pre[idx].previous = pre[s.charAt(i - 1) - 'a'];
				if (pre[idx].next == null && pre[s.charAt(i - 1) - 'a'].data != s.charAt(i + 1))
					pre[idx].next = pre[s.charAt(i + 1) - 'a'] == null
							? pre[s.charAt(i + 1) - 'a'] = new Node(s.charAt(i + 1))
							: pre[s.charAt(i + 1) - 'a'];
				Node nn = pre[s.charAt(i + 1) - 'a'];
				Node pp = pre[s.charAt(i - 1) - 'a'];
//				if (c != nn.data && c != pp.data) {
//					ans.append("NO\n");
//					continue outer;
//				}
			}
//			System.out.println("fdfaf");
			Node curr = head;
			boolean flag = false;
			while (curr.next != null) {
				if (curr == head) {
					flag = true;
					break;
				}
				curr = curr.next;
			}
			if (flag)
				ans.append("NO\n");
			else {
				ans.append("YES\n");
				StringBuilder temp = new StringBuilder();
				curr = head;
//				System.out.println(curr.data + " " + curr.previous.data);
				while (curr.previous != null) {
					curr = curr.previous;
//					System.out.println("fed");
				}
				while (curr.next != null) {
//					System.out.println("wtf");
					temp.append(curr.data);
					curr = curr.next;
				}
//				System.out.println("***");
				boolean alph[] = new boolean[26];
				for (i = 0; i < temp.length(); i++)
					alph[temp.charAt(i) - 'a'] = true;
				for (i = 0; i < 26; i++) {
					if (!alph[i])
						temp.append((char) (i + 'a'));
				}
				ans.append(temp.toString() + "\n");
			}
		}
		System.out.println(ans);
	}
}
