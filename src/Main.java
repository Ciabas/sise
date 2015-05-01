
import Exception.FoundResolutionException;
import Tree.*;

public final class Main {

	public static void main(String[] args) {
		
		Board rootBoard = new Board();
		rootBoard.create();
		rootBoard.smartShuffle(10);
		
		SingleMove rootMove = new SingleMove(rootBoard, "root");
		
		//DRZEWO
		Node<SingleMove> root = new Node<SingleMove>(null, rootMove);
		try{
			String[] order = {"L","P","G","D"};
			//String[] order = {"R"};
			Recursion.recursionAddDFS(root, 20, order);
		}catch(FoundResolutionException e){
			System.out.println("znalazł :)");
			System.out.println();
		}
		
		try{
		String[] order = {"L","P","G","D"};
		//String[] order = {"R"};
		Recursion.recursionAddBFS(root, 20, order);
		}
		catch(StackOverflowError e){
			System.out.println("Stos przepełniony");
		}
		catch(FoundResolutionException e){
		System.out.println("znalazł :)");
		System.out.println();
	}
		
		
		System.out.println();
		System.out.println("Początkowa układanka:");
		rootMove.getBoard().print();
		System.out.println();

		
	}

}
