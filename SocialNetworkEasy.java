import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * @author johnny16
 *
 */
public class SocialNetworkEasy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, k;
		n = in.nextInt();
		k = in.nextInt();
		int a[] = new int[n];
		int x, i;
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> s = new HashSet<>();
		for (i = 0; i < n; i++) {
			x = in.nextInt();
			if(s.contains(x))
				continue;
			if (q.isEmpty()) {
				s.add(x);
				q.add(x);
			}
			else if(s.contains(x)==false && q.size()<k) {
				q.add(x);
				s.add(x);
			}
			else if (q.size() == k && s.contains(x)==false) {
				int y = q.poll();
				s.remove(y);
				s.add(x);
				q.add(x);
			}
		}
		StringBuilder ans = new StringBuilder();
		int nn = q.size();
		Stack<Integer> st = new Stack<>();
		ans.append(nn+"\n");
		//System.out.println(q.size());
		for(i=0;i<nn;i++) {
			st.add(q.poll());
			//ans.append(q.peek()).append(" ");
			//q.poll();
		}
		for(i=0;i<nn;i++) {
			//st.add(q.poll());
			ans.append(st.pop()).append(" ");
			//q.poll();
		}
		System.out.println(ans);
	}

}
