import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class Shooting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int i,n,ans=0;
		n = in.nextInt();
		int a[] = new int[n];
		int order[] = new int[n];
		for(i=0;i<n;i++) {
			a[i] = in.nextInt();
			order[i] = i;
		}
		sort(a,0,n-1,order);
		int k =0;
		for(i=0;i<n;i++) {
			ans += k*a[i]+1;
			k++;
		}
		System.out.println(ans);
		for(i=0;i<n;i++)
			System.out.print((order[i]+1)+" ");
	}
	static int partition(int arr[], int low, int high, int o[]) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] > pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
                int t = o[i];
                o[i]=o[j];
                o[j]=t;
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
        int t = o[i+1];
        o[i+1]=o[high];
        o[high]=t;
  
        return i+1; 
    } 
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    static void sort(int arr[], int low, int high,int o[]) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high,o); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1,o); 
            sort(arr, pi+1, high,o); 
        } 
    } 

}
