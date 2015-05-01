import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import Exception.FoundResolutionException;
import Exception.ImpossibleToMoveException;
import Tree.Node;



public class Recursion {
	
	private static Queue<Node<SingleMove>> queueBFS = new LinkedList<Node<SingleMove>>();
	private static int amount = 0;
	
	public static void recursionAddDFS(Node<SingleMove> node, int counter, String [] order) throws FoundResolutionException{
			amount++;
			if(node.getData().getBoard().checkIfSolved()==true){
				System.out.println("DFS, wyszlooooooooooooooooooooooooooooooo");
				System.out.println("Rozwiazanie: " + new StringBuilder(pathToSolutionReversed(node)).reverse());
				//System.out.println("Głębokość: " + (20 - counter));
				System.out.println("Liczba kroków: " + amount);
				node.getData().getBoard().print();
				amount = 0;
				throw new FoundResolutionException();
			}
		try{
			String[] orderCopy = order;
			if(order[0] == "R"){
				orderCopy = randomOrder();
			}
			if(counter>0){
				for(int i = 0; i < orderCopy.length; i++){
					//System.out.println("szukam . . . głębokość: " + (20 - counter));
					try{
						if(node.getData().getMove() != opposite(orderCopy[i])){
							SingleMove move = moveTo(orderCopy[i], node);
							Node<SingleMove> moveNode = new Node<SingleMove>();
							moveNode.setData(move);
							node.addChild(moveNode);
							recursionAddDFS(moveNode, --counter, order);
						}
					}
					catch (ImpossibleToMoveException e) {}
				}
			} 
		}
		catch(OutOfMemoryError E){
			System.out.println("Brakło pamięci...");
		}
		return;
	}
	
	public static void recursionAddBFS(Node<SingleMove> node, int counter, String [] order) throws FoundResolutionException{
		amount++;
		if(node.getData().getBoard().checkIfSolved()==true){
			System.out.println("BFS, wyszlooooooooooooooooooooooooooooooo");
			System.out.println("Rozwiazanie: " + new StringBuilder(pathToSolutionReversed(node)).reverse());
			//System.out.println("Głębokość: " + (20 - counter));
			System.out.println("Liczba kroków: " + amount);
			node.getData().getBoard().print();
			amount = 0;
			throw new FoundResolutionException();
		}
		try{
			String[] orderCopy = order;
			if(order[0] == "R"){
				orderCopy = randomOrder();
			}
			if(counter>0){
				for(int i = 0; i < orderCopy.length; i++){	// stworz wszytkie mozliwe dzieci
					//System.out.println("szukam . . . głębokość: " + (20 - counter));
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
				}
				for(int j = 0; j< node.getDegree(); j++){
					recursionAddBFS(queueBFS.poll(), --counter, orderCopy);
				}	
			}
		}
		catch(OutOfMemoryError E){
			System.out.println("Brakło pamięci...");
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
}
