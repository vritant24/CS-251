
public class Box {
	public int weight;
	public int value;
	public Box(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
	
	/**
	 * @param box
	 * @return true if the boxes have the same weight and value, false otherwise
	 */
	public boolean equals(Box box) {
		if (box == null) {
			return false;
		}
		if (this.weight != box.weight) {
			return false;
		}
		if (this.value != box.value) {
			return false;
		}
		
		return true;
	}
	
	public String toString() {
		return String.format("(weight = %d, value = %d)", this.weight, this.value);
	}
}
