import Exception.FoundResolutionException;
import Tree.Node;


public class MainStats {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Board rootBoard = new Board();
		
		int i = 8;
		
		rootBoard.create();
		rootBoard.smartShuffle(i);
		SingleMove rootMove = new SingleMove(rootBoard, "root");
		Node<SingleMove> rootBFS = new Node<SingleMove>(null, new SingleMove(rootBoard.copy(), "root"));
		Node<SingleMove> rootDFS = new Node<SingleMove>(null, new SingleMove(rootBoard.copy(), "root"));
		Node<SingleMove> rootIDFS = new Node<SingleMove>(null, new SingleMove(rootBoard.copy(), "root"));
		//Node<SingleMove> root = new Node<SingleMove>(null, new SingleMove(rootBoard, "root"));
		
		//String[] order = {"L", "P","G","D"};
		String[] order = {"2"};
		//String[] order = {"R"};
		//for(int i = 1; i <= 8; i++){
		//int i = 1;
		int j=1;
			//for(int j = 0; j < 10; j++){
				//root.getData().getBoard().create();
				//root.getData().getBoard().smartShuffle(i);
				System.out.println(i+" ");
				//rootBFS.getData().getBoard().printInLine(); System.out.println("");
				//rootDFS.getData().getBoard().printInLine();System.out.println("");
				//rootIDFS.getData().getBoard().printInLine();System.out.println("");
				System.out.print("DFS \t");				
				DFS(rootDFS, 20, order,  rootMove, true);	
				System.out.println("");
				
				System.out.print("IDFS \t");			
				IDFS(rootIDFS, 20, order,  rootMove, true);	
				System.out.println("");
				
				System.out.print("BFS \t");				
				BFS(rootBFS, 20, order,  rootMove, true);	
				System.out.println("");
				
//				//root.getData().getBoard().printInLine();
//				IDFS(root, 20, order,  rootMove, true);
//				Recursion.amount=0;
//				System.out.println("");
			//}
			System.out.println("");
		//}

	}
	
	
	private static void DFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		Recursion.amount=0;
		try{
			Recursion.recursionAddDFS(root, counter, order, step_by_step);
		}catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.print(""+(stop-start)+"& ");
		}
		root.clear(root);
	}
	
	private static void BFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		Recursion.amount=0;
		try{
		Recursion.recursionAddBFS(root, counter, order, step_by_step);
		}
		catch(StackOverflowError e){
			System.out.println("Stos przepełniony");
		}
		catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.print(""+(stop-start)+"& ");
		}
		root.clear(root);
	}
	
	private static void IDFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		Recursion.amount=0;
		try{
		Recursion.recursionAddIDFS(root, counter, order, step_by_step);
		}
		catch(StackOverflowError e){
			System.out.println("Stos przepełniony");
		}
		catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.print(""+(stop-start)+"& ");
		}
		root.clear(root);	
	}
	
	private static int[][] createCustomPuzzle(int size, int row, String[] input, int[][] state){
		for(int i = 0 ; i < size ; i ++){
			state[row][i] = Integer.parseInt(input[i]);
		}
		return state;
	}
}
