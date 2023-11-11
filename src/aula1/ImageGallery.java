package aula1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

public class ImageGallery {

    private JFrame frame;
    private JLabel imageLabel;
    private File[] files;
    private int currentIndex = 0;


    public ImageGallery() {
        frame = new JFrame("Image Gallery");

        frame.setSize(600, 300);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addFrameContent();

        String path = "images"; // pasta criada dentro do projeto Eclipse,
        //onde devem ser colocadas as imagens
        files = new File(path).listFiles(new FileFilter() {
            public boolean accept(File f) {
                // se retornar verdadeiro, f será incluido

                return true;
                // colocar return true para serem escolhidos todos os ficheiros
            }
        });

    }

    public void open() {
        // Abrir a janela
        frame.setVisible(true);
    }

    private void updateImage() {
        if (files != null && files.length > 0 && currentIndex < files.length) {
            ImageIcon icon = new ImageIcon(files[currentIndex].getAbsolutePath());
            imageLabel.setIcon(icon);
        }
    }


    private void addFrameContent() {
        frame.setLayout(new BorderLayout());

        JButton leftArrowButton = new JButton("<<<");
        JButton rightArrowButton = new JButton(">>>");

        imageLabel = new JLabel();


        frame.add(leftArrowButton, BorderLayout.WEST);
        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(rightArrowButton, BorderLayout.EAST);


        // Event Listener para a left arrow
        leftArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex == 0) {
                 currentIndex = files.length;
                }
                else {
                    currentIndex--;
                }
                updateImage();
            }
        });

        // Event Listener para a right arrow
        rightArrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex == files.length) {
                    currentIndex = 0;
                }
                else {
                    currentIndex++;
                }
                updateImage();
            }
        });
    }

    public static void main(String[] args) {
        ImageGallery window = new ImageGallery();
        window.updateImage();
        window.open();
    }
}
