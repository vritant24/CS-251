/*Search Tree class
 * Your implementation goes in this file
 */
import java.util.*;

public class SearchTree {
	
	private Node root; //The root of the RB Tree
	private JobTable jobs; //The jobTable
	//The following variables are used for measuring utilities, do not change them!
	private int[] machineLoads;
	private int numOfMachine;
	private int scheduled;
	private int requests;
	//You can add any other variables here if needed

	//Create a balanced search tree consisting of all the machines initially empty
	public SearchTree(int space, int numOfMachine) {
		jobs = new JobTable();
		scheduled = requests = 0;
		this.numOfMachine = numOfMachine;
		machineLoads = new int[numOfMachine];
		machineLoads[0] = 0;
		root = new Node(0, space, 0);
		for (int i = 1; i < numOfMachine; i++){
			root = RedBlackBST.insert(root, new Node(i,space, 0));

			machineLoads[i] = 0;
		}

	}

	public SearchTree(JobTable jt) {
		jobs = jt;
	}
	


	//Find the machine with just enough free space to schedule a job
	//Update the free size and number of jobs on the machine
	//Return machine id... -1 if no such machine exists
	public int scheduleJobMinSpace(int jobid, int size) {
		Node m; 
		requests++;
		/* Do not modify the code above */
		/* TODO: Start your implementation here, find m to schedule */
		
		if(count(size) == 0) {
			return -1;
		}
		
		Node tracker = root; // goes through the search tree to find machine
		Node bestOption = tracker; // stores best option
		
		while(tracker.left != null && tracker.right != null) {
			//check if current node holds same free space as size
			if(tracker.free ==  size) {
				bestOption = tracker;
				break;
			}
			//check if current node can hold job, if it can, see if it has lesser memory that bestOption
			if(tracker.free > size) {
				if(tracker.free < bestOption.free) {
					bestOption = tracker;
				}
				tracker = tracker.left;
				continue;
			}
			//if node doesn't have enough memory, go to the right
			tracker = tracker.right;		
		}
		
		
		m = new Node(bestOption.id, bestOption.free - size, bestOption.numjobs + 1); // create new node to update machine in which job will be added 
		RedBlackBST.delete(root, bestOption); // delete machine in which job was added
		RedBlackBST.insert(root, m); // add updated machine to tree
		
		jobs.addJob(jobid, size, m); // add new job to jobs 

		/* Do not modify the following part */
		scheduled++;
		machineLoads[m.id] ++;
		return m.id;
	}
	
	
	//Find the machine with enough free space and minimum number of jobs to schedule a job
	//Update the free size and number of jobs on the machine
	//Return machine id... -1 if no such machine exists
	public int scheduleJobMinJob(int jobid, int size) {
		
		Node m = null;

		/* TODO: Start your implementation here: Find node m to schedule the job  */


		/* Do not modify the following part */
		machineLoads[m.id] ++;
		requests++;
		scheduled++;
		return m.id;
	}
	

	
	//Update the free space and number of jobs on machine releasing a job
	public void releaseJob(int jobid) {

		Node m = jobs.jobMachine(jobid);
		/* TODO: Release m */

		machineLoads[m.id]--;
	}
	
	//Return the number of machines that have atleast given free space
	public int count(int free){
		
		/* TODO: start your implementation here */
		return countNodes(root, free);
	}
	
	/**
	 * Counts the number of nodes under the subtree of node given
	 * with free space mem
	 * @param node
	 * @param mem
	 * @return count of nodes with free space mem
	 */
	public int countNodes(Node node, int mem) {

		if(node.free < mem) {
			return countNodes(node.right, mem);
		}
		
		if(node.right == null) {
			return 1;
		}
		
		if(node.left == null) {
			return (1 + node.right.size);
		}
		return (1 + node.right.size + countNodes(node.left, mem));
	}
	
	/*
	* DO NOT EDIT THE FOLLOWING FUNCTION
	* IT IS INVOLVED IN MEASURING THE UTILITIES FOR EXPERIMENTAL SECTION
	*/
	public void measureUtility(){
		double ideal = 0.0;
		double medianload = 0.0;
		ArrayList<Integer> loads = new ArrayList<Integer>();
		int size = 0;
		for (int i = 0; i < numOfMachine; i ++){
			int load = machineLoads[i];
			loads.add(load);
			size+=load;
		}

		int len = loads.size();

		Collections.sort(loads);
		if (size%2 == 0) {
			medianload = loads.get(len/2);
		}
		else {
			medianload = (loads.get(len/2) + loads.get(len/2+1))/2;
		}
		System.out.println(size);
		ideal = size/(double)numOfMachine;
		double fairness = medianload/ideal;
		double thoroughput = scheduled/(double)requests;
		System.out.format("Fairness: %f, Thoroughput: %f\n",fairness,thoroughput);
	}
	/*
	* DO NOT EDIT THE FUNCTION ABOVE
	*/
}