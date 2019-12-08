import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BearAndPrimeNumbers {
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

	static int l = (int) 10e7 + 1;
	static List<Integer> primes = new ArrayList<Integer>();

	static void simpleSieve(int limit) {
		// Create a boolean array "mark[0..n-1]" and initialize
		// all entries of it as true. A value in mark[p] will
		// finally be false if 'p' is Not a prime, else true.
		boolean mark[] = new boolean[limit + 1];
		Arrays.fill(mark, true);

		for (int p = 2; p * p < limit; p++) {
			// If p is not changed, then it is a prime
			if (mark[p] == true) {
				// Update all multiples of p
				for (int i = p * 2; i < limit; i += p)
					mark[i] = false;
			}
		}

		// Print all prime numbers and store them in prime
		for (int p = 2; p < limit; p++) {
			if (mark[p] == true) {
				primes.add(p);
			}
		}
	}

	// Prints all prime numbers smaller than 'n'
	static void segmentedSieve() {
		int limit = (int) (Math.floor(Math.sqrt(l)) + 1);
		simpleSieve(limit);

		int low = limit;
		int high = 2 * limit;

		// While all segments of range [0..n-1] are not processed,
		// process one segment at a time
		boolean mark[] = new boolean[limit + 1];
		while (low < limit) {
			if (high >= limit)
				high = limit;

			Arrays.fill(mark, true);

			for (int i = 0; i < primes.size(); i++) {
				// Find the minimum number in [low..high] that is
				// a multiple of prime.get(i) (divisible by prime.get(i))
				// For example, if low is 31 and prime.get(i) is 3,
				// we start with 33.
				int loLim = (int) (Math.floor(low / primes.get(i)) * primes.get(i));
				if (loLim < low)
					loLim += primes.get(i);

				/*
				 * Mark multiples of prime.get(i) in [low..high]: We are marking j - low for j,
				 * i.e. each number in range [low, high] is mapped to [0, high-low] so if range
				 * is [50, 100] marking 50 corresponds to marking 0, marking 51 corresponds to 1
				 * and so on. In this way we need to allocate space only for range
				 */
				for (int j = loLim; j < high; j += primes.get(i))
					mark[j - low] = false;
			}

			// Numbers which are not marked as false are prime
			for (int i = low; i < high; i++)
				if (mark[i - low] == true)
					primes.add(i);

			// Update low and high for next segment
			low = low + limit;
			high = high + limit;
		}
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int i, t, n, m, le, r;
		n = in.nextInt();
		segmentedSieve();
		System.out.println(primes);
		int arr[] = new int[n + 1];
		Map<Integer, Integer> count = new HashMap<>();
		int f[] = new int[l];
		int max = -1;
		for (i = 1; i <= n; i++) {
			arr[i] = in.nextInt();
			if (count.containsKey(arr[i]) == false)
				count.put(arr[i], 0);
			count.put(arr[i], count.get(arr[i]) + 1);
			max = Math.max(arr[i], max);
		}

		// Iterator<Integer> it = primes.iterator();
		for (int x : primes) {
			if (x > max)
				break;
			i = x;
			while (i <= max) {
				f[x] += count.containsKey(i) ? count.get(i) : 0;
				i = i + x;
			}
		}
		m = in.nextInt();
		for (i = 1; i < l; i++) {
			f[i] += f[i - 1];
		}
//		for (i = 1; i <= max; i++)
//			System.out.print(f[i] + " ");
		StringBuilder ans = new StringBuilder();
		for (i = 0; i < m; i++) {
			le = in.nextInt();
			r = in.nextInt();
			le = le >= l ? l - 1 : le;
			r = r >= l ? l - 1 : r;
			ans.append(f[r] - f[le - 1] + "\n");
		}
		System.out.println(ans);
	}
}
