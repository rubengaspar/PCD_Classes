package aula1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppExample {

    private JFrame frame;

    // Constructor
    public AppExample() {
        frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addFrameContent();
        // frame.setSize(300,200);
        frame.pack();
        frame.setVisible(true);
    }

    // Add content to frame
    private void addFrameContent() {

        // Flow layout vai inserindo de forma sequencial
        frame.setLayout(new FlowLayout());

        // Grid layout - linhas e colunas e insere linha a linha
        frame.setLayout(new GridLayout(2,1));

        JPanel pnLabels = new JPanel(new FlowLayout());
        JPanel pnButtons = new JPanel(new GridLayout(2,1));

        JLabel lbl1 = new JLabel("Label 1");
        JLabel lbl2 = new JLabel("Label 2");
        JLabel lbl3 = new JLabel("Label 3");

        JButton btn1 = new JButton("Button 1");
        JButton btn2 = new JButton("Button 2");
        JButton btn3 = new JButton("Button 3");

        // FlowLayout e GridLayout
        frame.add(pnLabels);
        frame.add(pnButtons);

        pnLabels.add(lbl1);
        pnLabels.add(lbl2);
        pnLabels.add(lbl3);

        pnButtons.add(btn1);
        pnButtons.add(btn2);
        pnButtons.add(btn3);

        // Event Handlers - 3 formas de fazer (sugerido usar a classe anonima)
        // Bt1 - classe interna
        btn1.addActionListener(new ListenerButton1());
        // Bt2 - classe anonima
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Button 2 Clicked");
            }
        });
        // Bt3 - Expressão lambda (apenas pode ser usado no caso de ser apenas um metodo a implementar, metodo funcional)
        btn3.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Button 3 Clicked"));

// BorderLayout - utiliza direção para definir localização
//        frame.add(lbl1, BorderLayout.NORTH);
//        frame.add(lbl2, BorderLayout.CENTER);
//        frame.add(lbl3, BorderLayout.SOUTH);
    }

    // Action listener para definir a ação do evento
    private class ListenerButton1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "Button 1 Clicked");
        }

    }

    // Main for testing
    public static void main(String[] args) {
        AppExample app = new AppExample();
    }

}
