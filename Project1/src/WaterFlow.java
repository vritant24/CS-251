/**
 * WaterFlow
 * Water flow problem which start every empty cell in the first row.
 *
 * @author TODO: Vritant Bhardwaj.
 * @CS login ID: TODO: bhardwav
 * @PSO section: TODO: 17
 * @date TODO: Put the date of completion here.
 */

import javax.swing.*;

import java.util.*;

public class WaterFlow {
    public int rows; //number of rows of the grid
    public int columns; //number of columns of grid
    public int[][] delayTimeGrid; //time of water in the grid, and 0 means the water is blocked
    public int[][] reachTimeGrid; //time of water flow reach that point
    public boolean[][] earliestPathGrid; //a boolean grid that identify the shortest path. For visualization purpose
    public List<Cell> earliestPath; //The earliest flow path
    public WaterFlowVisualization visualization;
    public Scanner s = new Scanner(System.in);
    public boolean visual = true;
    
    //TODO: add variables that you need
    public LinkedList<Cell>[] list; // Array of linked lists
    public int count; // counter that says which list from 'list' is currently active
    
    
    /* The default constructor 
     * Read Input from terminal, do not modify it
     * */
    @SuppressWarnings("unchecked")
	public WaterFlow() {
        //get inputs
        rows = s.nextInt();
        columns = s.nextInt();
        delayTimeGrid = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                delayTimeGrid[i][j] = s.nextInt();
            }
        }

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
        
        //initialize list
        list = new LinkedList[4];
        for(int i = 0; i < 4; i++) {
        	list[i] = new LinkedList<>();
        }
        
        //set count variable to 0
        count = 0;
    }

    
    /**
     * Update the water flow once.
     *
     * @return Null
     */
    public void flow() {
        // Don't Change this part, it's visualize Part
        if (visual) try {
            Thread.sleep(100);
            //count++;
            //if (count == 5) Thread.sleep(20000);
            visualization.repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //TODO: Start your implementation of progress here
        
        //Traverse through current list
        while(list[count].size() != 0) {
        	Cell cell = list[count].getFirst();
        	
        	// check to the left of the cell
        	if (cell.column > 0) {
        		
        		//check if it is blocked
        		if(delayTimeGrid[cell.row][cell.column - 1] != 0) {
        			
        			//check if it is not occupied
        			if(reachTimeGrid[cell.row][cell.column - 1] == -1) {
        				
        				//add to reachTimeGrid cell
        				reachTimeGrid[cell.row][cell.column - 1] = delayTimeGrid[cell.row][cell.column] + reachTimeGrid[cell.row][cell.column];
        				
        				//add cell to the respective list
        				Cell newCell = new Cell(cell.row,cell.column - 1);
        					list[(count + delayTimeGrid[newCell.row][newCell.column]) % 4].add(newCell);
        			}
        		}
        	}
        	
        	//check to the right of the cell
        	if (cell.column < (columns - 1)) {
        		
        		//check if it is blocked
        		if(delayTimeGrid[cell.row][cell.column + 1] != 0) {
        			
        			//check if it is not occupied
        			if(reachTimeGrid[cell.row][cell.column + 1] == -1) {
        				
        				//add to reachTimeGrid cell
        				reachTimeGrid[cell.row][cell.column + 1] = delayTimeGrid[cell.row][cell.column] + reachTimeGrid[cell.row][cell.column];
        				
        				//add cell to the respective list 
        				Cell newCell = new Cell(cell.row,cell.column + 1);
        					list[(count + delayTimeGrid[newCell.row][newCell.column]) % 4].add(newCell);
        			}
            	}
        	}
        	
        	//check to the bottom of the cell
        	if (cell.row < (rows - 1)) {
        		
        		//check if it is blocked
        		if(delayTimeGrid[cell.row + 1][cell.column] != 0) {
        			
        			//check if it is not occupied
        			if(reachTimeGrid[cell.row + 1][cell.column] == -1) {
        				
        				//add to reachTimeGrid cell
        				reachTimeGrid[cell.row + 1][cell.column] = delayTimeGrid[cell.row][cell.column] + reachTimeGrid[cell.row][cell.column];
        				
        				//add cell to the respective list
        				Cell newCell = new Cell((cell.row + 1),cell.column);
        					list[(count + delayTimeGrid[newCell.row][newCell.column]) % 4].add(newCell);
        			}
        		}
        	}
        	
        	//remove cell from list
        	list[count].removeFirst();
        }
        
        //set count to the next list
        count = (count + 1) % 4;
        
    }

    /**
     * Calculate the waterflow until it ends.
     */
    public void determineFlow() {
        //TODO: Fill in the condition of the while loop
    	
    	//store the cells in linked lists according to their delay times from the zeroth row.
        for (int j = 0; j < this.columns; j++) {
        	
        	if (delayTimeGrid[0][j] == 1) {
        		Cell cell = new Cell(0, j);
        		list[0].add(cell);
        	}
        	else if (delayTimeGrid[0][j] == 2) {
        		Cell cell = new Cell(0, j);
        		list[1].add(cell);
        	}
        	else if(delayTimeGrid[0][j] == 3){
        		Cell cell = new Cell(0, j);
        		list[2].add(cell);
        	}
        }
        
        while (true) {
            this.flow();
            
            //exit the loop when all lists are empty
            if (list[0].isEmpty() && list[1].isEmpty() && list[2].isEmpty() && list[3].isEmpty()) {
            	break;
            }
        }

    }

    /**
     * Create the Visualization of the Waterflow
     */
    public void visualize() {
        visualization = new WaterFlowVisualization(this);

        JFrame frame = new JFrame("Water Flow Visualization");
        frame.add(visualization);
        visualization.init();
        visualization.start();
        frame.setSize(visualization.getSize());
        frame.setVisible(true);
    }

    /**
     * Find one shortest path and update the shortestGrid.
     * 
     */
    public void earliestFlowPath() {
       //TODO: implement the earliest path method
    	
    	//if the flow doesn't reach the end of the grid, return empty list
    	if(!isReached()) {
    		return;
    	}
    	
    	int track = -1; //tracks cell of the shortest delay to exit
    	
    	int col = 0; // track column of the shortest delay to exit
    	
    	
    	//Find the cell with the shortest time to exit the waterflow
    	for(int j = 0; j < columns; j++) {
    		if(reachTimeGrid[rows - 1][j] != -1) {
    			if (track == -1) {
    				col = j;
    				track = reachTimeGrid[rows - 1][j] + delayTimeGrid[rows - 1][j];
    				continue;
    			}
    			else if ((reachTimeGrid[rows - 1][j] + delayTimeGrid[rows - 1][j]) < track) {
    				track = reachTimeGrid[rows - 1][j] + delayTimeGrid[rows - 1][j];
    				col = j;
    			}
    		}
    	}
    	
    	//Add the cell to earliest path
    	Cell cell = new Cell(rows - 1, col);
    	earliestPath.add(0, cell);
    	
    	//Add cell above the previous cell to earliest path
    	cell = new Cell(rows - 2, col);
    	earliestPath.add(0, cell);
    	
    	int trackCol = col; //tracks column of the current cell 
    	int trackRow = rows - 2; //tracks row of the current cell
    	
    	//add the cells to earliestPath, using findSmallest() to find the cells
    	while(trackRow > 0) {
    		Cell temp = findSmallest(trackRow, trackCol);
    		trackRow = temp.row;
    		trackCol = temp.column;
    		earliestPath.add(0, temp);
    	}
    	
    	//Set earliest path in earliestPathGrid
    	for(int i = 0; i < earliestPath.size(); i++) {
    		Cell temp = earliestPath.get(i);
    		earliestPathGrid[temp.row][temp.column] = true;
    	}
    	
    }

    /**
     * finds smallest delay on three sides (top, left and bottom)
     * using the given cell's row and column
     * @param i
     * @param j
     * @return
     */
    public Cell findSmallest(int i, int j) {
    	
    	Cell cell; //stores cell to return
    	int left = -1; //stores delay of the left cell
    	int right = -1; //stores delay of the right cell
    	int top = -1; //stores delay of the top cell
    	
    	//check if there is a left cell with water
    	if (j > 0)
    		left = reachTimeGrid[i][j - 1] + delayTimeGrid[i][j - 1];
    	
    	//check if there is a right cell with water
    	if (j < columns - 1)
    		right = reachTimeGrid[i][j + 1] + delayTimeGrid[i][j + 1];
    	
    	//check if there is a top cell with water
    	if (i > 0)
    		top = reachTimeGrid[i - 1][j] + delayTimeGrid[i - 1][j];
    	
    	
    	//if two of three possible cells are blocked 
    	if (left == -1 && right == -1) 
    		return cell = new Cell(i - 1, j);
    	
    	if (right == -1 && top == -1) 
    		return cell = new Cell(i, j - 1);
    	
    	if (left == -1 && top == -1) 
    		return cell = new Cell(i, j + 1);
    	
    	
    	//if one of three possible cells is blocked
    	if (left == -1) {
    		if (right < top)
    			return cell = new Cell(i, j + 1);
    		else 
    			return cell = new Cell(i - 1, j);
    	}
    	
    	if (right == -1) {
    		if (left <= top) 
    			return cell = new Cell(i, j - 1);
    		else 
    			return cell = new Cell(i - 1, j);
    	}
    	
    	if (top == -1) {
    		if(right < left) 
    			return cell = new Cell(i, j + 1);
    		else 
    			return cell = new Cell(i, j - 1);
    	}
    	
    	
    	//if all three cells are possibilities
    	if (left <= right && left <= top) 
    		cell = new Cell(i, j - 1);
    	
    	else if (right < top && right < left)
    		cell = new Cell(i, j + 1);
    	
    	else 
    		cell = new Cell(i - 1, j);
    	
    	return cell;
    }
    
    /**
     * Returns boolean indicating whether the flow has
     * reached the end of the grid
     * @return String 
     */
	public boolean isReached() {
		// TODO Auto-generated method stub
		
		//check if any cell on the last row is not a -1
		for(int j = 0; j < columns; j++) {
			if(reachTimeGrid[rows - 1][j] != -1) {
				return true;
			}
		}
		return false;
			
	}



}

