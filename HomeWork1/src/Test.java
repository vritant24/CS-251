import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BoxList boxlist = new BoxList();
		
		// max amount of boxes our drone can carry
		int maxBoxes = scanner.nextInt(); 
		
		// max weight our drone can carry
		int maxWeight = scanner.nextInt();
		
		// amount of boxes available to use
		int boxes = scanner.nextInt();
		for (int i = 0; i < boxes; i++){
			int boxWeight = scanner.nextInt();
			int boxValue = scanner.nextInt();
			boxlist.add(new Box(boxWeight, boxValue));
		}
		scanner.close();

		// get boxes used in max list
		boolean[] boxesUsedInMaxList = PackIt.boxesUsed(maxBoxes, maxWeight, boxlist);

		int weightUsed = 0;
		int boxesUsed = 0;
		int maxValue = 0;
		String maxBoxListString = "";
		for (int i = 0; i < boxesUsedInMaxList.length; i++) {
			if (boxesUsedInMaxList[i]) {
				boxesUsed++;
				Box b = boxlist.get(i);
				weightUsed += b.weight;
				maxValue += b.value;
				maxBoxListString += b.toString() + " ";
			}
		}

		System.out.printf("Max Weight Possible = %2d, Max Boxes Possible = %2d\n", maxWeight, maxBoxes);
		if (boxesUsed > 0) 
			System.out.printf("Max Value = %2d, Weight Used = %2d, Boxes Used = %d, List = %s\n", maxValue, weightUsed, boxesUsed, maxBoxListString);
		else
			System.out.println("Not Possible");
	}
}