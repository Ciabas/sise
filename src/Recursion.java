import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import Exception.FoundResolutionException;
import Exception.ImpossibleToMoveException;
import Tree.Node;



public class Recursion {
	
	private static Queue<Node<SingleMove>> queueBFS = new LinkedList<Node<SingleMove>>();
	private static int amount = 0;
	public static Node<SingleMove> root;
	
	
	public static void recursionAddDFS(Node<SingleMove> node, int counter, String[] order, boolean step_by_step) throws FoundResolutionException{
		amount++;
		if(amount==1){
			root = node;
		}
		if(node.getData().getBoard().checkIfSolved()==true){
			//System.out.println("DFS");
			StringBuilder steps = new StringBuilder(pathToSolutionReversed(node)).reverse();
			//System.out.print("Rozwiazanie: " + steps+" ");
			//System.out.print("Liczba kroków: " + (steps.length()/2)+" ");
			if(step_by_step == true){
				System.out.print("Liczba wywołań funkcji: " + amount+" ");
				System.out.print("Glebokosc: " + calculateDepth(node)+" ");
				//System.out.println("Krok po kroku: ");
				//SolutionPrint.solutionPrint(root.getData().getBoard(), stepsToStringTab(steps));
			}
			amount = 0;
			throw new FoundResolutionException();
		}
		try{
			String [] orderCopy = order;
			switch(order[0]){
				case "R": 
					orderCopy = randomOrder();
					break;
				case "1":
					orderCopy = Order.rowHeuristic(node);
					break;
				case "2":
					orderCopy = Order.distanceHeuristic(node);
					break;
			}
			if(counter>0){
				for(int i = 0; i < orderCopy.length; i++){
					try{
						if(node.getData().getMove() != opposite(orderCopy[i])){
							SingleMove move = moveTo(orderCopy[i], node);
							Node<SingleMove> moveNode = new Node<SingleMove>();
							moveNode.setData(move);
							node.addChild(moveNode);
							recursionAddDFS(moveNode, --counter, order, step_by_step);
						}
					}
					catch (ImpossibleToMoveException e) {}
				}
			} 
		}
		catch(OutOfMemoryError E){
			System.out.println("Brakło pamięci...");
		}
	}
	
	public static void recursionAddBFS(Node<SingleMove> node, int counter, String [] order, boolean step_by_step) throws FoundResolutionException{
		amount++;
		if(amount==1){
			root = node;
		}
		if(node.getData().getBoard().checkIfSolved()==true){
			//System.out.println("BFS");
			StringBuilder steps = new StringBuilder(pathToSolutionReversed(node)).reverse();
			System.out.println("Rozwiazanie: " + steps);
			System.out.println("Liczba kroków: " + (steps.length()/2));
			if(step_by_step == true){
				System.out.print("Liczba wywołań funkcji: " + amount+" ");
				System.out.print("Glebokosc: " + calculateDepth(node)+" ");
				System.out.print("Rozmiar kolejki:"+queueBFS.size()+" ");
				//System.out.println("Krok po kroku: ");
				//SolutionPrint.solutionPrint(root.getData().getBoard(), stepsToStringTab(steps));
				}
			amount = 0;
			throw new FoundResolutionException();
		}
		try{
			String[] orderCopy = order;
			switch(order[0]){
			case "R": 
				orderCopy = randomOrder();
				break;
			case "1":
				orderCopy = Order.rowHeuristic(node);
				break;
			case "2":
				orderCopy = Order.distanceHeuristic(node);
				break;
		}
			if(counter>0){
				for(int i = 0; i < orderCopy.length; i++){	// stworz wszytkie mozliwe dzieci
					try{
						if(node.getData().getMove() != opposite(orderCopy[i])){
							SingleMove move = moveTo(orderCopy[i], node);
							Node<SingleMove> moveNode = new Node<SingleMove>();
							moveNode.setData(move);
							node.addChild(moveNode);
						}
					}
					catch (ImpossibleToMoveException e) {}
				}
				queueBFS.remove(node);						//usuwasz sie bo sprawdziles juz
				for(int j = 0; j< node.getDegree(); j++){ 	//dla kazdego dziecka
					queueBFS.add(node.getChild(j));			//dodaj dziecko do kolejki
					counter++;								//zwiekszamy counter o liczbe dzieci, zeby counter odpowiadal za poziom
					//System.out.println("kolejka: " + queueBFS.size());
				}
				for(int j = 0; j< node.getDegree(); j++){
					recursionAddBFS(queueBFS.poll(), --counter, orderCopy, step_by_step);
				}	
			}
		}
		catch(OutOfMemoryError E){
			System.out.println("Brakło pamięci...");
		}
	}
	
	public static void recursionAddIDFS(Node<SingleMove> node, int counter, String [] order, boolean step_by_step) throws FoundResolutionException{
		amount++;
		System.out.print("I");
		for(int j = 1; j<=counter; j++){
			recursionAddDFS(node, j, order, step_by_step);
		}
	}

	
	private static String opposite(String what){
		switch(what){
			case "L":
				return "P";
			case "P" :
				return "L";
			case "G":
				return "D";
			case "D":
				return "G";
			default:
				return "error";
		}
	}
	
	private static SingleMove moveTo(String what, Node<SingleMove> parrent) throws ImpossibleToMoveException{
		switch(what){
			case "L":
				return parrent.getData().left();
			case "P" :
				return parrent.getData().right();
			case "G":
				return parrent.getData().up();
			case "D":
				return parrent.getData().down();
			case "R":
				String[] order = {"L","P","G","P"};
				
				Random rand = new Random();
				String randomFromOrder = order[rand.nextInt(4)];
				return moveTo(randomFromOrder, parrent);
			default:
				throw new ImpossibleToMoveException();
		}
	}
	
	private static String pathToSolutionReversed(Node<SingleMove> node){
		if(node.getData().getMove()=="root"){
			return "";
		}
		else{
			return node.getData().getMove()+" "+pathToSolutionReversed(node.getParent());
			}
	}
	
	private static String[] randomOrder(){
		int randomX, randomX1;
		String tmp;
		Random rand;
		String[] orderToReturn = {"L","P","G","D"};
		for(int i = 0; i < 6; i++){
			rand = new Random();
			randomX = rand.nextInt(orderToReturn.length);
			rand = new Random();
			randomX1 = rand.nextInt(orderToReturn.length);
			
			tmp=orderToReturn[randomX];
			orderToReturn[randomX]=orderToReturn[randomX1];
			orderToReturn[randomX1]=tmp;
		}
		return orderToReturn;
	}
	
	private static String[] stepsToStringTab(StringBuilder steps){
		String stringSteps = steps.toString();
		List<String> myList = new ArrayList<String>();
		for(int j = 1; j < steps.length(); j+=2){
			myList.add(""+stringSteps.charAt(j));
		}
		String[] arr = myList.toArray(new String[myList.size()]);
		return arr;
	}
	
	private static int calculateDepth( Node<SingleMove> node){
		if(node.hasParrent()==false){
			return + 0 ;
		}
		else{
			return 1 + calculateDepth(node.getParent());
		}
	}
}
