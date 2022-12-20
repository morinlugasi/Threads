package Ex2;
//Heba abu kaf ID: 323980441
//Morin Lugasi ID: 207576067

public class Car extends Wehicle {

	public Car(WehicleWasher w, String id) {
		super(w, id);
	}

	@Override
	public void run() {
		try {
			w.getWash(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
