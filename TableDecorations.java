import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class TableDecorations {
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		long r,g,b;
		r = in.nextLong();
		g = in.nextLong();
		b = in.nextLong();
		
		long x1 = r<g? (r<b?r:b):(g<b?g:b);
		long x3 = r>g? (r>b?r:b):(g>b?g:b);
		long x2 = (r+g+b)-x1-x3;
//		if((x3-x1)<=1)
//		{
//			System.out.println((r+g+b)/3);
//			return;
//		}
//		if(x2==0) {
//			System.out.println("0");
//			return;
//					
//		}
//		x3-=x1;
//		x2-=x1;
//		float ratio = x3/x2;
//		long ans = x1;
//		if(ratio>=2) {
//			ans+=x2;
//		}
//		else {
//			
//			ans+=((x3&1)==1 && x2>(x3/2+1))?(x3/2)+1:x3/2;
//		}
		long ans;
		if(x3>2*(r+g+b-x3))
			ans = r+g+b-x3;
		else
			ans = (r+g+b)/3;
		System.out.println(ans);
		
	}

}
