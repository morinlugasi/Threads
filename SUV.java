package Ex2;

//Heba Abukaf ID: 323980441
//Morin Lugasi ID: 207576067

public class SUV extends Wehicle{

	public SUV(WehicleWasher w, String string) {
		super(w, string);
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
