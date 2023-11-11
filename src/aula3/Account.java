package aula3;

// Crie uma classe para respresentar contas bancárias e um método para depositar montantes.
public class Account {

    private double balance;


    public Account() {

    }

    // put money into the account
    public void deposit(double amount) {

    }

    // get the current balance of the account
    public double getBalance() {

        return 0;
    }

    // Crie 10 Threads Cliente, cada um  fazendo depósitos contínuos de um valor aleatório (por exemplo entre 0 e 100€)
    //  até ser interrompido. Cada cliente guarda num atributo o total que depositou.

    // O main, fará o seguinte:
    //  1. Lança os 10 clientes
    //  2. dorme 10 segundos,
    //  3. interrompe todos os clientes,
    //  4. espera que terminem (usando o join()), imprime o saldo da conta
    //      soma o total depositado por todos os clientes para confirmação.

    // Experimente a execução com e sem sincronização do metodo deposit().
    // Dica: para detetar se uma Thread foi interrompida, pode ser usado o método interrupted().
    public static void main(String[] args) {

    }



}
