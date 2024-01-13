package semana07_ContaBancaria_DeadLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Account implements Comparable<Account>{
	int id;
	double balance;
	Lock lock= new ReentrantLock();
	public Account(int id,double balance) {
		this.id=id;
		this.balance = balance;
	}
	void withdraw(double amount){
		lock.lock();
		balance -= amount;
		lock.unlock();
	} 
	void deposit(double amount){
		lock.lock();
		balance += amount;
		lock.unlock();
	} 
	void transfer(Account to, double amount){
		if(compareTo(to)>0) {
			lock.lock();
			to.lock.lock();
		}
		else {
			to.lock.lock();
			lock.lock();
		}
		withdraw(amount);
		to.deposit(amount);
		if(compareTo(to)>0) {
			to.lock.unlock();
			lock.unlock();
		}
		else {
			lock.unlock();
			to.lock.unlock();
		}
	}
	private class Transferer extends Thread{
		private Account to;
		public Transferer(Account to) {
			this.to = to;
		}
		@Override
		public void run() {
			for(int i=0; i!=NUM_TRANSFERENCIAS;i++)
				transfer(to, 1);
		}
	}
	private static final int NUM_TRANSFERENCIAS = 10000000;
	public static void main(String[] args) throws InterruptedException{
		Account a1=new Account(1,NUM_TRANSFERENCIAS+1);
		Account a2=new Account(2,NUM_TRANSFERENCIAS+1);
		Thread t1=a1.new Transferer(a2);
		t1.start();
		Thread t2=a2.new Transferer(a1);
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Saldos:"+a1.balance+":"+a2.balance);
	}
	@Override
	public int compareTo(Account o) {
		return id-o.id;
	}
}
