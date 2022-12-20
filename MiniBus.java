package Ex2;

//Heba Abukaf ID: 323980441
//Morin Lugasi ID: 207576067
public class MiniBus extends Wehicle {

	public MiniBus(WehicleWasher w, String id) {
		super(w, id);
	}

	public void run() {
		try {
			w.getWash(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
