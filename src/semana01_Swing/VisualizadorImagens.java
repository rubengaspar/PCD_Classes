import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class VisualizadorImagens {
	private JFrame frame;
	private File imageFolder;
	private List<ImageIcon> icons = new ArrayList<>();
	private int selectedImage=-1;
	private JLabel label;
	private JLabel nomeImagem;

	public VisualizadorImagens(String path) {
		frame = new JFrame(path);

		imageFolder=new File(path);

		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());

		addFrameContent();

		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		frame.pack();

	}

	private void addFrameContent() {

		/* para organizar o conteudo em grelha (linhas x colunas)
		se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
		e estas sao acrescentadas automaticamente */
		frame.setLayout(new BorderLayout());

		nomeImagem=new JLabel();
		frame.add(nomeImagem,BorderLayout.NORTH);
		
		label = new JLabel("No Image selected");
		frame.add(label,BorderLayout.CENTER);




		JButton buttonLeft = new JButton("<");
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedImage>0 ){
					label.setText("");
					label.setIcon(icons.get(--selectedImage));
					nomeImagem.setText(icons.get(selectedImage).getDescription());
				}
				else{
					selectedImage=-1;
					label.setText("No Image selected");
					nomeImagem.setText("No Image selected");
					label.setIcon(null);
				}

				//frame.invalidate();
			}
		});
		frame.add(buttonLeft, BorderLayout.WEST);

		JButton buttonRight = new JButton(">");
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedImage<icons.size()-1){
					label.setText("");
					label.setIcon(icons.get(++selectedImage));
					nomeImagem.setText(icons.get(selectedImage).getDescription());
				}
				else{
					selectedImage=icons.size();
					label.setText("No Image selected");
					nomeImagem.setText("No Image selected");
					label.setIcon(null);
				}

				//frame.invalidate();
			}
		});
		frame.add(buttonRight,BorderLayout.EAST);

		JButton buttonUpdate = new JButton("Update");
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshImagesFromFolder();
			}

			
		});
		frame.add(buttonUpdate,BorderLayout.SOUTH);
		
		refreshImagesFromFolder();

	}
	
	private void refreshImagesFromFolder() {
		icons.clear();
		File[] files = imageFolder.listFiles(new FileFilter() {
		     public boolean accept(File f) {     
		          return f.isFile() && f.getName().endsWith("jpg");
		     }
		});
		
		System.err.println("Files:"+files.length);
		
		for(File file:files){
			selectedImage=0;
			label.setText("");
			ImageIcon icon=new ImageIcon(file.getAbsolutePath());
			icon.setDescription(file.getName());
			icons.add(icon);
		}	
		
		nomeImagem.setText(icons.get(selectedImage).getDescription());
		label.setIcon(icons.get(selectedImage));
		//frame.invalidate();
		
	}
	
	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		VisualizadorImagens window = new VisualizadorImagens("images");
		window.open();
	}
}
