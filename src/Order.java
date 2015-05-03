import java.util.Arrays;
import java.util.Hashtable;

import Exception.ImpossibleToMoveException;
import Tree.Node;

public class Order {
	
	public static String[] rowHeuristic(Node<SingleMove> node){
		Hashtable<Integer, String> hash = wrongMovesRow(node);
		return getMoves(hash);
	}
	
	public static String[] distanceHeuristic(Node<SingleMove> node){
		Hashtable<Integer, String> hash = wrongMovesDistance(node);
		return getMoves(hash);
		
	}
	
	private static Hashtable<Integer, String> wrongMovesDistance(Node<SingleMove> node) {
		Hashtable<Integer, String> wrongMovesHash = new Hashtable<Integer, String>();
		Board copyBoard=node.getData().getBoard().copy();	
		try{
			Board left = copyBoard.zeroToLeft();
			wrongMovesHash.put(allDistances(left),"L");
			}
		catch (ImpossibleToMoveException e){}
		try{
			Board right = copyBoard.zeroToRight();
			wrongMovesHash.put(allDistances(right),"R");
		}
		catch (ImpossibleToMoveException e){}
		try{
			Board up = copyBoard.zeroToUp();
			wrongMovesHash.put(allDistances(up),"G");
		}
		catch (ImpossibleToMoveException e){}
		try{
			Board down = copyBoard.zeroToDown();	
			wrongMovesHash.put(allDistances(down),"D");
		}
		catch (ImpossibleToMoveException e){}
		return wrongMovesHash;
	}

	private static Integer allDistances(Board board) {
		int count = 0;

        for (int i = 0; i < Board.SIZE ; i++){
        	for(int j = 0; j < Board.SIZE ; j++){
        		count += board.calculateDistance(i, j);
        	}
       	 
        }

        return count;
	}
	
	private static Hashtable<Integer, String> wrongMovesRow(Node<SingleMove> node){
		Hashtable<Integer, String> wrongMovesHash = new Hashtable<Integer, String>();
		for(Node<SingleMove> child : node.getChildren()){
			wrongMovesHash.put(wrongInAllRows(child),child.getData().getMove());
		}
		return wrongMovesHash;
	}
    
	
	private static int wrongInAllRows(Node<SingleMove> node){
		 int count = 0;

         for (int i = 0; i < Board.SIZE ; i++)
         {
        	 count += node.getData().getBoard().checkRow(i);
         }

         return count;
	}
	
	private static String[] getMoves(Hashtable<Integer, String> hash){
		int[] sorted = new int[4];
		   for(int i = 0 ; i < hash.keySet().toArray().length ; i ++){
			   sorted[i] =  (Integer) hash.keySet().toArray()[i];
		   }
			Arrays.sort(sorted);
			String[] moves = new String[4];
			int iter = 0;
			for(int key : sorted){
				moves[iter] = hash.get(key);
				iter++;
			}
		return moves;
	}
}
