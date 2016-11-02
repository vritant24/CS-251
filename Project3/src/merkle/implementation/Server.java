package merkle.implementation;

import merkle.IMerkleTree;
import merkle.IServer;
import merkle.IMerkleTree.NodeType;

import java.util.LinkedList;
import java.util.List;


/**
 * TASK 2
 * TODO: IMPLEMENT generateResponse
 *
 * @author Vritant Bhardwaj
 * @pso 17
 * @date 10/20/16
 */
public class Server extends IServer {

    /**
     * Given a node to verify identified by <i>blockToTest</i>
     * which corresponds to the node received by calling <i>merkleTree.getNode(blockToTest)</i>
     * this function generates the path siblings which are required for verification
     * The returned list should contains Nodes in order from node to the root, i.e. bottom up
     */
    public List<IMerkleTree.Node> generateResponse(int blockToTest) {
        List<IMerkleTree.Node> verificationList = new LinkedList<>();
        
        IMerkleTree.Node node = merkleTree.getNode(blockToTest);
        
        //add node at given index to the list
        verificationList.add(merkleTree.getNode(blockToTest));
        
        int i = blockToTest;
        //check if blockToTest is dummy
        if(i == 0) {
        	return verificationList;
        }
        
        //check if blockToTest is root
        if(i == 1) {
    		verificationList.add(merkleTree.getNode(1));
    		return verificationList;
    	}
        
        //add the path siblings of node at blockToTest to verificationList
        while (true) {
        	if(node.getType().equals(NodeType.left)) {
        		verificationList.add(merkleTree.getNode(i + 1));
        	} else {
        		verificationList.add(merkleTree.getNode(i - 1));
        	}
        	if((i / 2) <= 1) {
        		break;
        	}
        	i = i/2;
        	node = merkleTree.getNode(i);
        }

        return verificationList;
    }
}
