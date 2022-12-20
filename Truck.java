package Ex2;

public class Truck extends Wehicle {

	public Truck(WehicleWasher w, String id) {
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
