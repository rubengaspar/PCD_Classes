package aula3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContadorAtomic {

    private AtomicInteger counter;
    private Lock lock = new ReentrantLock(); // Cadeado Extrinsico, o intrinseco é definido dentro do próprio metodo

    public ContadorAtomic() {
        this.counter = new AtomicInteger();
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public AtomicInteger get() {
        return counter;
    }

    private class ConcurrentCount extends Thread {

        private ContadorAtomic sharedCounter;
//        public ConcurrentCount(Contador sharedCounter) {
//            this.sharedCounter = sharedCounter;
//        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                ContadorAtomic.this.increment();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ContadorAtomic sharedCounter = new ContadorAtomic();

        ConcurrentCount[] threads = new ConcurrentCount[4];

        for (int i = 0; i < threads.length; i++) {
            // ao criar as ConcurrentCount estamos a criar threads
            // threads[i] = new ConcurrentCount(sharedCounter); // partilha explicita
            threads[i] = sharedCounter.new ConcurrentCount(); // partilha implicita
        }
        for (ConcurrentCount t : threads)
        {
            t.start();
        }
        for (ConcurrentCount t : threads)
        {
            t.join();
        }

        System.out.println("Counter value: " + sharedCounter.get());

    }


}
