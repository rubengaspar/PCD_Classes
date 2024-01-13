package Semana02;

import java.util.Random;

public class EscritaConcorrente1  extends Thread {
		Random r=new Random();
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					int sleepTime=r.nextInt(4);
					// Pause 
					System.out.println(currentThread() + ": sleep for "+
							sleepTime+" seconds");
					sleep(sleepTime*1000);
				} catch (InterruptedException e) {
					System.out.println(currentThread()
							+ ": "+e.toString());
				}
			}
			System.out.println(currentThread() + ": DONE");
		}
	
	public static void main(String[] args) {
		EscritaConcorrente1 t=new EscritaConcorrente1();
		t.start();
		t=new EscritaConcorrente1();
		t.start();
	}

}
