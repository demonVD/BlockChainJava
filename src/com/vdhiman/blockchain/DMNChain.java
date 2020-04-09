package com.vdhiman.blockchain;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class DMNChain {
	
	public static List<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	public static void main(String[] args) {
		
		blockchain.add(new Block("First Block 1", "0"));
		System.out.println("Mining Block 1");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("First Block 2", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Mining Block 2");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("First Block 3", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Mining Block 3");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("Blockchain is valid :: " + isChainValid());
		
		String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockChainJson);
	}
	
	public static Boolean isChainValid() {
		
		Block curr = null;
		Block prev = null;
		String target = new String(new char[difficulty]).replace('\0','0');
		
		for(int i = 1; i < blockchain.size(); i++) {
			
			prev = blockchain.get(i-1);
			curr = blockchain.get(i);
			
			if(!curr.hash.equals(curr.calculateHash())){
				System.out.println("Hash of Current Node doesnt match");
				return false;
			}
			
			if(!prev.hash.equals(curr.previousHash)) {
				System.out.println("Prev Hash Doesnt Match");
				return false;
			}
			
			if(!curr.hash.substring(0, difficulty).equals(target)) {
				System.out.println("Block is not mined");
				return false;
			}
		}
		return true;
	}

}
