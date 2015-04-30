import Exception.ImpossibleToMoveException;


public class SingleMove {
	private Board board;
	private String move;
	
	public SingleMove(Board board, String move) {
		super();
		this.board = board;
		this.move = move;
	}
	
	public Board getBoard() {
		return board;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public SingleMove left() throws ImpossibleToMoveException{
		try{
			SingleMove newMove = new SingleMove(board.zeroToLeft(), "L");
			return newMove;
		}
		catch(ImpossibleToMoveException e){
			throw new ImpossibleToMoveException();
		}
	}
	
	public SingleMove right() throws ImpossibleToMoveException{
		try{
			SingleMove newMove = new SingleMove(board.zeroToRight(), "P");
			return newMove;
		}
		catch(ImpossibleToMoveException e){
			throw new ImpossibleToMoveException();
		}
	}
	
	public SingleMove up() throws ImpossibleToMoveException{
		try{
			SingleMove newMove = new SingleMove(board.zeroToUp(), "G");
			return newMove;
		}
		catch(ImpossibleToMoveException e){
			throw new ImpossibleToMoveException();
		}
	}
	
	public SingleMove down() throws ImpossibleToMoveException{
		try{
			SingleMove newMove = new SingleMove(board.zeroToDown(), "D");
			return newMove;
		}
		catch(ImpossibleToMoveException e){
			throw new ImpossibleToMoveException();
		}
	}
	
	public void print(){
		System.out.println("Board:");
		board.print();
		System.out.println("Ruch: "+ move);
		System.out.println("-----------------");
		
	}

}
