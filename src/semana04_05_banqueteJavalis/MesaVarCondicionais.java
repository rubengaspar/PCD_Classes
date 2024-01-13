import java.util.ArrayList;
// Este exercício é a solução para a Semana 5!
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class MesaVarCondicionais {
	private ArrayList<Javali> javalis=new ArrayList<>();
	private Lock lock=new ReentrantLock();
	private Condition haEspaco=lock.newCondition();
	private Condition haJavali=lock.newCondition();
	public static final int CAPACIDADE=10;
	public void coloca(Javali j) throws InterruptedException {
		lock.lock();
		try {
			while(javalis.size()==CAPACIDADE)
				haEspaco.await();
			javalis.add(j);
			haJavali.signalAll();
		}finally {lock.unlock();}
	}
	public Javali retira() throws InterruptedException {
		lock.lock();
		try {
			while(javalis.isEmpty())
				haJavali.await();
			haEspaco.signalAll();
			return javalis.remove(0);
		} finally {lock.unlock();}
	}
	public class Cozinheiro extends Thread {
		public final int id;
		public Cozinheiro(int id) {	this.id = id;}
		public void run() {
			for(int i=0; i!=10;i++)
				try {
					coloca(new Javali(i, id));
					System.out.println("Coloquei:"+id+":"+i);
				} catch (InterruptedException e) {		}
		}
	}
	public class Glutao extends Thread {
		@Override
		public void run() {
			for(int i=0; i!=5;i++)
				try {
					System.out.println("Consumi:"+retira());
				} catch (InterruptedException e) {}
		}
	}
	public static void main(String[] args) {
		Vector<Thread> threads=new Vector<>();
		MesaVarCondicionais m=new MesaVarCondicionais();
		for(int i=0; i!=10;i++) {
			threads.add(m.new Glutao());
			threads.lastElement().start();
		}
		for(int i=0; i!=5;i++) {
			threads.add(m.new Cozinheiro(i));
			threads.lastElement().start();
		}
	}
}
