package semana02_Threads;

import javax.naming.Name;
import java.util.Random;

public class NameThread extends Thread {


    int sleeptime;
    Random random = new Random();

    public NameThread (int id) {
        this.setName("" + id);
    }
    public void run() {

        for (int i = 0; i < 10; i++) {

            try {
                sleeptime = random.nextInt(2) + 1;
                System.out.println(currentThread() + " will sleep for " + sleeptime + " seconds.");
                sleep(sleeptime);
            } catch (InterruptedException e) {
                System.out.println(currentThread() + ": " + e.toString());
            }

        }

        System.out.println(currentThread() + " DONE");
    }

    public static void main(String[] args) {
        NameThread thread = new NameThread(1);
        thread.start();
        thread = new NameThread(2);
        thread.start();
    }

}



