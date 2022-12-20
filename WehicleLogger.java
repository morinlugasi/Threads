package Ex2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WehicleLogger {
	private int[] counter = new int[4];
	private int[] waitingTime = new int[4];
	private Object lock=new Object();
	private Object lockPrint=new Object();
	private long startTime=System.currentTimeMillis();
	private PrintWriter out;
	Scanner sc;
	public WehicleLogger(PrintWriter out) throws IOException {
		out = new PrintWriter(out);
		sc = new Scanner((Readable) out);
	}
	public void printEnterWasher() {
		long prtintTime = System.currentTimeMillis() - startTime;
		synchronized (lockPrint) {
			out.println("[info] car Id: " + Thread.currentThread().getId() + " | car name: " + Thread.currentThread().getName());
			out.println("Entered washer and joined Queue");
			out.println("Time was: " + prtintTime);
		}
	}
	public void updateWaitingTime(Wehicle w, int time){
		synchronized(lock) {
			if(w instanceof Car) {
				counter[0]++;
				waitingTime[0]+= time;
			}
			if(w instanceof SUV) {
				counter[1]++;
				waitingTime[1]+= time;
			}
			if(w instanceof Truck) {
				counter[2]++;
				waitingTime[2]+= time;
			}
			if(w instanceof MiniBus) {
				counter[3]++;
				waitingTime[3]+= time;
			}
		}
	}
	public int[] getAvgTime() {
		int []outPut=new int[4];
		synchronized(lock) {
			for(int i=0;i<waitingTime.length;i++) 
				outPut[i] = waitingTime[i]/counter[i];
		}
		return outPut;
	}
	public String read(){
		StringBuffer sbuffer = new StringBuffer();
		while (sc.hasNext())
			sbuffer.append(sc.nextLine());
		return sbuffer.toString();
	}
	public void write(Wehicle vehicle, String string) {
		out.println(string+", "+(System.currentTimeMillis()-startTime)+" Wehicle");
	}

}
