package aula1;

public class Galo extends Grid {

    private boolean xIsPlaying = true;
    public Galo() {
        super("Jogo do Galo", 3, 3, 50);
    }

    @Override
    protected void mouseAction(int id) {
        if (xIsPlaying) {
            setCell(id, "X");
            xIsPlaying = false;
        }
        else {
            setCell(id, "O");
            xIsPlaying = true;
        }
    }


    public static void main(String[] args) {
        Galo game = new Galo();
        game.open();
    }
}
