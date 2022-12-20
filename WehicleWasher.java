package Ex2;

//Heba Abukaf ID: 323980441
//Morin Lugasi ID: 207576067
import java.util.ArrayList;
import java.util.Queue;

public class WehicleWasher{

	private int washingStands;
	private int numWehicle;
	private double washingTime;
	private double betweenTime;
	private   WehicleLogger file;

	private Queue<Wehicle> watingWash;
	private ArrayList<Wehicle> whileWash;
	private ArrayList<Car> washedCar;
	private ArrayList<SUV> washedSuv;
	private ArrayList<Truck> washedTruck;
	private ArrayList<MiniBus> washedMiniBus;

	private  int[] stands=new int[washingStands];

	public WehicleWasher(int washingStands, double washingTime){
		this.washingStands = washingStands;
		this.washingTime = washingTime;
		washingTime = System.currentTimeMillis();
		whileWash = new ArrayList<Wehicle> ();
		washedCar = new ArrayList<Car>();
		washedSuv = new ArrayList<SUV>();
		washedTruck = new ArrayList<Truck>();
		washedMiniBus = new ArrayList<MiniBus>();
	}

	public void enterQueue(Wehicle vehicle) throws InterruptedException {
		synchronized(watingWash){
			watingWash.add(vehicle);
			System.out.println("in Queue: " + vehicle);
			Thread.sleep((long) washingTime);
			file.write(vehicle, "Get in Queue");
		}
	}

	public void getWash(Wehicle w) throws InterruptedException {
		synchronized(this){
			while(isFull()){
				wait();
				enterQueue(w);
			}
			whileWash.add(w);
			notifyAll();
			takeStand();
		}
	}
	public synchronized void takeStand(){
		for(int i=0;i< washingStands;i++)
			if(stands[i] == 1) {
				stands[i] = 0;
				break;
			}

	}
	public synchronized void freeStand(){
		for(int i=0;i< washingStands;i++)
			if(stands[i] == 0) {
				stands[i] = 1;
				break;
			}

	}
	public synchronized void removeFromStand(Wehicle w) throws InterruptedException{
		if(w instanceof Car) 
			washedCar.add((Car) w);
		if(w instanceof SUV) 
			washedSuv.add((SUV) w);
		if(w instanceof Truck) 
			washedTruck.add((Truck) w);
		if(w instanceof MiniBus) 
			washedMiniBus.add((MiniBus) w);
		Thread.sleep((long) betweenTime);
		removeFromQueue();
		freeStand();
	}
	public boolean isFull() {
		for(int i=0;i< washingStands;i++)
			if(stands[i] == 0)
				return false;
		return true;

	}
	public void removeFromQueue() {
		watingWash.remove();
	}

}
