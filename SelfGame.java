package Ex2;

//Heba Abukaf ID: 323980441
//Morin Lugasi ID: 207576067
import java.util.ArrayList;

public class SelfGame extends Game{
	private Game game;
	SelfPlayer player1= new SelfPlayer(playerType.x,game);
    SelfPlayer player2 = new SelfPlayer(playerType.o,game);

    public void start() {
    	player1.setGame(this);
        player2.setGame(this);
        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);
        thread1.start();
        thread2.start();
    	System.out.println("Game is strating ...");
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

	public synchronized void resetBord() {
		super.resetBoard();
	}

	public synchronized playerType getTurn() {
		return super.getTurn();
	}

	public synchronized ArrayList<CellCoordinates> getFreeCells() {
		return super.getFreeCells();
	}
	
	public synchronized boolean isFullBoard() {
		return super.isFull();
	}

    public synchronized void setCell(int row, int col, playerType type) throws Exception {
		try {
			while (type != getTurn())
				wait();
		} catch (InterruptedException e) {
			return;
		}
		super.setCell(row, col, type);
		this.notifyAll();
	}

	public playerType getCell(int row, int col) {
		return super.getCell(row, col);
		}
	
}
