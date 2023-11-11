package aula4;

/**
 * Classe Produtore - THREAD
 *
 * - run() - Produzir um numero fixo e javalis e coloca-los na mesa
 *
 *
 */

public class Cozinheiro extends Thread {

    private int id; // Identificador
    private int max; // numero de javalis a produzir
    private BufferMesa mesa; // Buffer Partilhado

    public Cozinheiro(int id, int max, BufferMesa mesa) {
        super("Cozinheiro-" + id);
        this.id = id;
        this.max = max;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            for (; i < max; i++) {
                Javali j = new Javali(this.id, i);
                mesa.put(j);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " - Interrompida");
        }
        System.out.println(getName() + " - produziu " + i + " javalis");
    }




}
