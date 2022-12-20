package Ex2;

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
