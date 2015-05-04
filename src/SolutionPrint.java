import Exception.ImpossibleToMoveException;


public class SolutionPrint {
	public static void solutionPrint(Board board, String[] rightOrder){

		Board boardCopy = board.copy();
		for(String iter : rightOrder){
			switch(iter){
			case "L":
				try {
					boardCopy.zeroToLeft();
					boardCopy.print();
					System.out.println("");
				} catch (ImpossibleToMoveException e) {}
				break;
			case "P":
				try {
					boardCopy.zeroToRight();
					boardCopy.print();
					System.out.println("");
				} catch (ImpossibleToMoveException e) {}
				break;
			case "G":
				try {
					boardCopy.zeroToUp();
					boardCopy.print();
					System.out.println("");
				} catch (ImpossibleToMoveException e) {}
				break;
			case "D":
				try {
					boardCopy.zeroToDown();
					boardCopy.print();
					System.out.println("");
				} catch (ImpossibleToMoveException e) {}
				break;
			}
		}
	}
}
