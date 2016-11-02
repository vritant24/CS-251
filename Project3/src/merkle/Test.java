package merkle;

import java.io.File;

import merkle.implementation.MerkleTree;

public class Test {
	public static void main(String[] args) {
		MerkleTree mt = new MerkleTree();
		File fd = new File("/Users/Luffy/Desktop/CS 251/project3/src/merkle/merkle_tree");
		System.out.println(fd.getPath());
//		try {
//			mt.build(fd);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
