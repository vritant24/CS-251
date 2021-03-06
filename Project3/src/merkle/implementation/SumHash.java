package merkle.implementation;

import merkle.IMerkleTree;
import merkle.hash.HashFunction;

/**
 * TASK 3
 * TODO: IMPLEMENT hash
 *
 * @author Vritant Bhardwaj
 * @pso 17
 * @date 10/21/16
 */
public class SumHash implements HashFunction {

    /**
     * This function returns an integer (cast to a string) which is
     * the sum of all the bytes in the <i>input</i> String
     * You can cast bytes to integer using <i>(int)</i>
     * You can cast integers to String using <i>String.valueOf</i> 
     */
    @Override
    public String hashBlock(String input) throws Exception {
        int hash = 0; //stores hash value
        
        byte[] arr = input.getBytes(); // convert given string into an array of bytes
        
        // add all the elements of the bytes array and store in hash
        for(int i = 0; i < arr.length; i++) {
        	hash += (int) arr[i];
        	
        }
        
        //return hash
        return String.valueOf(hash);
    }

    @Override
    public String concatenateHash(MerkleTree.Node leftNode, MerkleTree.Node rightNode) throws Exception {
        return String.valueOf(Integer.parseInt(leftNode.getHash()) + Integer.parseInt(rightNode.getHash()));
    }
}
