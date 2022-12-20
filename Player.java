package Ex2;

//Heba Abukaf ID: 323980441
//Morin Lugasi ID: 207576067
import java.util.Random;
import Ex2.Game.playerType;

public abstract class  Player implements Runnable {
	Random random = new Random();
	protected playerType player;
	protected Game game;

	public Player(playerType player,Game game) {
		this.game = game;
		this.player = player;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	public playerType getPlayerT() {
		return player;
	}

	public void setPlayerT(playerType playerT) {
		this.player = playerT;
	}	
	public void run() {
		}
	
}
