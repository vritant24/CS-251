/**
 * WaterFlow
 * Water flow problem which start every empty cell in the first row.
 *
 * @author TODO: Vritant Bhardwaj.
 * @purdueid TODO: bhardwav
 * @PSO TODO: 17
 * @date TODO: September 2 2016.
 * @collaborators TODO: 
 */

import javax.swing.*;

import java.io.IOException;
import java.util.*;

public class WaterFlow {
    public int rows; //number of rows of the grid
    public int columns; //number of columns of grid
    public int[][] delayTimeGrid; //time of water in the grid, and 0 means the water is blocked
    public WaterFlowVisualization visualization;
    public Scanner s = new Scanner(System.in);
    public boolean visual = false;

    //TODO: add variables that you need
    int count = 0;
    /* The default constructor 
     * Read Input from terminal, do not modify it
     * */
    public WaterFlow() {
        //get inputs
        rows = s.nextInt();
        columns = s.nextInt();
        
        //check if rows or columns is 0
        if(rows <= 0 || columns <= 0) {
        	System.out.println("ERROR : number of rows or columns cannot be less than or equal to zero");
        	System.exit(0);
        }
        
        delayTimeGrid = new int[rows][columns];

        //TODO: Project 0: Read in the values for the delayTimeGrid using the scanner s
        // and store them in the correct position
        
        //add elements to array and check if number of elements are less than required
        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < columns; j++) {
        		try{
	        		delayTimeGrid[i][j] = s.nextInt();
	        		
        		}
        		catch(NoSuchElementException d) {
        			System.out.println("ERROR : array not complete or input invalid");
        			System.exit(0);
        		}
        	}
        }
        
        //check if elements are more than required
        if(s.hasNextInt()) {
        	System.out.println("ERROR : more elements than needed");
        	System.exit(0);
        }
    }


    /**
     * Create the Visualization of the Waterflow
     */
    void visualize() {
        visualization = new WaterFlowVisualization(this);

        JFrame frame = new JFrame("Water Flow Visualization");
        frame.add(visualization);
        frame.pack();
        visualization.init();
        visualization.start();
        frame.setSize(rows*20+15,columns*20+25);
        frame.setVisible(true);
    }

    /*
     * Prints out the delay time grid
     * Separate individual integers with spaces
     * End each line with a newline character ('\n')
     */
    void printDelayGrid()
    {
	//TODO: Complete the print function
    	System.out.println(this.rows + "" + this.columns);
    	for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.columns; j++) {
    			System.out.print(delayTimeGrid[i][j] + " ");
    		}
    		System.out.println("");
    	}
    	
    }

	public boolean isVisual() {
		return visual;
	}


	public void setVisual(boolean visual) {
		this.visual = visual;
	}


	public int[][] getDelayTimeGrid() {
		// TODO Auto-generated method stub
		return delayTimeGrid;
	}


	public int getRows() {
		// TODO Auto-generated method stub
		return rows;
	}


	public int getColumns() {
		// TODO Auto-generated method stub
		return columns;
	}


}

