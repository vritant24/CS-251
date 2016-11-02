
/**
 * PackIt
 *
 * @author TODO: Vritant Bhardwaj.
 * @CS login ID: TODO: bhardwav
 * @PSO section: TODO: 17
 * @date TODO: 09/30/2016.
 */
public class PackIt {
	
	/**
	 * This method defines which boxes from the initial list you used for your max value box list
	 * @param k - max number of boxes a drone can carry
	 * @param w - max weight a drone can carry
	 * @param boxlist - boxes available for drone to take
	 * @return boolean array indicating whether you used (true) or did not use (false) a box at the specified index for your box list
	 */
	public static boolean[] boxesUsed(int k, int w, BoxList boxlist) {
		
		BoxList maxBoxList = packIt(boxlist, boxlist.size()-1, w, k);
		boolean[] boxesUsed = new boolean[boxlist.size()];
		/* Let boxesUsed[i] be true if you used boxlist.get(i) in your maxBoxList, false otherwise.
		 *
		 * Example:
		 * if you are given this boxlist:
		 * 		boxlist   = [box1,  box2, box3, box4]
		 * and you only use box2 and box3 in your maxBoxList; boxesUsed[] should be:
		 * 		boxesUsed = [false, true, true, false]
		 * 
		 * If you use no boxes in your maxBoxList because it is not possible, all values in boxesUsed should be false
		 * 
		 * Make sure they are in the same order as the boxes were initially given to you.
		 * 
		 * TODO Implement that mapping here. You may use Box.equals() for comparing if two boxes are equal.
		 */
		
		for(int i = 0; i < maxBoxList.size(); i++) {
			for (int j = 0; j < boxlist.size(); j++) {
				if(maxBoxList.get(i).equals(boxlist.get(j))){
					boxesUsed[j] = true;
					continue;
				}
			}
		}
		
		return boxesUsed;
	}
	
	/**
	 * Recursive function packIt
	 * @param boxlist - the boxlist given from input
	 * @param n - tracks index of boxes in boxlist
	 * @param w - tracks how much weight can still be added
	 * @param k - tracks number of boxes that can be added
	 * @return a boxlist with max value in regards to the weight limit and box limit of the drone.
	 */
	public static BoxList packIt(BoxList boxlist, int n, int w, int k) {
		// TODO Implement recursive method here.
		
		BoxList boxer = new BoxList(); //stores boxes to return
		
		// Base Cases 
		//if weight left is equal to zero OR
		//if number of boxes that can be added is 0 OR
		//if the index is at the first element
		//return empty list
		if(w == 0 || k == 0 || n < 0) {
			return boxer;
		}
		
		//if current boxes weight is more than the weight that can be added, return boxList without current box
		if(boxlist.get(n).weight > w) {
			return packIt(boxlist, (n - 1), w, k);
		}
		
		//compare boxlist with box n and without box n, store boxlist with greater totalValue
		BoxList option =  maxNumber( (boxlist.get(n)), packIt(boxlist, (n - 1), (w - boxlist.get(n).weight), (k - 1)),
				packIt(boxlist, n - 1, w, k) );
		
		//add boxlist with total value to boxer
		for(int i = 0; i < option.size(); i++) {
			boxer.add(option.get(i));
		}
		
		return boxer;
		
	}
	
	/**
	 * Compares two BoxLists and returns one with larger value 
	 * @param box - adds this to boxlist x
	 * @param x - boxlist to check with box n
	 * @param y - boxlist to check without box n
	 * @return
	 */
	public static BoxList maxNumber(Box box, BoxList x, BoxList y) {
		
		x.add(box);
		
		if(y.getTotalValue() > x.getTotalValue()) { 
			return y;
		}
		
		return x;
	}
}
