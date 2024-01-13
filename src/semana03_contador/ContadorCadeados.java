package semana03_contador;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContadorCadeados {
	public class Incrementador extends Thread {

		@Override
		public void run() {
			for(int i=0; i!=1000;i++)
				incrementar();
		}

	}
	private int valor;
	private Lock lock=new ReentrantLock();

	public void incrementar(){
		lock.lock();
		try {
			valor++;
		}finally {
			lock.unlock();
		}
	}
	public void decrementar(){
		lock.lock();
		try {
			valor--;
		}finally {
			lock.unlock();
		}
	}
	public int getValor(){
		return valor;
	}

	public static void main(String[] args){
		Thread[] threads=new Thread[4];
		ContadorAtomico contador= new ContadorAtomico();
		for(int i=0;i!=threads.length;i++)
			threads[i]=contador.new Incrementador();
		long initTime= System.currentTimeMillis();
		for(Thread t:threads)
			t.start();
		for(Thread t:threads)
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("Duracao:"+(System.currentTimeMillis()-initTime)+" Valor final:"+contador.getValor());
	}

}
