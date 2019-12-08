import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class BeautifulSetOfPoints {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i,n,m,y;
		n = in.nextInt();
		m = in.nextInt();
		StringBuilder ans = new StringBuilder();
		int x=Math.min(n, m);
		ans.append(x+1).append("\n");
		int count=0;
		for(i=0;i<x+1;i++) {
			y = x-i; 
			ans.append(i+" "+y+"\n");
			count++;
		}
		
//		if(i<=m)
//			ans.append("0 ").append(i);
		System.out.println(ans);
		//System.out.println("dfd  "+count);
	}

}
