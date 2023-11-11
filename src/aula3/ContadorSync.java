package aula3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContadorSync {

    private int counter;
    private Lock lock = new ReentrantLock(); // Cadeado Extrinsico, o intrinseco é definido dentro do próprio metodo

    public ContadorSync() {
        this.counter = 0;
    }

    public void increment() {

        // para este caso é a mesma coisa usar synchonized aqui ou no nome da classe
        // O mesmo cadeado (this, ou outra variavel, tem de ser o mesmo quando 2 secções sao concorreentes)
        // Ao fazer o synchronized diretamente na declaração do método, estamos a dizer que o cadeado é o this
        lock.lock(); // synchronized (lock) { }
        try {
            counter++;
        }
        finally {
            lock.unlock();
        }


        // count = count + 1
        // Operações (não é atómica)
        // READ count
        // MODIFY add
        // WRITE count
        // Para evitar que as operações se sobreponham, usamos cadeado

    }

    public int get() {
        return counter;
    }

    private class ConcurrentCount implements Runnable {

        private ContadorSync sharedCounter;
//        public ConcurrentCount(Contador sharedCounter) {
//            this.sharedCounter = sharedCounter;
//        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                ContadorSync.this.increment();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ContadorSync sharedCounter = new ContadorSync();

        Thread[] threads = new Thread[4];

        for (int i = 0; i < threads.length; i++) {
            // ao criar as ConcurrentCount estamos a criar threads
            // threads[i] = new ConcurrentCount(sharedCounter); // partilha explicita
            ConcurrentCount task= sharedCounter.new ConcurrentCount();
            threads[i] = new Thread(task); // partilha implicita
        }
//        for (ConcurrentCount t : threads)
//        {
//            t.start();
//        }
//        for (ConcurrentCount t : threads)
//        {
//            t.join();
//        }d
        System.out.println("Counter value: " + sharedCounter.get());
    }


}
