package aula1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grid {

    private JFrame frame;
    private JPanel gridPanel;


    public Grid(String header, int rows, int cols, int width){

        frame = new JFrame(header);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addFrameContent(rows, cols, width);
        frame.pack();
    }


    private void addFrameContent(int rows, int cols, int width) {
        frame.setLayout(new FlowLayout());

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        gridPanel.setPreferredSize(new Dimension(rows * width,cols * width));
        gridPanel.setFont(new Font("Arial", Font.BOLD, width-10));
        frame.add(gridPanel);

        JLabel label;
        Border border = BorderFactory.createLineBorder(Color.black,2);

        for (int i = 0; i < rows*cols; i++) {
            final int finalI = i;
            label = new JLabel("");
            label.setBorder(border);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mouseAction(finalI);
                }
            });

            gridPanel.add(label);
        }
    }

    protected void mouseAction(int i) {
        JOptionPane.showMessageDialog(frame, "You Pressed Square: " + i);
    }

    public void open() {
        frame.setVisible(true);
    }

    protected void setCell(int id, String symbol) {
        JLabel currentLabel = (JLabel) (gridPanel.getComponent(id));
        if (currentLabel.getText().compareTo("") == 0) {
            currentLabel.setText(symbol);
        }
        else {
            JOptionPane.showMessageDialog(frame, "Position already occupied");
        }

    }


    public static void main(String[] args) {
        Grid window = new Grid("Teste",3,3,50);
        window.open();
        System.out.println();
    }
}
