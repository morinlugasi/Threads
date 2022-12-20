package Ex2;

//Heba Abukaf ID: 323980441
//Morin Lugasi ID: 207576067

import java.util.ArrayList;

import Ex2.Game.playerType;

public class SelfPlayer extends Player {

	public SelfPlayer(playerType player, Game game) {
		super(player, game);
		// TODO Auto-generated constructor stub
	}
	public synchronized void run() {
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(500);
				if (this.player == game.getTurn())
					if (!game.isFull()) {
						ArrayList<CellCoordinates> freeCells = game.getFreeCells(); // get list of free cells
						int i = random.nextInt(freeCells.size());
						game.setCell(freeCells.get(i).getRow(), freeCells.get(i).getCol(), this.player);
						game.printBoard();
					} else {
						System.out.println("Board is full");
						return;
					}
				if (game.isFull()) {
					return;
				}
			} catch (InterruptedException e) {
				return;		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
