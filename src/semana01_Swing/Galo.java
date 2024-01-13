
public class Galo extends Grid{
	private boolean xIsPlaying=true;
	public Galo() {
		super("Galo", 3, 3, 10);
	}

	@Override
	protected void mouseAction(int i, int j) {
		if(xIsPlaying)
			setCell(i, j,"X");
		else
			setCell(i, j,"0");
		
		xIsPlaying=!xIsPlaying;
	}

	public static void main(String[] args) {
		Galo grid = new Galo();
	    grid.open();
	}
	
	

}
