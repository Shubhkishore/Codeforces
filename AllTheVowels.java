
// Working program using Reader Class
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author johnny16
 *
 */
public class AllTheVowels {
	static int mod = 1000000007;
	static boolean prime[];

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
			byte[] buf = new byte[64]; // line length
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

	public static void main(String[] args) throws IOException
    {
        Reader in=new Reader();
        int i,n,n1,m;
        n=m=5;
        n1 = in.nextInt();
//        sieveOfEratosthenes(10000);
//        if(prime[n1])
//        	System.out.println("-1");
        if(n1<25) {
        	System.out.println("-1");
        	return;
        }
        
        String a[] = {"aeiou","uaieo","ouaei","iouae","eioua"};
        i=5;
        while(i<=100) {
        	if(n1%i==0) {
        		//System.out.println(i+" asasd");
        		if((n1/i)>5 || (n1/i)==5)
        		{
        			n= i;
        			m = n1/i;
        			break;
        		}
        		else
        		{
        			System.out.println("-1");
        			return;
        		}
        	}
        	i++;
        }
        if(i==101) {
        	System.out.println("-1");
			return;
        }
        StringBuilder ans = new StringBuilder();
        for(i=0;i<n;i++) {
        	int x = m/5;
        	int y = m%5;
        	for(int j=0;j<x;j++)
        		ans.append(a[i%5]);
        	ans.append(a[i%5].substring(0, y));
        }
        System.out.println(ans);
    }

//	static void sieveOfEratosthenes(int n) {
//		// Create a boolean array "prime[0..n]" and initialize
//		// all entries it as true. A value in prime[i] will
//		// finally be false if i is Not a prime, else true.
//		prime = new boolean[n + 1];
//		for (int i = 0; i < n; i++)
//			prime[i] = true;
//
//		for (int p = 2; p * p <= n; p++) {
//			// If prime[p] is not changed, then it is a prime
//			if (prime[p] == true) {
//				// Update all multiples of p
//				for (int i = p * p; i <= n; i += p)
//					prime[i] = false;
//			}
//		}

//	}
}