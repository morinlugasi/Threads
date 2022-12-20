package Ex2;

public abstract class Wehicle implements Runnable {
	WehicleWasher w;
	String id;

	public Wehicle(WehicleWasher w, String id) {
		this.w = w;
		this.id = id;
	}
	
}
