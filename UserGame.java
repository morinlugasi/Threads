package Ex2;

//Heba Abukaf ID: 323980441
//Morin Lugasi ID: 207576067
import java.util.ArrayList;

public class UserGame extends Game {
	private Game game;
	private SelfPlayer player1 = new SelfPlayer(playerType.x,game);
	private UserPlayer player2 = new UserPlayer(playerType.o,game); 

	public void start() {
		player1.setGame(this);
		player2.setGame(this);
		Thread thread1 = new Thread(player1);
		Thread thread2 = new Thread(player2);
		thread1.start();
		thread2.start();
    	System.out.println("Game is starting ...");
		resetBord();
		printBoard();
		try {
			thread1.join();
			thread2.join();

			if(isFullBoard()) {
				System.out.println("Winner: " );

			} else {
				System.out.println("Choose cell");
			}
		} catch (InterruptedException e) {
			return;
		}
	}

	public synchronized void printBoard() {
		super.printBoard();
	}

	public void resetBord() {
		super.resetBoard();
	}

	public synchronized playerType getTurn() {
		return super.getTurn();
	}

	public ArrayList<CellCoordinates> getFreeCells() {
		return super.getFreeCells();
	}

	public boolean isFullBoard() {
		return super.isFull();
	}

	public synchronized void setCell(playerType type, int row, int col) throws Exception {
		try {
			while (type != getTurn())
				wait();
		}catch (InterruptedException e) {
			return;
		}
		super.setCell(col, row, type);
		notifyAll();
	}

	public playerType getCell(int row, int col) {
		return super.getCell(row, col);
	}

}
