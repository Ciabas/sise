
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import Exception.FoundResolutionException;
import Tree.*;

public final class Main {

	public static void main(String[] args) {
		System.out.println("Witaj w programie ze Sztucznej Inteligencji - Sławna Piętnastka!");
		int size;
		String input = "4";
		String[] rows = {"1","2","3","4"};
		
		try{
			System.out.println("Podaj wielkość łamigłówki");
	        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	        input = bufferRead.readLine();
	    }
	    catch(IOException e)
	    {
	        e.printStackTrace();
	    }
		size = Integer.parseInt(input);
		Board.SIZE = size;
		Board rootBoard = new Board();
		System.out.println("[t/n] Czy chcesz wypełnić tablicę łamigłówki? jeśli nie - wylosujemy ją sami!");
		try{
	        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	        input = bufferRead.readLine();
	    }
	    catch(IOException e)
	    {
	        e.printStackTrace();
	    }
		int[][] state = new int[size][size];
		if(input.equals("t")){
			try{
				for(int i = 0 ; i < size ; i++){
					System.out.println("Podaj wiersz "+ (i+1));
					BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
					input = bufferRead.readLine();
					rows = input.split(",");
					state = createCustomPuzzle(size, i, rows, state);
				}
		    }catch(IOException e)
		    {
		        e.printStackTrace();
		    }
			rootBoard.custom(state);
		}
		else{
			rootBoard.create();
			rootBoard.smartShuffle(6);
		}
		
		System.out.println("Menu:");
		System.out.println("-b /--bfs porządek	Strategia przeszukiwania wszerz (id: 1)");
		System.out.println("-d /--dfs porządek	Strategia przeszukiwania w głąb (id: 2)");
		System.out.println("-i /--idfs porządek	Strategia przeszukiwania w głąb z iteracyjnym pogłębianiem (id: 3)");
		System.out.println("-a /--a id_strategii id_heurystyki	Strategia najpierw najlepszy");
		System.out.println("-t trudność układanki - domyślnie 6");
		System.out.println("-l pokaz lamiglowke");
		System.out.println("-p wlacz/wylacz pokazywanie rozwiązania krok po kroku");
		System.out.println("-x wyjście");
		
		SingleMove rootMove = new SingleMove(rootBoard, "root");
		Node<SingleMove> root = new Node<SingleMove>(null, rootMove);
		String[] order = {"R"};
		String menu = "-b";
		boolean step_by_step = false;
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
				BFS(root, 0, order,  rootMove, step_by_step);			
				break;
		case("-d"):
			case("--dfs"):
				DFS(root, 0, order,  rootMove, step_by_step);	
			break;
		case("-i"):
			case("--idfs"):
				IDFS(root, 0, order,  rootMove, step_by_step);
			break;
		case("-a"):
			case("--a"):
				if(parts.length == 3){
					int strategy_id = (Integer.parseInt(parts[1]));
					order[0] = parts[2];
					switch(order[0]){
					case "1":
						System.out.println("Heurystyka rzędów");
						break;
					case "2":
						System.out.println("Heurystyka odległości (Manhatann)");
						break;
					}
					switch(strategy_id){
					case 1:
						BFS(root, 0, order,  rootMove, step_by_step);
						break;
					case 2:
						DFS(root, 0, order,  rootMove, step_by_step);
						break;
					case 3:
						IDFS(root, 0, order,  rootMove, step_by_step);
						break;
					}
				}
			break;
		case "-t":
			rootBoard.create();
			try{
				rootBoard.smartShuffle(Integer.parseInt(parts[1]));
			}
			catch(ArrayIndexOutOfBoundsException e){
				rootBoard.smartShuffle(6);
			}
		    rootBoard.print();
		    break;

		case "-l":
			rootBoard.print();
			break;
		case "-p":
			step_by_step = !step_by_step;
			System.out.println(step_by_step);
			break;
		case "-x":
			System.out.println("Do widzenia!");
			contin = false;
			return;
		}
		}
		
	}
	
	private static void DFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
			Recursion.recursionAddDFS(root, 20, order, step_by_step);
		}catch(FoundResolutionException e){
			stop = System.currentTimeMillis();
			System.out.println("Czas wykonania:"+(stop-start));
			System.out.println();
		}
		root.clear(root);
	}
	
	private static void BFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
		Recursion.recursionAddBFS(root, 20, order, step_by_step);
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
	
	private static void IDFS(Node<SingleMove> root, int counter, String[] order, SingleMove rootMove, boolean step_by_step){
		long stop, start;
		root = new Node<SingleMove>(null, rootMove);
		start = System.currentTimeMillis();
		try{
		Recursion.recursionAddIDFS(root, 20, order, step_by_step);
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
	
	private static int[][] createCustomPuzzle(int size, int row, String[] input, int[][] state){
		for(int i = 0 ; i < size ; i ++){
			state[row][i] = Integer.parseInt(input[i]);
		}
		return state;
	}
}
