package tarefa1;

import java.util.Random;

// Cada filósofo é uma Thread que alterna entre pensar e comer
public class Filosofo extends Thread {

    private final int id;

    // Referências para os garfos da esquerda e da direita
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;

    // Usado para gerar tempos aleatórios
    private final Random random = new Random();

    public Filosofo(int id, Garfo esquerdo, Garfo direito) {
        this.id = id;
        this.garfoEsquerdo = esquerdo;
        this.garfoDireito = direito;
    }

    // O filósofo "dorme" por um tempo aleatório entre 1 e 3 segundos
    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    // O filósofo tenta pegar primeiro o garfo esquerdo e depois o direito
    private void comer() throws InterruptedException {

        // Tentativa de pegar o garfo esquerdo
        System.out.println("Filósofo " + id + " tenta pegar o garfo esquerdo (" + garfoEsquerdo.getId() + ").");

        synchronized (garfoEsquerdo) {
            // Se entrou aqui, significa que conseguiu pegar o garfo esquerdo
            System.out.println("Filósofo " + id + " pegou o garfo esquerdo (" + garfoEsquerdo.getId() + ").");

            // Agora tenta pegar o garfo direito
            System.out.println("Filósofo " + id + " tenta pegar o garfo direito (" + garfoDireito.getId() + ").");

            synchronized (garfoDireito) {
                // Conseguiu pegar os dois garfos, então pode comer
                System.out.println("Filósofo " + id + " pegou ambos os garfos e começou a comer.");

                // Simula o tempo de alimentação (entre 1 e 3 segundos)
                Thread.sleep((random.nextInt(3) + 1) * 1000);

                // Após comer, os garfos são liberados automaticamente ao sair do synchronized
                System.out.println("Filósofo " + id + " terminou de comer e soltou os garfos.");
            }
        }
    }

    // O filósofo fica em loop infinito alternando entre pensar e comer
    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            // Caso a thread seja interrompida
            Thread.currentThread().interrupt();
        }
    }
}
