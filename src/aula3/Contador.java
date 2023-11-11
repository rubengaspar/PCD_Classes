package aula3;

public class Contador {

    private int counter;

    public Contador() {
        this.counter = 0;
    }

    public void increment() {

        counter++;
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

    private class ConcurrentCount extends Thread {

        private Contador sharedCounter;
//        public ConcurrentCount(Contador sharedCounter) {
//            this.sharedCounter = sharedCounter;
//        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                Contador.this.increment();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Contador sharedCounter = new Contador();

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
