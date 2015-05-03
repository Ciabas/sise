import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

import Tree.Node;

public class Order {
	
	public char[] rowHeuristic(Node<SingleMove> node){
		Hashtable<Integer, String> hash = conflictsInMoves(node);
		return getMoves(hash);
	}
	
	private Hashtable<Integer, String> conflictsInMoves(Node<SingleMove> node){
		Hashtable<Integer, String> conflictHash = new Hashtable<Integer, String>();
		// dla ka¿dej mo¿liwoœci ruchu trzeba dodaæ do hasha dla tego ruchu iloœæ wrongInAllRows
		// dodaje siê tak : conflictHash.put(5, "L");
		// dla jakeigos node o ruchu L:
		// conflictHash.put(wrongInAllRows(node), "L");
		
		
		return conflictHash;
	}
    
	
	private int wrongInAllRows(Node<SingleMove> node){
		 int count = 0;

         for (int i = 0; i < Board.SIZE ; i++)
         {
        	 count += node.getData().getBoard().checkRow(i);
         }

         return count;
	}
	
	private char[] getMoves(Hashtable<Integer, String> hash){
		int[] sorted = new int[4];
		   for(int i = 0 ; i < hash.keySet().toArray().length ; i ++){
			   sorted[i] =  (Integer) hash.keySet().toArray()[i];
		   }
			Arrays.sort(sorted);
			char[] moves = new char[4];
			int iter = 0;
			for(int key : sorted){
				moves[iter] = hash.get(key).charAt(0);
				iter++;
			}
		return moves;
	}
}
