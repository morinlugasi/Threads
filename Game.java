package Ex2;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public abstract class Game{
	protected playerType [][] gameBoard = new playerType[3][3];
	protected playerType turn;
	enum playerType {
		x, o, nul
	}
	public Game() {
		resetBoard();
		this.turn= playerType.x;
	}
	public void printBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == playerType.nul) {
					System.out.print("!");
				} else if (gameBoard[i][j] == playerType.x) {
					System.out.print("x");
				} else if (gameBoard[i][j] == playerType.o) {
					System.out.print("o");
				} else {
					System.out.print(gameBoard[i][j]);
				}
				System.out.print(" | ");
			}
			System.out.println();
		}
		System.out.println("");
	}
	public ArrayList<CellCoordinates> getFreeCells() {
		ArrayList<CellCoordinates> freeCells = new ArrayList<CellCoordinates>();
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++)
				if(gameBoard[i][j] == playerType.nul) {
					CellCoordinates temp = new CellCoordinates(i,j);
					freeCells.add(temp);
				}
		return freeCells;
	}
	public playerType getTurn() {
		return turn;
	}
	public void resetBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j] = playerType.nul;
			}
		}
		turn = playerType.x;
	}
	public boolean isFull() {
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) 
				if (gameBoard[i][j] == playerType.nul)
					return false;
		return true;
	}
	public playerType getCell(int row, int col) {
		if (row >= 0 && row < 3 && col >= 0 && col < 3)
			return gameBoard[row][col];
		throw new InvalidParameterException("invalid index!");
	}
	public synchronized void setCel(int i, int j, playerType type) throws Exception {
		try {
			while (type != getTurn())
				wait();
		} catch (InterruptedException e) {
			return;
		}setCell(i, j, type);
		this.notifyAll();
	}
	public void makeMove(CellCoordinates cell) {
		if (gameBoard[cell.getRow()][cell.getCol()] == playerType.nul) {
			gameBoard[cell.getRow()][cell.getCol()] = turn;
				if( turn == playerType.x )
					turn = playerType.o ;
				else 
					turn = playerType.x;
			printBoard();
			System.out.println();
		} else {
	            System.out.println("This cell is taken!");
		}
	}
	public void setCell(int i, int j, playerType type) throws Exception {
		if (i < 0 || i >= 3 || j < 0 || j >= 3) {
			throw new IllegalArgumentException("Illegal cell!");
		}else if(isFull()) {
			throw new Exception("Board is full!");

		}else {
			if (gameBoard[i][j] == playerType.nul) {
				gameBoard[i][j] = type;
				if (type == playerType.x)
					this.turn= playerType.o;
				else
					this.turn= playerType.x;
			}
		}

	}
}
