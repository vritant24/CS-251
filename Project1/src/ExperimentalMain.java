import java.util.LinkedList;


public class ExperimentalMain {
	/*
    public static void main(String[] args) {
    	int row = 50;
    	int col = 200;
    	double p = 0.7;
    	GenerateRandomGrid random = new GenerateRandomGrid();
    	int i = 0;
    	int j = 0;
    	int counter = 0;
    	double average = 0;
    	
    	while(j < 100) {
	   
    		while (i < 100) {
		    	
	    		WaterFlow WF = new WaterFlow(random.generateGrid(row, col, p), row, col);
	    		
		        WF.determineFlow();
		        //WF.earliestFlowPath();
		        if(WF.isReached()) {
		        	counter++;
		        }
		        i++;
	    	}
    		//System.out.println("isReached -> " + counter);
	    	j++;
	    	average += counter;
	    	counter = 0;
	    	i = 0;
    	}
        System.out.println("here -> " + (average/100));

    }
    
    //
    public WaterFlow(int grid[][], int row, int col) {
        //get inputs
        rows = row;
        columns = col;

        delayTimeGrid = grid;
       

        //TODO: initialize the variables
        
        //initialize reachTimeGrid and set all elements to -1, except in row 0
        reachTimeGrid = new int[rows][columns];
        for(int i = 0; i < this.rows; i++) {
        	for (int j = 0; j < this.columns; j++) {
        		if(i == 0 && delayTimeGrid[0][j] != 0) {
        			reachTimeGrid[0][j] = 0;
        			continue;
        		}
        		reachTimeGrid[i][j] = -1;
        	}
        }
        
        //initialize earliestPath and earliestPathGrid
        earliestPath = new LinkedList<Cell>();
        earliestPathGrid = new boolean[rows][columns];
        
        //set hasEnded to false by default
        hasEnded = false;
        
        //initialize list
        list = new LinkedList[4];
        for(int i = 0; i < 4; i++) {
        	list[i] = new LinkedList<>();
        }
        
        //set count variable to 0
        count = 0;
    }
    */
}
