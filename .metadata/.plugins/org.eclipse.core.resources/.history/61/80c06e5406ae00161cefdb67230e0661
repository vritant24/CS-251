package merkle.implementation;

import merkle.Configuration;
import merkle.IMerkleTree;

import java.io.*;

import static merkle.Configuration.blockSize;
import static merkle.Configuration.hashFunction;

/**
 * TASK 1
 * TODO: IMPLEMENT build
 *
 * @author John Doe
 * @pso 1729
 * @date 10-23-2016
 */
public class MerkleTree extends IMerkleTree {


    /**
     * Given an <i>inputFile</i> this function builds a Merkle Tree and return the <i>masterHash</i>
     * <i>this.tree</i> is the array representation of the tree which you need to create
     * You can use <i>Configuration.hashFunction</i>
     * The basic code to read a file block wise is provided. You can choose to use it.
     * The tree should be 1-indexed
     */
    @Override
    public String build(File inputFile) throws Exception {
        int blocks = (int) Math.ceil((double) inputFile.length() / Configuration.blockSize);

        //It's specified in the handout that the internal nodes are going to be the number of blocks - 1
        int internalNodes = blocks - 1;

        //The total elements will be the number of internal nodes, the number of blocks and an extra zeroth block for 1 indexing
        tree = new Node[internalNodes + blocks + 1];
        tree[0] = new Node("dummy", 0);

        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(inputFile))) {
            byte[] byteArray = new byte[blockSize];
            int readStatus;

            //Since the number of internal nodes are known we know that the first block hash will be inserted just after
            int index = internalNodes;

            while ((readStatus = reader.read(byteArray)) != -1) {
                String block = padBytes(byteArray, readStatus);
                index++;
                tree[index] = new Node(hashFunction.hashBlock(block), index);
            }
        }

        //We start at the last internal node because both children need to be initialized in order to compute the parent
        for (int i = internalNodes; i > 0; i--) {
            Node left = tree[2 * i];
            Node right = tree[2 * i + 1];
            tree[i] = new Node(hashFunction.concatenateHash(left, right), i);
        }

        String masterHash = tree[1].getHash();
        //The master hash is the hash stored at the root and the root is at index 1
        return masterHash;
    }
}
