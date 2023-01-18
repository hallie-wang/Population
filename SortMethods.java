/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author 
 *	@since	
 */
 
import java.util.ArrayList;

public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of City objects to sort
	 */
	public void bubbleSort(ArrayList<City> arr) 
	{
		for (int outer = arr.size() - 1; outer > 0; outer--)
		{
			for (int inner = 0; inner < outer; inner++)
			{
				if (arr.get(inner).compareTo(arr.get(inner + 1)) > 0)
					swap(arr, inner, inner + 1);
			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(ArrayList<City> arr, int x, int y) 
	{
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of City objects to sort
	 */
	public void selectionSort(ArrayList<City> arr) 
	{
		int n = arr.size();
        for (int outer = 0; outer < arr.size() - 1; outer++)
        {
            // Find the minimum element in unsorted array
            int minIndex = outer;
            for (int inner = outer + 1; inner < arr.size(); inner++)
                if (arr.get(inner).compareTo(arr.get(minIndex)) < 0)
                    minIndex = inner;
 
            // Swap the found minimum element with the first element
            swap(arr, minIndex, outer);
        }
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of City objects to sort
	 */
	public void insertionSort(ArrayList<City> arr) 
	{
		for (int outer = 1; outer < arr.size(); outer++)
		{
			City temp = arr.get(outer);
			int i = outer;
			while(i > 0 && temp.getName().compareTo(arr.get(i - 1).getName()) < 0)
			{
				arr.set(i, arr.get(i - 1));
				i--;
			}
			arr.set(i, temp);
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of City objects to sort
	 * 	@param l		left bound
	 * 	@param r		right bound
	 * 	@param m 		middle of array
	 * 	@param c		choice
	 */
	public void merge(ArrayList<City> arr, int l, int m, int r, int c)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        ArrayList<City> left = new ArrayList<City>(n1);
        ArrayList<City> right = new ArrayList<City>(n2);
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; i++) 
        {
			left.add(arr.get(l + i));
		}
        for (int j = 0; j < n2; j++)
			right.add(arr.get(m + 1 + j));
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) 
        {
			if (c == 4)
			{
				if (left.get(i).getName().compareTo(right.get(j).getName()) > 0) 
				{
					arr.set(k, left.get(i));
					i++;
				}
				else 
				{
					arr.set(k, right.get(j));
					j++;
				}
				k++;
			}
			else
			{
				if (left.get(i).compareTo(right.get(j)) >= 0) 
				{
					arr.set(k, left.get(i));
					i++;
				}
				else 
				{
					arr.set(k, right.get(j));
					j++;
				}
				k++;
			}
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        {
			arr.set(k, left.get(i));
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        {
			arr.set(k, right.get(j));
            j++;
            k++;
        }
    }
 
    /**
	 *	Merge Sort algorithm - in descending order (you implement)
	 *	@param arr		array of City objects to sort
	 * 	@param l		left bound
	 * 	@param r		right bound
	 * 	@param c		choice
	 */
    public void mergeSort(ArrayList<City> arr, int l, int r, int c)
    {
        if (l < r) 
        {
            // Find the middle point
            int m = l + (r - l) / 2;
 
            // Sort first and second halves
            mergeSort(arr, l, m, c);
            mergeSort(arr, m + 1, r, c);
 
            // Merge the sorted halves
            merge(arr, l, m, r, c);
        }
    }
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of City to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(ArrayList<City> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %4d", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr.get(a));
			else System.out.printf(", %4d", arr.get(a));
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
	//	se.run();
	}
	
/*	public void run() {
		List<City> arr = new ArrayList<City>();
		// Fill arr with random numbers
	//	for (int a = 0; a < 10; a++)
	//		arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
	//	for (int a = 0; a < 10; a++)
	//		arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
	//	for (int a = 0; a < 10; a++)
	//		arr[a] = (int)(Math.random() * 100) + 1;
/*		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
	/*	for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}*/
}
