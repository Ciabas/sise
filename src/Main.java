
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import Exception.FoundResolutionException;
import Tree.*;

public final class Main {

	public static void main(String[] args) {
		
		System.out.println("Witaj w programie ze Sztucznej Inteligencji - Sławna Piętnastka!");
		System.out.println("Menu:");
		System.out.println("-b /--bfs porządek	Strategia przeszukiwania wszerz (id: 1)");
		System.out.println("-d /--dfs porządek	Strategia przeszukiwania w głąb (id: 2)");
		System.out.println("-i /--idfs porządek	Strategia przeszukiwania w głąb z iteracyjnym pogłębianiem (id: 3)");
		System.out.println("-a /--a id_strategii id_heurystyki	Strategia najpierw najlepszy");
		System.out.println("-t trudność układanki - domyślnie 20");
		System.out.println("-l pokaz lamiglowke");
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
		order[0] = "R";
		System.out.println("Co chcesz zrobić? : ");
	    try{
	        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	        menu = bufferRead.readLine();
	    }
	    catch(IOException e)
	    {
	        e.printStackTrace();
	    }
	    String[] parts = menu.split(" ");

	    if(parts.length > 1){

	    	if(parts[1] == "R"){
	    		order[0] = "R";
	    	}
	    	else if(parts[0] != "-a" && parts[0] != "--a"){
	    		order = parts[1].split(",");
	    	}
	    	
	    }
		switch(parts[0]){
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
				if(parts.length == 3){
					int strategy_id = (Integer.parseInt(parts[1]));
					order[0] = parts[2];
					switch(order[0]){
					case "1":
						System.out.println("Heurystyka poprawności rzędów");
						break;
					case "2":
						System.out.println("Heurystyka odległości od rozwiązania");
						break;
					}
					switch(strategy_id){
					case 1:
						BFS(root, 0, order,  rootMove);
						break;
					case 2:
						DFS(root, 0, order,  rootMove);
						break;
					case 3:
						IDFS(root, 0, order,  rootMove);
						break;
					}
				}
			break;
		case "-t":
			rootBoard.create();
			rootBoard.smartShuffle(Integer.parseInt(parts[1]));
		    rootBoard.print();
		    break;

		case "-l":
			rootBoard.print();
			break;
		case "-x":
			System.out.println("Do widzenia!");
			contin = false;
			return;
		}
		}
		
	}
	
	private static void DFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
			Recursion.recursionAddDFS(root, 20, order);
		}catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
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
			System.out.println("Czas wykonania:"+(stop-start));
			System.out.println();
		}
		root.clear(root);	
	}
}
