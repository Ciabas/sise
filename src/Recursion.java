import Exception.FoundResolutionException;
import Exception.ImpossibleToMoveException;
import Tree.Node;



public class Recursion {
		public static void recursionAdd(Node<SingleMove> parrent, int counter) throws FoundResolutionException{
				if(parrent.getData().getBoard().checkIfSolved()==true){
					System.out.println("WYSZLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
					System.out.println("WYSZLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
					System.out.println("Głębokość: " + (20 - counter));
					parrent.getData().getBoard().print();
					throw new FoundResolutionException();
				}
			try{
				if(counter>0){
					System.out.println("szukam . . . głębokość: " + (20 - counter));
					try {
						if(parrent.getData().getMove()!="P"){
							SingleMove left = parrent.getData().left();
							Node<SingleMove> leftNode = new Node<SingleMove>();
							leftNode.setData(left);
							parrent.addChild(leftNode);
							//left.print();///////////////////////////////////////////////////////
							recursionAdd(leftNode, --counter);
					}
					} catch (ImpossibleToMoveException e) {}
					
					try {
						if(parrent.getData().getMove()!="D"){
							SingleMove up = parrent.getData().up();
							Node<SingleMove> upNode = new Node<SingleMove>();
							upNode.setData(up);
							parrent.addChild(upNode);
							//up.print();///////////////////////////////////////////////////////
							recursionAdd(upNode, --counter);
						}
					} catch (ImpossibleToMoveException e) {}
					
					try {
						if(parrent.getData().getMove()!="L"){
							SingleMove right = parrent.getData().right();
							Node<SingleMove> rightNode = new Node<SingleMove>();
							rightNode.setData(right);
							parrent.addChild(rightNode);
							//right.print();///////////////////////////////////////////////////////
							recursionAdd(rightNode, --counter);
						}
					} catch (ImpossibleToMoveException e) {}
					
					try {
						if(parrent.getData().getMove()!="G"){
							SingleMove down = parrent.getData().down();
							Node<SingleMove> downNode = new Node<SingleMove>();
							downNode.setData(down);
							parrent.addChild(downNode);
							//down.print();///////////////////////////////////////////////////////
							recursionAdd(downNode, --counter);
						}
					} catch (ImpossibleToMoveException e) {}
				}
			}catch(OutOfMemoryError E){
				System.out.println("Brakło pamięci...");
			}
			return;
		}
}
