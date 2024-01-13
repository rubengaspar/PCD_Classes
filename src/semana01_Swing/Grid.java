import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;


public class Grid {

	
	private int lines;
	private int columns;
	private int size;
	private JFrame frame;
	private JPanel panel;

	public Grid(String title, int lines, int columns, int size) {
		this.lines=lines;
		this.columns=columns;
		this.size=size;
		
		frame = new JFrame(title);

		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());

		addFrameContent();

		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		frame.pack();

	}
	
	// Solução alternativa para evitar cópia final dos índices
	private class CellListener extends MouseAdapter {
		private int i,j;
	
	    public CellListener(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		public void mouseClicked(MouseEvent e) {
	    	mouseAction(i,j);
	    	
	    }
	}
	private void addFrameContent() {
		panel = new JPanel();

		/* para organizar o conteudo em grelha (linhas x colunas)
		se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
		e estas sao acrescentadas automaticamente */
		panel.setLayout(new GridLayout(lines, columns));
		frame.add(panel);
		
		for(int i=0;i!=lines;i++)
			for(int j=0;j!=columns;j++){
				JLabel l=new JLabel();
				final int i1=i;
				final int j1=j;
				l.setPreferredSize(new Dimension(lines*size, columns*size));
				l.setFont(new Font("Arial", Font.BOLD, 12));
				l.setHorizontalAlignment(SwingConstants.CENTER);
				Border border = BorderFactory.createLineBorder(Color.black, 1);
				l.setBorder(border);
//				l.addMouseListener(new MouseAdapter() {
//				    public void mouseClicked(MouseEvent e) {
//				    	mouseAction(i1,j1);
//				    }
//				});
				// Alternativa
				l.addMouseListener(new CellListener(i, j));
				panel.add(l);
			}
	}
	
	protected void mouseAction(int i, int j){
		JOptionPane.showMessageDialog(frame, "You pressed cell "+i+","+j);
	}

		
	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
	}
	
	protected void setCell(int i, int j, String string) {
		JLabel l=(JLabel) panel.getComponent(i*columns +j);
		//System.err.println("Updating component "+(i*columns +j)+" conteudo:"+l.getText());
		l.setText(string);
		
		frame.invalidate();
		
	}
	
	public static void main(String[] args) {
	    Grid grid = new Grid("Test", 5, 4, 10);
	    grid.open();
	}
}
