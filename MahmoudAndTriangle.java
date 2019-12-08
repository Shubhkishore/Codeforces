import java.util.Arrays;
import java.util.Scanner;

class MahmoudAndTriangle {

	public static void main(String args[]){
		
		Scanner in =new Scanner(System.in);
		int n,i,j,z=0;
		n = in.nextInt();
		long a[] = new long[n];
		for(i=0;i<n;i++){
			a[i]=in.nextLong();
		}
		Arrays.sort(a);
		for(i=0;i<n-1;i++){
			long max = a[i+1]+a[i]-1;
			long min = a[i+1]-a[i]+1;
			if((i+2)<n && a[i+2]<=max)
				{z=1;
				break;
				}
			if((i-1)>=0 && a[i-1]>=min){
				z=1;
				break;
			}
		}
		if(z==1)
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}
