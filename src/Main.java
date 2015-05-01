import Exception.FoundResolutionException;
import Exception.ImpossibleToMoveException;
import Tree.*;

public final class Main {

	public static void main(String[] args) {
		
		Board rootBoard = new Board();
		rootBoard.create();
		//rootBoard.shuffle();
		rootBoard.smartShuffle(10);
		
		SingleMove rootMove = new SingleMove(rootBoard, "root");
//		rootBoard.create();
//		rootBoard.print();
//		System.out.println("rozwiazane: "+rootBoard.checkIfSolved());
//		rootBoard.getZero().print();
//		System.out.println("---------------------");
//		
//		rootBoard.shuffle();
//		rootBoard.print();
//		System.out.println("rozwiazane: "+rootBoard.checkIfSolved());
//		rootBoard.getZero().print();
//		System.out.println("---------------------");
		
//		rootMove.print();
//
//		try {
//			SingleMove left = rootMove.left();
//			left.print();
//		} catch (ImpossibleToMoveException e) {}
//		
//		try {
//			SingleMove up = rootMove.up();
//			up.print();
//		} catch (ImpossibleToMoveException e) {}
//		
//		try {
//			SingleMove right = rootMove.right();
//			right.print();
//		} catch (ImpossibleToMoveException e) {}
//		
//		try {
//			SingleMove down = rootMove.down();
//			down.print();
//		} catch (ImpossibleToMoveException e) {}
		
		
		
		//DRZEWO
		Node<SingleMove> root = new Node<SingleMove>(null, rootMove);
		try{
			Recursion.recursionAdd(root, 30);
		}catch(FoundResolutionException e){
			System.out.println("znalazł :)");
		}
		System.out.println();
		System.out.println("Początkowa układanka:");
		rootMove.getBoard().print();
		System.out.println();
		System.out.println();
//		System.out.println("rozwiazane?: "+rootMove.getBoard().checkIfSolved());
	}

}
