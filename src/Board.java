import java.util.Random;

import Exception.ImpossibleToMoveException;


public class Board {
	public final static int SIZE = 3;
	private int[][] state = new int[SIZE][SIZE];
	private Point zero = new Point();
//	private Point lastMove;
//	
//
//	public Point getLastMove() {
//		return lastMove;
//	}
//	
//	public void setLastMove(Point lastMove) {
//		this.lastMove = lastMove;
//	}
	
	public Point getZero() {
		return zero;
	}
	
	public int[][] getState() {
		return state;
	}
	
	public void setState(int[][] state) {
		this.state = state;
	}

	public void setZero(Point zero) {
		this.zero = zero;
	}

	public Board(){
		super();
	}
	
//	public Board(Board toCopy){
//		this.state = new toCopy.state;
//		if(toCopy==copy) System.out.println("ta sama referencja");
//		this.zero = toCopy.zero;
//		//this.lastMove = toCopy.lastMove;
//	}
	
	public Board copy(Board toCopy){
		
		int[][] stateCopy = new int[SIZE][SIZE];
		Point zeroCopy = new Point(toCopy.getZero().getX(), toCopy.getZero().getY());
		for(int j = 0; j < SIZE; j++){
			for(int k = 0; k < SIZE; k++){
				stateCopy[j][k]=toCopy.getState()[j][k];
			}
		}
		Board copy = new Board();
		copy.setState(stateCopy);
		copy.setZero(zeroCopy);
		return copy;
	}
	
	public void create(){
		int number = 1;
		for(int j = 0; j < SIZE; j++){
			for(int k = 0; k < SIZE; k++){
				state[j][k] = number;
				if(number==SIZE*SIZE) state[j][k] = 0;
				number++;
			}
		}
		localizateZero();
	}
	
	public void shuffle(){
		int randomX, randomY, randomX1, randomY1, tmp;
		Random rand;
		for(int i = 0; i < 30; i++){
			rand = new Random();
			randomX = rand.nextInt(SIZE);
			rand = new Random();
			randomY = rand.nextInt(SIZE);
			rand = new Random();
			randomX1 = rand.nextInt(SIZE);
			rand = new Random();
			randomY1 = rand.nextInt(SIZE);
			
			tmp=state[randomX][randomY];
			state[randomX][randomY]=state[randomX1][randomY1];
			state[randomX1][randomY1]=tmp;
		}
		localizateZero();
	}
	
	public void localizateZero(){
		for(int j = 0; j < SIZE; j++){
			for(int k = 0; k < SIZE; k++){
				if(state[j][k]==0){
					this.zero.setLocation(k, j);
				}
			}
		}
	}
	
	public boolean checkIfSolved(){
		boolean solved=false;
		int number = 1;
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				if(number==SIZE*SIZE)number=0;
				if(state[i][j] == number){
					solved=true;
				}
				else return false;
				number++;
			}
		}
		return solved;
	}
	
	public Board zeroToLeft() throws ImpossibleToMoveException{
		int xZero = getZero().getX();
		int yZero = getZero().getY();
		
		int changingX= 999;
		if(xZero>0){ 
			changingX = xZero-1;
		}
		else throw new ImpossibleToMoveException();
		int changingY = yZero;
		
		Board newBoard = copy(this);
		int tmp = state[yZero][xZero];
		newBoard.state[yZero][xZero]=state[changingY][changingX];
		newBoard.state[changingY][changingX]=tmp;
		newBoard.localizateZero();
		return newBoard;
	}
	
	public Board zeroToRight() throws ImpossibleToMoveException{
		int xZero = getZero().getX();
		int yZero = getZero().getY();
		
		int changingX= 999;
		if(xZero<SIZE - 1){ 
			changingX = xZero+1;
		}
		else throw new ImpossibleToMoveException();
		int changingY = yZero;
		
		Board newBoard = copy(this);
		int tmp = state[yZero][xZero];
		newBoard.state[yZero][xZero]=state[changingY][changingX];
		newBoard.state[changingY][changingX]=tmp;
		newBoard.localizateZero();
		return newBoard;
	}
	
	public Board zeroToDown() throws ImpossibleToMoveException{
		int xZero = getZero().getX();
		int yZero = getZero().getY();
		
		int changingY= 999;
		if(yZero<SIZE - 1){ 
			changingY = yZero+1;
		}
		else throw new ImpossibleToMoveException();
		int changingX = xZero;
		
		Board newBoard = copy(this);
		int tmp = state[yZero][xZero];
		newBoard.state[yZero][xZero]=state[changingY][changingX];
		newBoard.state[changingY][changingX]=tmp;
		newBoard.localizateZero();
		return newBoard;
	}
	
	public Board zeroToUp() throws ImpossibleToMoveException{
		int xZero = getZero().getX();
		int yZero = getZero().getY();
		
		int changingY= 999;
		if(yZero>0){ 
			changingY = yZero-1;
		}
		else throw new ImpossibleToMoveException();
		int changingX = xZero;
		
		Board newBoard = copy(this);
		int tmp = state[yZero][xZero];
		newBoard.state[yZero][xZero]=state[changingY][changingX];
		newBoard.state[changingY][changingX]=tmp;
		newBoard.localizateZero();
		return newBoard;
	}
	
	public void print(){
		String toPrint="";
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				toPrint+=state[i][j]+"\t";
			}
			System.out.println(toPrint);
			toPrint="";
		}
	}
}
