package aula1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrameTest {
    private JFrame frame;
    private int frameHeight;
    private int frameWidth;

    public FrameTest() {
        frame = new JFrame("Hello");

        // Get screen size
        double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        // Set frame height to 40% of screen size
        frameWidth = (int) (screenWidth * 0.4);
        frameHeight = (int) (screenHeight * 0.4);

        System.out.println("Width: " + frameWidth);
        System.out.println("Height: " + frameHeight);

        frame.setSize(frameWidth, frameHeight); // pq é que este não funciona?
        frame.setLocation(200, 200);

        // para que o botao de fechar a janela termine a aplicacao
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addFrameContent();

        // para que a janela se redimensione de forma a ter todo o seu conteudo visivel
        // frame.pack();
    }

    public void open() {
        // para abrir a janela (torna-la visivel)
        frame.setVisible(true);
        // Get Screen size

        // Como fazer para
    }

    private void addFrameContent() {


		/* para organizar o conteudo em grelha (linhas x colunas)
		se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
		e estas sao acrescentadas automaticamente */
        frame.setLayout(new GridLayout(4,2));


        JLabel titleLabel = new JLabel("Keep Proportional");
        JLabel widthLabel = new JLabel("Width");
        JLabel heightLabel = new JLabel("Height");

        JTextField titleTextField = new JTextField("Hello");
        JTextField widthTextField = new JTextField("300");
        JTextField heightTextField = new JTextField("150");

        JButton updateButton = new JButton("Update");
        JButton redimButton = new JButton("Square");

        JCheckBox centerCheckBox = new JCheckBox("Center");

        frame.add(titleLabel);          // 0, 0
        frame.add(centerCheckBox);      // 0, 1
        frame.add(widthLabel);          // 1, 0
        frame.add(widthTextField);      // 1, 1
        frame.add(heightLabel);         // 2, 0
        frame.add(heightTextField);     // 2, 1
        frame.add(updateButton);        // 3, 0
        frame.add(redimButton);         // 3, 1

        // Classe Anonima
        redimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (heightTextField.getText().compareTo(widthTextField.getText()) == 0) {
                    JOptionPane.showMessageDialog(frame, "Sizes are already equal");
                }
                else {
                    heightTextField.setText(widthTextField.getText());
                    JOptionPane.showMessageDialog(frame, "Size changed");
                }

            }
        });


        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, centerCheckBox.isSelected() ? "checked" : "not checked");

            }
        });
    }

    public static void main(String[] args) {
        FrameTest window = new FrameTest();
        window.open();

    }
}