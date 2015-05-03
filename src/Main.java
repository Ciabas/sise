
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import Exception.FoundResolutionException;
import Tree.*;

public final class Main {

	public static void main(String[] args) {
		
		System.out.println("Witaj w programie ze Sztucznej Inteligencji - Sławna Piętnastka!");
		System.out.println("Menu:");
		System.out.println("-b /--bfs porządek	Strategia przeszukiwania wszerz");
		System.out.println("-d /--dfs porządek	Strategia przeszukiwania w głąb");
		System.out.println("-i /--idfs porządek	Strategia przeszukiwania w głąb z iteracyjnym pogłębianiem");
		System.out.println("-a /--a id_strategii id_heurystyki	Strategia najpierw najlepszy");
		System.out.println("-t trudność układanki - domyślnie 20");
		System.out.println("-x wyj�cie");
		Board rootBoard = new Board();
		rootBoard.create();
		rootBoard.smartShuffle(20);
		SingleMove rootMove = new SingleMove(rootBoard, "root");
		Node<SingleMove> root = new Node<SingleMove>(null, rootMove);
		String[] order = {"R"};
		String menu = "-b";
		boolean contin = true;
		while(contin = true){
		System.out.println("Co chcesz zrobić? : ");
	    try{
	        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	        menu = bufferRead.readLine();
	    }
	    catch(IOException e)
	    {
	        e.printStackTrace();
	    }
		switch(menu){
		case("-b"):
			case("--bfs"):
				BFS(root, 0, order,  rootMove);			
				break;
		case("-d"):
			case("--dfs"):
				DFS(root, 0, order,  rootMove);	
			break;
		case("-i"):
			case("--idfs"):
				IDFS(root, 0, order,  rootMove);
			break;
		case("-a"):
			case("--a"):
			break;
		case "-t":
			try{
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        String level = bufferRead.readLine();
		        rootBoard.smartShuffle(Integer.parseInt(level));
		    }
		    catch(IOException e)
		    {
		        e.printStackTrace();
		    }
		case "-x":
			contin = false;
			return;
		}
		}

		
		
//		order[0] = "2";
//		System.out.println("HOKUSY POKUSY");
		
		//DFS
//				root = new Node<SingleMove>(null, rootMove);
//				start = System.currentTimeMillis();
//				try{
//					Recursion.recursionAddDFS(root, 20, order);
//				}catch(FoundResolutionException e){
//					stop = System.currentTimeMillis();
//					System.out.println("znalazł :)");
//					System.out.println("Czas wykonania:"+(stop-start));
//					System.out.println();
//				}
//				root.clear(root);
//				
//				//IDFS
//				root = new Node<SingleMove>(null, rootMove);
//				start = System.currentTimeMillis();
//				try{
//				Recursion.recursionAddIDFS(root, 20, order);
//				}
//				catch(StackOverflowError e){
//					System.out.println("Stos przepełniony");
//				}
//				catch(FoundResolutionException e){
//					stop = System.currentTimeMillis();
//					System.out.println("znalazł :)");
//					System.out.println("Czas wykonania:"+(stop-start));
//					System.out.println();
//				}
//				root.clear(root);
//		
//				//BFS
//				root = new Node<SingleMove>(null, rootMove);
//				start = System.currentTimeMillis();
//				try{
//				Recursion.recursionAddBFS(root, 20, order);
//				}
//				catch(StackOverflowError e){
//					System.out.println("Stos przepełniony");
//				}
//				catch(FoundResolutionException e){
//					stop = System.currentTimeMillis();
//					System.out.println("znalazł :)");
//					System.out.println("Czas wykonania:"+(stop-start));
//					System.out.println();
//				}
//				root.clear(root);
//		
//		
//		System.out.println();
//		System.out.println("Początkowa układanka:");
//		rootMove.getBoard().print();
//		System.out.println();

		
	}
	
	private static void DFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
			Recursion.recursionAddDFS(root, 20, order);
		}catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.println("Rozwiązanie znalezione!");
			System.out.println("Czas wykonania:"+(stop-start));
			System.out.println();
		}
		root.clear(root);
	}
	
	private static void BFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove){
		long stop, start;
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
	}
	
	private static void IDFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove){
		long stop, start;
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
			System.out.println("Rozwiązanie znalezione!");
			System.out.println("Czas wykonania:"+(stop-start));
			System.out.println();
		}
		root.clear(root);	
	}
}
