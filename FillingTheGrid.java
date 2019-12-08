import java.util.Arrays;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class FillingTheGrid {

	/**
	 * @param args
	 */
	static final int mod = 1000000007;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i,h,w,j;
		h = in.nextInt();
		w = in.nextInt();
		int row[] = new int[h];
		int col[] =new int[w];
		long ans=1;
		int grid[][] = new int[h][w];
		for(i=0;i<h;i++) {
			row[i] = in.nextInt();
			for(j=0;j<row[i];j++)
				grid[i][j]=1;
		}
		for(i=0;i<w;i++) {
			col[i] = in.nextInt();
			for(j=0;j<col[i];j++)
				grid[j][i]=1;
		}
		for(i=0;i<h;i++)
		System.out.println(Arrays.toString(grid[i]));
		for(i=1;i<h;i++) {
			for(j=row[i]+1;j<w;j++)
			{
				if(grid[i][j]!=1 && i>(col[j]))
					{
						ans = ((ans%mod)*2)%mod;
						grid[i][j]=1;
					}
			}
		}
		System.out.println();
		for(i=0;i<h;i++)
			System.out.println(Arrays.toString(grid[i]));
		for(i=1;i<w;i++) {
			for(j=col[i]+1;j<h;j++)
			{
				if(grid[j][i]!=1 && i>(row[j]))
					{
						ans = ((ans%mod)*2)%mod;
						grid[j][i]=1;
					}
			}
		}
		System.out.println();
		for(i=0;i<h;i++)
			System.out.println(Arrays.toString(grid[i]));
		if(ans==1)
			System.out.println("0");
		else
			System.out.println(ans%mod);
	}

}
