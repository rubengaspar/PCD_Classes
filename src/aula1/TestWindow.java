package aula1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestWindow {

    private JFrame frame;

    public TestWindow() {
        frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrameContent();
        frame.setSize(800, 500);
    }

    public void open() {
        // para abrir a janela (torna-la visivel)
        frame.setVisible(true);
    }

    public void close() {
        // tornar a janela invisivel
        frame.setVisible(false);
    }

    private void addFrameContent() {
        frame.setLayout(new GridLayout(3,3));

        JLabel checkInfo = new JLabel("Check or Uncheck box");


    }


    public static void main(String[] args) {
        TestWindow testWindow = new TestWindow();
        testWindow.open();


    }


}
