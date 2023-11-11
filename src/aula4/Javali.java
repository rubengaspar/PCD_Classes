package aula4;

public class Javali {

    private int cozId; // ID do Cozinheiro que produziu o jvali
    private int ordem; // o-esimo javali do cozinheiro codID


    public Javali(int cozId, int ordem) {
        this.cozId = cozId;
        this.ordem = ordem;
    }

    @Override
    public String toString() {
        return "Javali #" + ordem + " do cozinheiro " + cozId;
    }

}
