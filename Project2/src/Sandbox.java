import java.util.Arrays;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.RETURN;


public class Sandbox {
	static MagicBox magicBox = new MagicBox();
	
	public static void main(String[] args) {
//		Merge merge = new Merge();
//		Selection selection = new Selection();
//		int[] array = readInput();
//		merge.sortMerge8Sort(array);
//		System.out.println(merge.count);
		
		System.out.println(String.valueOf(9));
	}
	
	public static int[] readInput(){
		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
		    array[i] = s.nextInt();
		}

		return array;
	}
}