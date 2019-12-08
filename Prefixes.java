import java.util.Scanner;

/**
 * @author johnny16
 *
 */
class Prefixes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int l = in.nextInt();
		in.nextLine();
		String s = in.nextLine();
		char arr[] = s.toCharArray();
		int i,op=0;
//		boolean a,b;
		for(i=0;i<l;i++) {
			if((i&1)==1) {
				if(arr[i-1]=='a' && arr[i]=='a') {
					arr[i]='b';
					op++;
				}
				else if(arr[i-1]=='b' && arr[i]=='b') {
					arr[i]='a';
					op++;
				}
			}
		}
		System.out.println(op+"\n"+String.valueOf(arr));
	}

}
