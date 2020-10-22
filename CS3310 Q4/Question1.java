import java.util.Random;

public class Question1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		int setOfSizes[] = {10,100,1000};
		int numOfArrays = 10000;
		Random rand = new Random();
		long duration;
		double[] avgTime = new double[numOfArrays];
		double avg;
		
		for(int index = 0; index < setOfSizes.length; index++) 
		{
			
			for(int jindex = 0; jindex < numOfArrays;jindex++) 
			{
				int array[] = new int[setOfSizes[index]];
				
				
				randomfy(array,rand);
				duration = beginTest(array);
				
				avgTime[jindex]=(double)(duration/1000);//log for average
				
			}


			System.out.format("The average time to solve an array of size %d  is %f ms \n",setOfSizes[index], calcAvg(avgTime));
		}
	}

	private static double calcAvg(double[] avgTime) {
		double avg=0;
		for(int i=0;i<avgTime.length;i++) {
			avg +=avgTime[i];
		}
		return avg/avgTime.length;	
	}

	private static long beginTest(int[] array) {
		long start = 0;
		long end = 0;
		
		start = System.nanoTime();//TimeStart.
		setOrderToPeaksAndVals(array); //function to be tested.
		end = System.nanoTime();//TimeStop.
		
		return end - start;

	}

	private static void randomfy(int[] array, Random rand) {
		
		
		for(int i = 0; i < array.length; i++)
		{
			array[i] =rand.nextInt();
		}
		
	}

	private static int[] setOrderToPeaksAndVals(int[] array) {
		
		mergeSort(array,0,array.length-1); //O(nlogn)

		
		int finishedArray[] = new int[array.length];
		
		int right = array.length-1;
		int left = 0;
		boolean peak = true;
		
		for(int i = 0; i <array.length;i++) { //O(n)
			if(peak) {
				finishedArray[i]=array[right--];
				peak = false;
			}else {
				finishedArray[i]=array[left++];
				peak = true;
			}
		}
		return finishedArray;
	}

	private static void mergeSort(int[] array, int l, int r) {
		
		if (l < r) { 
            // Find the middle point 
            int m = (l + r) / 2; 
  
            // Sort first and second halves 
            mergeSort(array, l, m); 
            mergeSort(array, m + 1, r); 
  
            // Merge the sorted halves 
            merge(array, l, m, r); 
        } 
	}

	private static void merge(int[] array, int l, int m, int r) {
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int[n1]; 
        int R[] = new int[n2]; 
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) 
            L[i] = array[l + i]; 
        for (int j = 0; j < n2; ++j) 
            R[j] = array[m + 1 + j]; 
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) { 
                array[k] = L[i]; 
                i++; 
            } 
            else { 
                array[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) { 
            array[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) { 
            array[k] = R[j]; 
            j++; 
            k++; 
        } 
	}

	private static void printArray(int[] array) {
		
		for(int i = 0;i<array.length;i++) 
		{
			System.out.format("[%d]", array[i]);
		}
	}

}
