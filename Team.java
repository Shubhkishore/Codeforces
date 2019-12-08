import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class Team {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n,m,i;
		n = in.nextInt();
		m = in.nextInt();
		int maxOnes = (n+1)*2;
		StringBuilder ans = new StringBuilder();
		if(m>maxOnes || n>(m+1))
		{
			System.out.println("-1");
			return;
		}
		int s = n+m;
		if(n==m) {
			for(i=0;i<s;i=i+2) {
				ans.append("01");
			}
		}
		else if(n>m) {
			for(i=0;i<s-1;i=i+2) {
				ans.append("01");
			}
			ans.append("0");
		}
		else {
			int extra = m-n-1;
			boolean prevZero = true;
			while(s>0) {
				if(prevZero) {
					if(extra>0) {
						ans.append("11");
						extra--;
						s=s-2;
					}
					else
						{
							ans.append("1");
							s--;
						}
					prevZero = false;
				}
				else {
					ans.append("0");
					s--;
					prevZero=true;
				}
			}
		
		}
		System.out.println(ans);
	}

}
