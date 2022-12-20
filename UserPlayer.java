package Ex2;

import java.util.Scanner;

import Ex2.Game.playerType;

public class UserPlayer extends Player  {
	UserGame game;

	public UserPlayer(playerType player, Game game) {
		super(player, game);
	}
	/*
	public void run() {
		if (game == null) {
			throw new IllegalStateException("Game is not set!");
		}
		chooseCell();
	}*/
	public synchronized void chooseCell() {
		while (true) {
			while (game.getTurn() != getPlayerT()) {
				try {
					game.wait();
					if (game.isFull()) {
						break;
					}
				} catch (InterruptedException e) {
				}
			}

			if (!game.isFull()) {
				Scanner sc = new Scanner(System.in);
				game.printBoard();
				System.out.println("Your turn! Enter the cell: ");
				int cell = sc.nextInt() - 1;
				int row = cell / 3;
				int col = cell % 3;
				game.makeMove(new CellCoordinates(row, col));
				sc.close();
			} else {
				game.notifyAll();
				break;
			}
			game.notifyAll();
		}
	}

}
