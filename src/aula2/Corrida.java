package aula2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class Corrida {
    private JFrame frame;
    private JTextField[] textFields;
    private Cavalo[] cavalos;

    public Corrida() {
        frame = new JFrame("Corrida");
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        cavalos = new Cavalo[3];

        for (int i = 0; i < cavalos.length; i++) {
            cavalos[i] = new Cavalo(i);
        }

        addFrameContent();
    }

    // Open and close windows
    public void open() {
        frame.setVisible(true);
    }

    // Add content to frame
    private void addFrameContent() {
        frame.setLayout(new GridLayout(2,3));

        JButton startButton = new JButton("Iniciar");

        textFields = new JTextField[3];

        for (int i = 0; i < cavalos.length; i++) {
            frame.add(cavalos[i].field);
        }

        frame.add(startButton);


        // Event Listener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Cavalo cavalo : cavalos) {
                    cavalo.start();
                }
            }
        });
    }

    public class Cavalo extends Thread {
        public static final int MAX_MOVES = 30;
        public int moves = MAX_MOVES;
        JTextField field = new JTextField(MAX_MOVES);

        public Cavalo(int id) {
            super("thread" + id);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " - started");

                try {
                    while (moves > 0) {
                        move();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            System.out.println(Thread.currentThread().getName() + " - ended");
        }

        public void move() throws InterruptedException {
            if (moves > 0) {
                this.moves--;
                field.setText("" + moves);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            }
        }
    }

    public static void main(String[] args) {
        Corrida window = new Corrida();
        window.open();
    }

}
