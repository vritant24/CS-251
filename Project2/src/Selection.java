import java.util.Arrays;
import java.util.Stack;

/**
 * Selection.java, you must SUBMIT this file.
 * Do not modify any variable definition
 *
 * Multiple Implementations of Selection sort involving Magic Boxes
 *
 * Vritant Bhardwaj
 * PSO 17
 * 14th October 2016
 * Collaborators - Ivan Chan
 */

public class Selection{
	public MagicBox magicBox = new MagicBox();
	public int count;
	
	/**
	 * sortSelection
	 * A standard selection sort
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortSelection(int[] array) {
        // a for loop to control the number of elements that finish sorting
        for (int left = 0; left < array.length - 1; left++) {
            int right = array.length - 1;
            int min = right; //index of the minimum element
	    
	    	// from last to first unsorted element, find the min
            // and place it into the first unsorted position
            while (right >= left) {
                if(array[right] < array[min]){
				    min = right;
				}
				count++;
			right--;
	    	}
            
			if(min!=left){
				int temp = array[left];
				array[left] = array[min];
				array[min] = temp;
			}
        }
	}
	
	/**
	 * sortSelection8Sort
	 * Selection Sort the array using the 8-Sort Box
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortSelection8Sort(int[] array) {
		//return if only one element
		if(array.length == 1) {
			return;
		}
		
		int[] magicArray = new int[8]; //array to pass through to eightSort 
		int extra = array.length % 4;
		int[] newArray = new int[array.length + (4 - extra)]; //auxiliary array padded to be of a length that is a multiple of 4
		
		int i = 0;
		//copy elements into auxiliary array and pad with MAX_VALUE if needed
		for(; i < array.length; i++) {
			newArray[i] = array[i];
		}
		for(; i < newArray.length; i++) {
			newArray[i] = Integer.MAX_VALUE;
		}

		int index = 0; //index to add the element at in array

		//go through newArray and add 4 minimum elements at a time to array
		for(i = 0; i < newArray.length - 3;) {
			
			//add first 4 elements to magicArray
			for(int k = 0; k < 4; k++) {
				magicArray[k] = newArray[i++];
			}
			
			//go through the rest of the elements, and find the smallest 4
			for(int j = i; j < newArray.length;) {
				for(int k = 4; k < 8; k++) {
					magicArray[k] = newArray[j++];
				}
				magicArray = sortArray(magicArray, magicBox.eightSort(magicArray));
				count++;
			}
			
			//check if there are no more elements to sort. If none, sort those and add to array and break from the while loop
			if(i == newArray.length) {
				for(int k = 4; k < 8; k++) {
					magicArray[k] = Integer.MAX_VALUE;
				}
				magicArray = sortArray(magicArray, magicBox.eightSort(magicArray));
				count++;
				for(int k = 0; k < extra; k++) {
					array[index++] = magicArray[k];
				}
				break;
			}
			//add the 4 minimum elements to the array 
			for(int k = 0; k < 4; k++) {
				array[index++] = magicArray[k];
			}
			//make sure that the 4 minimum elements aren't used again
			takeCareOfArray(newArray, magicArray, i - 4);
		}
	}
	
	/**
	 * sortArray
	 * 
	 * retuns the sorted version of magicArray
	 * @param a is magicArray
	 * @param b contains the indices of the elements for the sorted array
	 * @return sorted magic Array
	 */
	public int[] sortArray(int[] a, int[]b) {
		int[] x = new int[8];
		for(int i = 0; i < 8; i++) {
			x[i] = a[b[i]];
		}
		return x;
	}
	
	/**
	 * takeCareOfArray
	 * 
	 * swaps the 4 minimum elements from magicArray in the newArray to indices n to n + 3
	 * @param a newArray
	 * @param b magicArray
	 * @param n index to swap from in newArray
	 */
	public void takeCareOfArray(int[] a, int[] b, int n) {
		for(int i = 0; i < 4; i++) {
			for(int j = n; j < a.length; j++) {
				if(a[j] == b[i]) {
					int temp = a[n];
					a[n] = a[j];
					a[j] = temp;
					n++;
					continue;
				}
			}
		}
	}
	
	/**
	 * TODO: sortSelection8Min
	 * Selection Sort the array using the 8-Min Box
	 *
	 * @param array: The array to be sorted.
	 */
	public void sortSelection8Min(int[] array) {
		// return if array length is 1
		if(array.length == 1) {
			return;
		}
		
		int[] magicArray = new int[8]; // array to pass into eightMin
		int extra = (array.length) % 7;
		int[] newArray = new int[array.length + (7 - extra) + 7]; // auxiliary array padded with MAX_VALUE to be a multiple of 7
		int minIndex = 0; // tracks index of min
		int min = array[0]; // stores minimum element
		
		//store array in newArray, pad with MAX_VALUE
		int k;
		for(k = 0; k < array.length; k++) {
			newArray[k] = array[k];
		}
		for(; k < newArray.length; k++) {
			newArray[k] = Integer.MAX_VALUE;
		}
		
		//go through newArray in increments of 7, and store min at i in every iteration
		for(int i = 0; i < newArray.length - 1; i++) {
			min = newArray[i];
			for(int j = i + 1; j < newArray.length - 7;) {
				magicArray[0] = min;
				for(k = 1; k < 8; k++) {
					magicArray[k] = newArray[j++];
				}
				minIndex = magicBox.eightMin(magicArray);
				count++;
				min = magicArray[minIndex];
			}
			swap(newArray, min, i);
		}
		
		//store sorted newArray in array
		for(int i = 0; i < array.length; i++) {
			array[i] = newArray[i];
		}
	}
	
	/**
	 * swaps a in arr with element at index n
	 * @param arr newArray
	 * @param a min
	 * @param n index
	 */
	public void swap(int[] arr, int a, int n) {
		for(int i = n; i < arr.length; i++) {
			if(arr[i] == a) {
				int temp = arr[n];
				arr[n] = arr[i];
				arr[i] = temp;
				return;
			}
		}
	}
}



/**
 * Selection.java, you should SUBMIT this file.
 * Do not modify any variable definition
 */


