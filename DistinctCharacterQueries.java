import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class DistinctCharacterQueries {

	/**
	 * @param args
	 */
	static class node{
		int count;
		boolean alpha[];
		char c;
		
		node(){
			count=0;
			alpha = new boolean[26];
			c='a';
		}
		
		node(int cnt,boolean[] x){
			this.count = cnt;
			for(int i=0;i<26;i++)
				alpha[i]=x[i];
			this.c='a';
		}
	}
	static class SegmentTree  
	{ 
	    node st[]; // The array that stores segment tree nodes 
	  
	    /* Constructor to construct segment tree from given array. This 
	       constructor  allocates memory for segment tree and calls 
	       constructSTUtil() to  fill the allocated memory */
	    SegmentTree(char arr[], int n) 
	    { 
	        // Allocate memory for segment tree 
	        //Height of segment tree 
	        int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); 
	  
	        //Maximum size of segment tree 
	        int max_size = 2 * (int) Math.pow(2, x) - 1; 
	  
	        st = new node[max_size]; // Memory allocation 
	        for(int i=0;i<max_size;i++)
	        	st[i] = new node();
	        constructSTUtil(arr, 0, n - 1, 0); 
	    } 
	  
	    // A utility function to get the middle index from corner indexes. 
	    int getMid(int s, int e) { 
	        return s + (e - s) / 2; 
	    } 
	  
	    /*  A recursive function to get the sum of values in given range 
	        of the array.  The following are parameters for this function. 
	  
	      st    --> Pointer to segment tree 
	      si    --> Index of current node in the segment tree. Initially 
	                0 is passed as root is always at index 0 
	      ss & se  --> Starting and ending indexes of the segment represented 
	                    by current node, i.e., st[si] 
	      qs & qe  --> Starting and ending indexes of query range */
	    node getDistinctUtil(int ss, int se, int qs, int qe, int si) 
	    { 
	        // If segment of this node is a part of given range, then return 
	        // the sum of the segment 
	        if (qs <= ss && qe >= se) 
	            return st[si]; 
	  
	        // If segment of this node is outside the given range 
	        if (se < qs || ss > qe) 
	            return null; 
	  
	        // If a part of this segment overlaps with the given range 
	        int mid = getMid(ss, se);
	        node left = getDistinctUtil(ss, mid, qs, qe, 2 * si + 1);
	        node right = getDistinctUtil(ss, mid, qs, qe, 2 * si + 2);
	        int cnt=0;
	        boolean x[] = new boolean[26];
	        for(int i=0;i<26;i++) {
	        	if((left!=null && left.alpha[i]) || (right!=null && right.alpha[i]))
	        		{
	        			x[i] = true;
	        			cnt++;
	        		}
	        }
	        node temp=new node(cnt,x);
	        return temp; 
	    } 
	  
	    /* A recursive function to update the nodes which have the given  
	       index in their range. The following are parameters 
	        st, si, ss and se are same as getSumUtil() 
	        i    --> index of the element to be updated. This index is in 
	                 input array. 
	       diff --> Value to be added to all nodes which have i in range */
	    node updateValueUtil(int ss, int se, int i, int si,char new_val) 
	    { 
	        // Base Case: If the input index lies outside the range of  
	        // this segment 
	        if (i < ss || i > se) 
	            return null; 
	  
	        // If the input index is in range of this node, then update the 
	        // value of the node and its children 
	        //st[si] = st[si] + diff;
	        if(se==ss) {
	        	st[si].c=new_val;
	        	for(int id=0;i<26;i++)
	        		st[si].alpha[id]=false;
	        	st[si].alpha[(int)(new_val-'a')]=true;
	        	st[si].count=1;
	        	return null;
	        }
	        if (se != ss) { 
	            int mid = getMid(ss, se);
	            node left = updateValueUtil(ss, mid, i, 2 * si + 1,new_val); 
	            node right = updateValueUtil(mid + 1, se, i, 2 * si + 2,new_val); 
	            int cnt=0;
		        for(int id=0;id<26;i++) {
		        	if((left!=null && left.alpha[id]) || (right!=null && right.alpha[id]))
		        		{
		        			st[si].alpha[id] = true;
		        			cnt++;
		        		}
		        }
		        st[si].count=cnt;
		        return st[si];
	        } 
	        return null;
	    } 
	  
	    // The function to update a value in input array and segment tree. 
	   // It uses updateValueUtil() to update the value in segment tree 
	    void updateValue(char arr[], int n, int i, char new_val) 
	    { 
	        // Check for erroneous input index 
	        
	  
	        // Get the difference between new value and old value 
	        //int diff = new_val - arr[i]; 
	  
	        // Update the value in array 
	        arr[i] = new_val; 
	  
	        // Update the values of nodes in segment tree 
	        updateValueUtil(0, n - 1, i, 0,new_val); 
	    } 
	  
	    // Return sum of elements in range from index qs (quey start) to 
	   // qe (query end).  It mainly uses getSumUtil() 
	    int getDistinct(int n, int qs, int qe) 
	    { 
	        // Check for erroneous input values 
	      
	        return getDistinctUtil(0, n - 1, qs, qe, 0).count; 
	    } 
	  
	    // A recursive function that constructs Segment Tree for array[ss..se]. 
	    // si is index of current node in segment tree st 
	    node constructSTUtil(char arr[], int ss, int se, int si) 
	    { 
	        // If there is one element in array, store it in current node of 
	        // segment tree and return 
	        if (ss == se) { 
	            st[si].c = arr[ss];
	            st[si].alpha[(int)(arr[ss]-'a')]=true;
	            st[si].count=1;
	            return st[si]; 
	        } 
	  
	        // If there are more than one elements, then recur for left and 
	        // right subtrees and store the sum of values in this node 
	        int mid = getMid(ss, se);
	        node left = constructSTUtil(arr, ss, mid, si * 2 + 1);
	        node right = constructSTUtil(arr, ss, mid, si * 2 + 2);
	        int cnt=0;
	        for(int i=0;i<26;i++) {
	        	if(left.alpha[i] || right.alpha[i])
	        		{
	        			st[si].alpha[i] = true;
	        			cnt++;
	        		}
	        }
	        st[si].count=cnt;
//	        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) + 
//	                 constructSTUtil(arr, mid + 1, se, si * 2 + 2); 
	        return st[si]; 
	    } 
	  
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		SegmentTree seg = new SegmentTree(s.toCharArray(),s.length());
		int o,q,i,l,r;
		String c;
		q = in.nextInt();
		StringBuilder ans = new StringBuilder();
		while(q-->0) {
			o = in.nextInt();
			if(o==1) {
				i = in.nextInt();
				c = in.next();
				seg.updateValue(s.toCharArray(), s.length(), i, c.charAt(0));
			}
			else if(o==2) {
				l = in.nextInt();
				r = in.nextInt();
				int v=seg.getDistinct(s.length(), l, r);
				ans.append(v+"\n");
			}
		}
		System.out.println(ans);
	}

}

