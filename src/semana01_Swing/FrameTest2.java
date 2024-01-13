import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
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

public class FrameTest2 {
	private JFrame frame;
	private JTextField textWidth;
	private JTextField textHeight;

	public FrameTest2(String title) {
		frame = new JFrame(title);
		
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());
		
		addFrameContent();
		
		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		frame.pack();
	}

	public void open(int width, int height) {
		// para abrir a janela (torna-la visivel)
		frame.setSize(width, height);
		textHeight.setText(""+height);
		textWidth.setText(""+width);
		frame.setVisible(true);
	}
	private void addFrameContent() {
		JPanel panel = new JPanel();
		/* para organizar o conteudo em grelha (linhas x colunas)
		se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
		e estas sao acrescentadas automaticamente */
		panel.setLayout(new GridLayout(4,2));
		frame.add(panel);
		JLabel label = new JLabel("Title");
		panel.add(label);
		final JTextField text = new JTextField(frame.getTitle());
		panel.add(text);
		JLabel labelWidth  = new JLabel("Width");
		panel.add(labelWidth);
		textWidth = new JTextField(frame.getWidth()+"");
		panel.add(textWidth);		
		JLabel labelHeight= new JLabel("Height");
		panel.add(labelHeight);
		textHeight = new JTextField(frame.getHeight()+"");
		panel.add(textHeight);
		final JCheckBox check = new JCheckBox("center");
		panel.add(check);
		JButton button = new JButton("update");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frame.getSize().getWidth()!=Double.parseDouble(textWidth.getText())
						|| frame.getSize().getHeight()!=Double.parseDouble(textHeight.getText()))
					frame.setSize(new Dimension((int)Double.parseDouble(textWidth.getText()),
							(int)Double.parseDouble(textHeight.getText())));
				if( check.isSelected())
					frame.setLocation(
							new Point((Toolkit.getDefaultToolkit().getScreenSize().width-frame.getWidth())/2,
							(Toolkit.getDefaultToolkit().getScreenSize().height-frame.getHeight())/2));
				if(!text.getText().equals(frame.getTitle()))
					frame.setTitle(text.getText());
			}});
		panel.add(button);
	}
	

	public static void main(String[] args) {
		FrameTest2 window = new FrameTest2("Hello");
		window.open(350,200);
	}
}