package semana03_contador;

public class ContadorSincronizado {
	public class Incrementador extends Thread {

		@Override
		public void run() {
			for(int i=0; i!=1000;i++)
				incrementar();
		}

	}
	private int valor;
	
	public synchronized void incrementar(){
		valor++;
	}
	public synchronized void decrementar(){
		valor--;
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
