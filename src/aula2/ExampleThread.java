package aula2;

import javax.swing.*;

public class ExampleThread extends Thread{
    public ExampleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - started");
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - running");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(Thread.currentThread().getName() + " - ended");
    }


    public static void main(String[] args) throws InterruptedException {
        // lista de tarefas da thread main
        System.out.println(Thread.currentThread().getName() + " - started");

        ExampleThread nameThread = new ExampleThread("thread 1");
        // Tarefas
        nameThread.start();

        // A thread pode ser interrompida por isso temos de incluir ou a InterruptedException ou envolver com um try catch
        Thread.sleep(100);


        // termina a thread main
        System.out.println(Thread.currentThread().getName() + " - ended");

    }

}
