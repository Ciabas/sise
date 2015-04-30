import java.util.Random;

import Exception.ImpossibleToMoveException;


public class Board {
	private int[][] state = new int[4][4];
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
	
	public Board(){
		super();
	}
	
	public Board(Board toCopy){
		this.state = toCopy.state;
		this.zero = toCopy.zero;
		//this.lastMove = toCopy.lastMove;
	}
	
	public Board copy(Board toCopy){
		Board copy = new Board(toCopy);
		if(toCopy==copy) System.out.println("ta sama referencja");
		return copy;
	}
	
	public void create(){
		int number = 1;
		for(int j = 0; j < 4; j++){
			for(int k = 0; k < 4; k++){
				state[j][k] = number;
				if(number==16) state[j][k] = 0;
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
			randomX = rand.nextInt(4);
			rand = new Random();
			randomY = rand.nextInt(4);
			rand = new Random();
			randomX1 = rand.nextInt(4);
			rand = new Random();
			randomY1 = rand.nextInt(4);
			
			tmp=state[randomX][randomY];
			state[randomX][randomY]=state[randomX1][randomY1];
			state[randomX1][randomY1]=tmp;
		}
		localizateZero();
	}
	
	public void localizateZero(){
		for(int j = 0; j < 4; j++){
			for(int k = 0; k < 4; k++){
				if(state[j][k]==0){
					this.zero.setLocation(k, j);
				}
			}
		}
	}
	
	public boolean checkIfSolved(){
		boolean solved=false;
		int number = 1;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(state[i][j] == number) solved=true;
				else if(state[3][3] == 0) solved = true;
				else solved=false;
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
		if(xZero<3){ 
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
		if(yZero<3){ 
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
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				toPrint+=state[i][j]+" ";
			}
			System.out.println(toPrint);
			toPrint="";
		}
	}
}
