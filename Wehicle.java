package Ex2;
//Heba Abukaf ID: 323980441
//Morin Lugasi ID: 207576067

public abstract class Wehicle implements Runnable {
	WehicleWasher w;
	String id;

	public Wehicle(WehicleWasher w, String id) {
		this.w = w;
		this.id = id;
	}
	
}
