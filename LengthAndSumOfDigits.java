import java.util.Arrays;
import java.util.Scanner;

public class LengthAndSumOfDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int m, s, i;
		m = in.nextInt();
		s = in.nextInt();
		if (m == 1 && s == 0) {
			System.out.println("0 0");
			return;
		}
		else if(s==0) {
			System.out.println("-1 -1");
			return;
		}
		
		if (can(m, s) == false)
			System.out.println("-1 -1");
		else {
			int max[] = new int[m];
			int min[] = new int[m];
		//	Arrays.fill(max, 1);
			//Arrays.fill(min, 1);
			int sum = s;
			//sum -=m;
			for (i = 0; i < m && sum>0; i++) {
				int c = Math.min(9, sum);
				max[i] += c;
				sum = sum - c;
			}
			sum = s;
		//	sum-=m;
			min[0]=1;
			sum-=1;
			for (i = m-1; i >= 0 && sum>0; i--) {
				int c = Math.min(9, sum);
				min[i] += c;
				sum = sum - c;
			}			
			for(i=0;i<m;i++)
				System.out.print(min[i]);
			System.out.print(" ");
			for(i=0;i<m;i++)
				System.out.print(max[i]);
			//System.out.println(Arrays.toString(max) + " " + Arrays.toString(min));
		}

	}

	private static boolean can(int m, int s) {
		// TODO Auto-generated method stub
		int maxSum = 9 * m;
		int minSum = 1;
		if (s > maxSum || s < minSum)
			return false;
		else
			return true;
	}
}