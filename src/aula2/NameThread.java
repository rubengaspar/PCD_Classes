package aula2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NameThread extends Thread {

    Random rand = new Random();
    public NameThread(int id) {
        super("thread " + id);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - started");
            try {
                // adormecer entre 1000 ms e 2000ms [1000, 2000[
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " - iteration #" + i);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000) + 1000); // rand.nextInt()
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " - interrupted");
        }

        System.out.println(Thread.currentThread().getName() + " - ended");
    }

    public static void main(String[] args) throws InterruptedException {
        // lista de tarefas da thread main
        System.out.println(Thread.currentThread().getName() + " - started");

        NameThread thread1 = new NameThread(1);
        NameThread thread2 = new NameThread(2);
        // Tarefas
        thread1.start();
        thread2.start();

        // Ex 2 - Altere o main, passando a usar o método join da classe Thread de modo a que a thread principal,
        // do main, espere pelo fim das outras duas threads antes de escrever na consola que terminou o programa.

        // a thread que invoca espera que a thread invocada termine para terminar a thread que invoca
        // thread1.join();
        // thread2.join();


        // Ex 3 - Altere o método main para que este ao fim de 4 segundos interrompa as duas threads,
        // que neste caso devem correr repetidamente até que tal aconteça. Quando as threads forem interrompidas devem terminar.

        // Interromper/parar uma thread é feito com o método interrupt
        thread1.interrupt();
        thread2.interrupt();

        // A thread pode ser interrompida por isso temos de incluir ou a InterruptedException ou envolver com um try catch
        Thread.sleep(100);

        // termina a thread main
        System.out.println(Thread.currentThread().getName() + " - ended");
    }

}
