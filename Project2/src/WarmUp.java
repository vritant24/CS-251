import java.util.Arrays;

/*
 * WarmUp
 *
 * The class where you will implement all of 
 * the warm up methods for project 2. 
 *
 * You MUST SUBMIT this file.
 * Do NOT modify variable & method defintion.
 * TODO: Vritant Bhardwaj
 * TODO: PSO 17
 * TODO: 12th October 2016
 */

public class WarmUp {
	private MagicBox magicBox = new MagicBox();
	/**
	 * TODO: min8Min
	 *
	 * returns the minimum element of the given array as determined using 8Min
	 *
	 * @param array - the array of integers being checked
	 * @return min: the value of the least element in array
	 */
	public int min8Min(int[] array){
		int min = array[0]; // stores mininimum  
		int minIndex = 0; // tracks index of minimum
		int extra = ( (array.length - 1) % 7); // elements with indices more than % 7
		int index = 0;
		int[] minarray = new int[8]; //array to pass through to eighMin
		
		if(array.length >= 8) {
			//go through the array,and put compare 7 elements with a min in eightSort 
			for(int i = 1; i < array.length - extra; i = i + 7) {
				minarray[0] = min;
				index = 1;
				for(int j = i; j < i + 7; j++) {
					minarray[index++] = array[j];
				}
				minIndex = magicBox.eightMin(minarray);
				min = minarray[minIndex];
			}
		}
		index = 0;
		minarray[index++] = min; 
		//same as before, but for the extra elements, Pad if necessary
		for(int i = array.length - extra; i < array.length; i++) {
			minarray[index++] = array[i];
		}
		while(index < 8) {
			minarray[index++] = Integer.MAX_VALUE;
		}
		minIndex = magicBox.eightMin(minarray);
		if(min > minarray[minIndex]) {
			min = minarray[minIndex];
		}
		return min;
	}

	/**
	 * TODO: isSorted8Sort
	 *
	 * checks if the given array is sorted in increasing order using the 8-Sort Magic Box
	 *
	 * @param array - the array of integers being checked
	 * @return: true if sorted, false otherwise
	 */
	public boolean isSorted8Sort(int[] array){
		int[] minarray = new int[8];  // array to pass through to eightSort
		int extra = ((array.length - 1) % 7); // stores number of elements that have indices greater than % 7
		int index;
		int[] check = {0,1,2,3,4,5,6,7}; // array to compare indices with
		
		if(array.length >= 8) {
			//go through the array,and put compare 7 elements with a min in eightSort 
			for(int i = 0; i < array.length - extra - 1; i = i + 7) {
				index = 0;
				for(int j = i; j < i + 8; j++) {
					minarray[index++] = array[j];
				}
				//if the index array doesn't match check, the array isn't sorted
				if(isNotEqual(check, magicBox.eightSort(minarray))) {
					return false;
				}
			}
		}
		index = 0;
		//same as before, but for the extra elements, Pad if necessary
		for(int i = array.length - extra - 1; i < array.length; i++) {
			minarray[index++] = array[i];
		}
		while(index < 8) {
			minarray[index++] = Integer.MAX_VALUE;
		}
		minarray = magicBox.eightSort(minarray);
		for(int i = 0; i < extra; i++) {
			if(check[i] != minarray[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * isNotEqual
	 * 
	 * compares the given array
	 * 
	 * @param a array
	 * @param b array
	 * @return false if a != b, true otherwise
	 */
	public boolean isNotEqual(int[] a, int[] b) {
		for(int i = 0; i < 8; i++) {
			if(a[i] != b[i]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * TODO: isSorted8Min
	 *
	 * checks if the given array is sorted in increasing order using the 8-Min Magic Box
	 *
	 * @param array - the array of integers being checked
	 * @return: true if sorted, false otherwise
	 */
	public boolean isSorted8Min(int[] array){
		int[] minarray = new int[8]; // array to send to eightMin
		//Pad minarray with MAX_VALUE
		for(int i = 2; i < 8; i++) {
			minarray[i] = Integer.MAX_VALUE;
		}
		//go through given array comparing each consecutive element and make sure that it is increasing order. 
		//Return false if not
		for(int i = 0; i < array.length - 1; i++) {
			minarray[0] = array[i];
			minarray[1] = array[i + 1];
			
			if(magicBox.eightMin(minarray) != 0) {
				return false;
			}
		}
		
		return true;
	}


}
