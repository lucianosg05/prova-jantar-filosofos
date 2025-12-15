package tarefa2;

import java.util.Random;

// Cada filósofo é uma thread que alterna entre pensar e comer
// Nesta versão, um filósofo pega os garfos em ordem diferente para evitar deadlock
public class Filosofo extends Thread {

    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;

    // Contador de quantas vezes o filósofo conseguiu comer
    private int vezesComeu = 0;

    private final Random random = new Random();

    public Filosofo(int id, Garfo esquerdo, Garfo direito) {
        this.id = id;
        this.garfoEsquerdo = esquerdo;
        this.garfoDireito = direito;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    // O filósofo de ID 4 pega primeiro o garfo direito, enquanto os outros pegam primeiro o esquerdo
    private void comer() throws InterruptedException {

        // Define a ordem dos garfos
        Garfo primeiro;
        Garfo segundo;

        if (id == 4) {
            // Filósofo 4 pega na ordem inversa (direito primeiro)
            primeiro = garfoDireito;
            segundo = garfoEsquerdo;
        } else {
            // Os demais seguem a ordem padrão
            primeiro = garfoEsquerdo;
            segundo = garfoDireito;
        }

        System.out.println("Filósofo " + id + " tenta pegar o garfo " + primeiro.getId() + ".");

        synchronized (primeiro) {
            System.out.println("Filósofo " + id + " pegou o garfo " + primeiro.getId() + ".");
            System.out.println("Filósofo " + id + " tenta pegar o garfo " + segundo.getId() + ".");

            synchronized (segundo) {
                System.out.println("Filósofo " + id + " pegou ambos os garfos e começou a comer.");

                Thread.sleep((random.nextInt(3) + 1) * 1000);

                // Incrementa estatística
                vezesComeu++;

                System.out.println("Filósofo " + id + " terminou de comer e soltou os garfos.");
            }
        }
    }

    // Retorna quantas vezes o filósofo comeu
    public int getVezesComeu() {
        return vezesComeu;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
