import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class WhiteSheet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i,x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6;
		x1 = in.nextInt();
		y1 = in.nextInt();
		x2 = in.nextInt();
		y2 = in.nextInt();
		x3 = in.nextInt();
		y3 = in.nextInt();
		x4 = in.nextInt();
		y4 = in.nextInt();
		x5 = in.nextInt();
		y5 = in.nextInt();
		x6 = in.nextInt();
		y6 = in.nextInt();
		if((x1<x2 && x1<x3) || (x1>x2 && x1>x3))
		{
			System.out.println("YES");
			return;
		}
		if((y1<y2 && y1<y3) || (y1>y2 && y1>y3))
		{
			System.out.println("YES");
			return;
		}
		if((x1<x2 && x1>x3) || (x1>x2 && x1<x3))
		{
			System.out.println("YES");
			return;
		}
		if((y1<y2 && y1>y3) || (y1>y2 && y1<y3))
		{
			System.out.println("YES");
			return;
		}
	}

}
