
import Exception.FoundResolutionException;
import Tree.*;

public final class Main {

	public static void main(String[] args) {
		
		Board rootBoard = new Board();
		rootBoard.create();
		rootBoard.smartShuffle(20);
		
		SingleMove rootMove = new SingleMove(rootBoard, "root");
		long start, stop; 
		
		//String[] order = {"L","P","G","D"};
		String[] order = {"R"};
		Node<SingleMove> root = new Node<SingleMove>(null, rootMove);

		//DFS
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
			Recursion.recursionAddDFS(root, 20, order);
		}catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.println("znalazł :)");
			System.out.println("Czas wykonania:"+(stop-start));
			System.out.println();
		}
		root.clear(root);
		
		//IDFS
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
		Recursion.recursionAddIDFS(root, 20, order);
		}
		catch(StackOverflowError e){
			System.out.println("Stos przepełniony");
		}
		catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.println("znalazł :)");
			System.out.println("Czas wykonania:"+(stop-start));
			System.out.println();
		}
		root.clear(root);
		
		
		
		order[0] = "1";
		System.out.println("HOKUSY POKUSY");
		
		//DFS
				root = new Node<SingleMove>(null, rootMove);
				start = System.currentTimeMillis();
				try{
					Recursion.recursionAddDFS(root, 20, order);
				}catch(FoundResolutionException e){
					stop = System.currentTimeMillis();
					System.out.println("znalazł :)");
					System.out.println("Czas wykonania:"+(stop-start));
					System.out.println();
				}
				root.clear(root);
				
				//IDFS
				root = new Node<SingleMove>(null, rootMove);
				start = System.currentTimeMillis();
				try{
				Recursion.recursionAddIDFS(root, 20, order);
				}
				catch(StackOverflowError e){
					System.out.println("Stos przepełniony");
				}
				catch(FoundResolutionException e){
					stop = System.currentTimeMillis();
					System.out.println("znalazł :)");
					System.out.println("Czas wykonania:"+(stop-start));
					System.out.println();
				}
				root.clear(root);
		
		//BFS
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
		Recursion.recursionAddBFS(root, 20, order);
		}
		catch(StackOverflowError e){
			System.out.println("Stos przepełniony");
		}
		catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.println("znalazł :)");
			System.out.println("Czas wykonania:"+(stop-start));
			System.out.println();
		}
		root.clear(root);
		
		
		System.out.println();
		System.out.println("Początkowa układanka:");
		rootMove.getBoard().print();
		System.out.println();
	}
}
