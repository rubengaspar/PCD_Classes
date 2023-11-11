package aula4;

import java.util.LinkedList;

/**
 * Classe buffer partilhado

 * - put() para colocar javalis no buffer partilhado
 * - get() para retirar javalis do buffer partilhado
 *
 */

public class BufferMesa {

    public static final int CAPACIDADE = 10;
    private LinkedList<Javali> mesa = new LinkedList<Javali>();

    public synchronized void put(Javali j) throws InterruptedException { // Cadeado ("this")

        while(mesa.size() == CAPACIDADE) { // Enquanto a mesa estiver cheia
            this.wait();
        }

        // Adicionar Javali
        mesa.add(j);
        // Notificar que há novo javali na mesa (direcionado para os Glutoes)
        this.notifyAll();

    }

    public synchronized Javali get() throws InterruptedException {

        while(mesa.isEmpty()) {
            this.wait();
        }
        // Remover
        Javali j = mesa.remove(0);
        // Notificar que ha novo espaço na mesa (direcionado para os Cozinheiros)
        this.notifyAll();
        // Devolver javali
        return j;

    }



}
