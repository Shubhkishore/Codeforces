import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
public class GargariAndBishops {

	static int mod = 1000000007;
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
 
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Reader in = new Reader();

		int n, i, j;
		n = in.nextInt();
		int arr[][] = new int[n+1][n+1];
//		Map<Integer, Long> lDig = new HashMap<>();
//		Map<Integer, Long> rDig = new HashMap<>();
		long lDig[] = new long[2 * n + 2];
		long rDig[] = new long[2 * n + 2];
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				arr[i][j] = in.nextInt();
				rDig[i + j] += arr[i][j];
				lDig[i - j + n] += arr[i][j];

			}
		}

		long val = 0;
		int c;
		int x[] = new int[2];

		int y[] = new int[2];
		x[0] = x[1] = y[0] = 1;
		y[1] = 2;
		long ans[] = new long[2];
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				c = (i + j) & 1;
				val = lDig[i - j + n] + rDig[i + j] - arr[i][j];
				if (val > ans[c]) {
					ans[c] = val;
					x[c] = i;
					y[c] = j;
				}
			}
		}
		StringBuilder s = new StringBuilder();
		s.append(ans[0]+ans[1]+"\n");
		s.append(x[0] + " " + y[0] + " " + x[1] + " " + y[1]);
		System.out.println(s);
		//System.out.println(x[0] + " " + y[0] + " " + x[1] + " " + y[1]);
		
	}

}
