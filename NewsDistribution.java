import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author johnny16
 *
 */
public class NewsDistribution {

	/**
	 * @param args
	 * @throws IOException 
	 * 
	 */   
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		int n,m,i;
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st1.nextToken());
		m = Integer.parseInt(st1.nextToken());
		LinkedList<Integer>[] adj = new LinkedList[n];
		for(i=0;i<n;i++) {
			adj[i] = new LinkedList<Integer>();
		}
		while(m-->0) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			//System.out.println("asdasd           "+ String.valueOf(st));
			int s = Integer.parseInt(st.nextToken());
			//System.out.println(s);
			if(s==1) {
				s = Integer.parseInt(st.nextToken());
				continue;
			}
			if(s==0)
				continue;
			Integer a[] = new Integer[s];
			a[0] = Integer.parseInt(st.nextToken());
			
			for(i=1;i<s;i++) {
				a[i] = Integer.parseInt(st.nextToken());
				//System.out.println(a[i]);
				adj[a[i-1]-1].add(a[i]-1);
				adj[a[i]-1].add(a[i-1]-1);
			}
		}
		Boolean visit[] =new Boolean[n];
		Boolean vis[] =new Boolean[n];
		int c = 0;
		Map<Integer,Integer> mp = new HashMap<Integer,Integer>();
		for(int v = 0; v < n; ++v) {
			int t = 0;
	            if(!visit[v]) { 
	                // print all reachable vertices 
	                // from v 
	                DFSUtil(adj, v,visit);
	                for(i=0;i<n;i++) {
	                	if(vis[i]!=visit[i]) {
	                		t++;
	                	}
	                }
	                for(i=0;i<n;i++) {
	                	if(vis[i]!=visit[i]) {
	                		vis[i] = visit[i];
	                		mp.put(i, t);
	                	}
	                }
	            } 
	     }
		StringBuilder ans = new StringBuilder();
		for(i=0;i<n;i++) {
			ans.append(mp.get(i)+" ");
		}
		System.out.print(ans);
	}
	static void DFSUtil(LinkedList<Integer>[] adj, int v, Boolean[] visit) { 
        // Mark the current node as visited and print it 
        visit[v] = true; 
        //System.out.print(v+" "); 
        // Recur for all the vertices 
        // adjacent to this vertex 
        for (int x : adj[v]) { 
            if(!visit[x]) DFSUtil(adj, x,visit); 
        } 
}
}