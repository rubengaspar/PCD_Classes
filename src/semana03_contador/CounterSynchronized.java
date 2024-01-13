package semana03_contador;

public class CounterSynchronized {

    int value = 0;
    int kValue = 1000;

    public class kIncrement extends Thread {
        public void run() {
            for (int i = 0; i < kValue; i++) {
                increment();
            }
        }
    }


    public synchronized void increment() {
        this.value++;
    }
    public synchronized void decrement() {
        this.value--;
    }
    int getValue () {
        return this.value;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[4];



    }
}
