package aula2;

import javax.swing.*;

public class ExampleRunnable implements Runnable {

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

        ExampleRunnable task1 = new ExampleRunnable();

        // Dizer a tarefa que a thread vai executar
        Thread thread1 = new Thread(task1, "thread1");

        // Tarefas
        thread1.start();

        // A thread pode ser interrompida por isso temos de incluir ou a InterruptedException ou envolver com um try catch
        Thread.sleep(100);

        // termina a thread main
        System.out.println(Thread.currentThread().getName() + " - ended");

    }

}
