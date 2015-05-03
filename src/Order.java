import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

import Exception.ImpossibleToMoveException;
import Tree.Node;

public class Order {
	
	public static String[] rowHeuristic(Node<SingleMove> node){
		TreeMap<String, Integer> hash = wrongMovesRow(node);
		return getMoves(hash);
	}
	
	public static String[] distanceHeuristic(Node<SingleMove> node){
		TreeMap<String, Integer> hash = wrongMovesDistance(node);
		return getMoves(hash);
		
	}
	
	private static TreeMap<String, Integer> wrongMovesDistance(Node<SingleMove> node) {
		TreeMap<String, Integer> wrongMovesHash = new TreeMap<String, Integer>();
		Board copyBoard=node.getData().getBoard().copy();	
		try{
			Board left = copyBoard.zeroToLeft();
			wrongMovesHash.put("L",allDistances(left));
			}
		catch (ImpossibleToMoveException e){}
		try{
			Board right = copyBoard.zeroToRight();
			wrongMovesHash.put("P", allDistances(right));
		}
		catch (ImpossibleToMoveException e){}
		try{
			Board up = copyBoard.zeroToUp();
			wrongMovesHash.put("G", allDistances(up));
		}
		catch (ImpossibleToMoveException e){}
		try{
			Board down = copyBoard.zeroToDown();	
			wrongMovesHash.put("D",allDistances(down));
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
	
	private static TreeMap<String, Integer> wrongMovesRow(Node<SingleMove> node){
		TreeMap<String, Integer> wrongMovesHash = new TreeMap<String, Integer>();
		Board copyBoard=node.getData().getBoard().copy();	
		try{
			Board left = copyBoard.zeroToLeft();
			wrongMovesHash.put("L",wrongInAllRows(left));
			}
		catch (ImpossibleToMoveException e){}
		try{
			Board right = copyBoard.zeroToRight();
			wrongMovesHash.put("P", wrongInAllRows(right));
		}
		catch (ImpossibleToMoveException e){}
		try{
			Board up = copyBoard.zeroToUp();
			wrongMovesHash.put("G", wrongInAllRows(up));
		}
		catch (ImpossibleToMoveException e){}
		try{
			Board down = copyBoard.zeroToDown();	
			wrongMovesHash.put("D",wrongInAllRows(down));
		}
		catch (ImpossibleToMoveException e){}
		return wrongMovesHash;
	}
    
	
	private static int wrongInAllRows(Board board){
		 int count = 0;

         for (int i = 0; i < Board.SIZE ; i++)
         {
        	 count += board.checkRow(i);
         }

         return count;
	}
	
	private static String[] getMoves(TreeMap<String, Integer> hash){
		Map<String, Integer> sorted = Sort.sortByComparator(hash, true);
		String[] sortedArray = new String[sorted.keySet().toArray().length];
		for(int i = 0 ; i < sorted.keySet().toArray().length ; i ++){
			sortedArray[i] = (String)sorted.keySet().toArray()[i];
		}
		return sortedArray;
	}
}
