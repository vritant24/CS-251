import java.util.Arrays;

/**
 * Merge.java, you MUST SUBMIT this file.
 *
 * Multiple Implementations of merge sort involving Magic Boxes
 *
 * Vritant Bhradwaj
 * PSO 17
 * 14th October 2016 
 * Collaborators - Ivan Chan
 */
public class Merge{
	public MagicBox magicBox = new MagicBox();  
	public int count; //Counter for the number of comparisons in the standard algorithm
	
	/**
	 * sortMerge
	 * A standard merge sort
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortMerge(int[] array) {
		//Implemented merge sort and increment "count" 
		//to keep track of the number of pairwise comparisons
		if (array.length <= 1) return;

		// Split the array in half
		int[] first = new int[array.length / 2];
		int[] second = new int[array.length - first.length];
		System.arraycopy(array, 0, first, 0, first.length);
		System.arraycopy(array, first.length, second, 0, second.length);

		// Sort each half
		sortMerge(first);
		sortMerge(second);

		// Merge the halves together, overwriting the original array
		standardMerge(first, second, array);
	}
   
	/**
	 * standardMerge
	 *
	 * merges two arrays back together for merge sort
	 *
	 * @param a the first array being merged
	 * @param b the second array being merged
	 * @param result the target array where the other two arrays are being merged
	 */
	private void standardMerge(int[] a, int[] b, int[] result) {
		// Merge both halves into the result array
		// Next element to consider in the first array
		int aIndex = 0;
		// Next element to consider in the second array
		int bIndex = 0;

		// Next open position in the result
		int j = 0;
		// As long as neither iFirst nor iSecond is past the end, move the
		// smaller element into the result.

		while (aIndex < a.length && bIndex < b.length) {
			count++;
			if (a[aIndex] < b[bIndex]) {
				result[j] = a[aIndex];
				aIndex++;
			} else {
				result[j] = b[bIndex];
				bIndex++;
			}
			j++;
		}
		// copy what's left
		System.arraycopy(a, aIndex, result, j, a.length - aIndex);
		System.arraycopy(b, bIndex, result, j, b.length - bIndex);
    }
	
	
	/**
	 * TODO: sortMerge8Sort
	 * Merge Sort the array using the 8-Sort Box
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortMerge8Sort(int[] array) {
		//if array.length is less than or equal to 8, sort using eightSort, return
		if (array.length <= 8) {
			int[] magicArray = new int[8]; // array to pass through to eightSort
			
			//add array's elements to magicArray, pad if necessary
			int i;
			for(i = 0; i < array.length; i++) {
				magicArray[i] = array[i];
			}
			for(; i < 8; i++) {
				magicArray[i] = Integer.MAX_VALUE;
			}
			
			//get sorted indices of magicArray
			magicArray = magicBox.eightSort(magicArray);
			//get sorted array using sorted indices
			magicArray = sortArray(array, magicArray);
			for(i = 0; i < array.length; i++) {
				array[i] = magicArray[i];
			}
			return;
		}
	
		// Split the array in half
		int[] first = new int[array.length / 2];
		int[] second = new int[array.length - first.length];
		System.arraycopy(array, 0, first, 0, first.length);
		System.arraycopy(array, first.length, second, 0, second.length);
		
		// Sort each half
		sortMerge8Sort(first);
		sortMerge8Sort(second);
	
		// Merge the halves together, overwriting the original array
		eightSortMerge(first, second, array);
	}
	
	/**
	 * 
	 * sortArray
	 * 
	 * returns an array which contains elements of a in sorted order
	 * 
	 * @param a   array to sort
	 * @param b	  indices of sorted elements in a
	 * @return
	 */
	public int[] sortArray(int[] a, int[]b) {
		int[] x = new int[8];
		for(int i = 0; i < a.length; i++) {
			x[i] = a[b[i]];
		}
		return x;
	}
	
	/**
	 * eightSortMerge
	 * 
	 * performs Merge Sort using magic boxes
	 * 
	 * @param a
	 * @param b
	 * @param result
	 */
	private void eightSortMerge(int[] a, int[] b, int[] result) {
		// Merge both halves into the result array
		// Next element to consider in the first array
		int aIndex = 0;
		// Next element to consider in the second array
		int bIndex = 0;
		//array to pass to eightSort
		int[] magicArray = new int[8];
		// Next open position in the result
		int index = 0;
		int aCount;
		int bCount;
		int j = 0;
		// As long as neither iFirst nor iSecond is past the end, move the
		// smaller element into the result.
		while(true) {
			//store elements in magicArray, pad if necessary
			aCount = 0; 
			bCount = 0;
			int[] tempA = new int[4];
			int[] tempB = new int[4];
			for(j = 0; j < 4 && aIndex < a.length; j++) {
				magicArray[j] = a[aIndex++]; 
				tempA[j] = magicArray[j];
				aCount++;
			}
			for(; j < 4; j++) {
				magicArray[j] = Integer.MAX_VALUE;
			}
			for(j = 4; j < 8 && bIndex < b.length; j++) {
				magicArray[j] = b[bIndex++]; 
				tempB[j - 4] = magicArray[j];
				bCount++;
			}
			for(; j < 8; j++) {
				magicArray[j] = Integer.MAX_VALUE;
			}
			
			//store sorted magicArray in itself
			magicArray = sortArray(magicArray, magicBox.eightSort(magicArray));
			
			//check which subarray's last element came first
			int check = whichComesFirst(magicArray, a[aIndex - 1], b[bIndex - 1], aCount, bCount);
			
			
			//if it is in a, move bIndex back to elements it didn't put in new sorted array, else vice-verse for b
			if(check == a[aIndex - 1]) {
				
				int count = 0; //counts number of properly sorted elements
				
				int tempIndex = aIndex - aCount; //index of the first element from a that was put into magicArray
				
				//find number of elements used from b, decrement bIndex by elements not used
				for(int k = 0; k < 8; k++) {
					count++;
					result[index++] = magicArray[k];
					
					if(magicArray[k] == a[tempIndex]) {
						if(a[tempIndex++] == a[aIndex - 1]) break; //break if one of the last elements added, from a or b, found
					}
				}
				if(count == aCount) {
					bIndex -= bCount;
				}
				else {
					bIndex = bIndex - (bCount - (count - aCount));
				}
			}else {
				int count = 0; //counts number of properly sorted elements
				int tempIndex = bIndex - bCount; //index of the first element from a that was put into magicArray
				
				//find number of elements used from b, decrement bIndex by elements not used
				for(int k = 0; k < 8; k++) {
					count++;
					result[index++] = magicArray[k];
					
					if(magicArray[k] == b[tempIndex]) {
						if(b[tempIndex++] == b[bIndex - 1]) break; //break if one of the last elements added, from a or b, found
					}
				}
				if(count == bCount) {
					aIndex -= aCount;
				}
				else {
					aIndex = aIndex - (aCount - (count - bCount));
				}
			}
			
			//if index reaches the last element at any point, break;
			if(aIndex == a.length || bIndex == b.length) {
				break;
			}
		}
		
		// copy what's left
		System.arraycopy(a, aIndex, result, index, a.length - aIndex);
		System.arraycopy(b, bIndex, result, index, b.length - bIndex);
    }
	
	/**
	 * 
	 * whcihComesFirst
	 * 
	 * returns either m or n, whichever comes first in arr
	 * @param arr	magicArray
	 * @param m	  last element from a
	 * @param n	  last element from b
	 * @param mCount   number of elements from a inserted
	 * @param nCount   number of elements from b inserted
	 * @return
	 */
	public int whichComesFirst(int[] arr, int m, int n, int mCount, int nCount) {
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			count++;
			if(arr[i] == m && count >= mCount) {
				return m;
			}
			else if(arr[i] == n && count >= nCount) {
				return n;
			}
		}
		return m;
	}
}

 /* Merge.java, you MUST SUBMIT this file. 
  * Do not modify any variable definition
  */
