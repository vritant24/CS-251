/**
 * Sample Test Program
 * This file will be replaced for final Grading! Do not submit it.
 * This file does NOT contains all the test cases and corner cases.
 *
 */


public class Test {
    /**
     * It's a sample main, you can leave it blank.
     */

    public static void main(String[] args) {
//        WaterFlow WF = new WaterFlow();
//        if (WF.isVisual()) WF.visualize();
//	WF.printDelayGrid();
    	
    	int mysum = 0, n = 351;
    	for(int i = n; i >= 1; i = i/2) {
    		for(int j = 1; j <= i; j++) {
    			mysum++;
    		}
    	}
    	System.out.println(mysum);
    }
}

