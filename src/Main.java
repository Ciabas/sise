import Exception.ImpossibleToMoveException;
import Tree.*;

public final class Main {

	public static void main(String[] args) {
		
		Board rootBoard = new Board();
		rootBoard.create();
		rootBoard.shuffle();
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
		rootMove.getBoard().print();
		System.out.println();
		System.out.println();
		Node<SingleMove> root = new Node<SingleMove>(null, rootMove);
		try{
			if(Recursion.recursionAdd(root, 38) == 1) return;	
		}catch(Exception e){
			System.out.println("Pewnie brakło pamięci...");
		}
		System.out.println();
		System.out.println();
		System.out.println("rozwiazane?: "+rootMove.getBoard().checkIfSolved());
	}

}
