import Exception.FoundResolutionException;
import Tree.Node;


public class MainStats {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Board rootBoard = new Board();
		SingleMove rootMove = new SingleMove(rootBoard, "root");
		Node<SingleMove> root = new Node<SingleMove>(null, rootMove);
		String[] order = {"L","P","G","D"};
		for(int i = 1; i <= 10; i++){
			for(int j = 0; j < 10; j++){
				root.getData().getBoard().create();
				root.getData().getBoard().smartShuffle(i);
				System.out.print(i+" ");
				root.getData().getBoard().printInLine();
				DFS(root, 20, order,  rootMove, true);	
				System.out.println("");
			}
			System.out.println("");
		}

	}
	
	
	private static void DFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
			Recursion.recursionAddDFS(root, counter, order, step_by_step);
		}catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.print("Czas wykonania:"+(stop-start)+" ");
		}
		root.clear(root);
	}
	
	private static void BFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
		Recursion.recursionAddBFS(root, counter, order, step_by_step);
		}
		catch(StackOverflowError e){
			System.out.println("Stos przepełniony");
		}
		catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.print("Czas wykonania:"+(stop-start)+" ");
		}
		root.clear(root);
	}
	
	private static void IDFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
		Recursion.recursionAddIDFS(root, counter, order, step_by_step);
		}
		catch(StackOverflowError e){
			System.out.println("Stos przepełniony");
		}
		catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.print("Czas wykonania:"+(stop-start)+" ");
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
