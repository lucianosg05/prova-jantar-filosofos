package tarefa3;

import java.util.Random;
import java.util.concurrent.Semaphore;

// Nesta versão, um semáforo limita quantos filósofos podem tentar pegar os garfos ao mesmo tempo.
public class Filosofo extends Thread {

    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;

    // Semáforo compartilhado entre todos os filósofos
    private final Semaphore semaforo;

    // Estatística: quantas vezes o filósofo comeu
    private int vezesComeu = 0;

    private final Random random = new Random();

    public Filosofo(int id, Garfo esquerdo, Garfo direito, Semaphore semaforo) {
        this.id = id;
        this.garfoEsquerdo = esquerdo;
        this.garfoDireito = direito;
        this.semaforo = semaforo;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    // Antes de pegar os garfos, o filósofo precisa adquirir uma permissão do semáforo.

    private void comer() throws InterruptedException {

        System.out.println("Filósofo " + id + " tenta entrar na região crítica (semáforo).");

        // Tenta adquirir permissão no semáforo
        semaforo.acquire();

        try {
            System.out.println("Filósofo " + id + " conseguiu permissão do semáforo.");

            // Ordem padrão: esquerdo depois direito
            System.out.println("Filósofo " + id + " tenta pegar o garfo esquerdo (" + garfoEsquerdo.getId() + ").");
            synchronized (garfoEsquerdo) {

                System.out.println("Filósofo " + id + " pegou o garfo esquerdo (" + garfoEsquerdo.getId() + ").");
                System.out.println("Filósofo " + id + " tenta pegar o garfo direito (" + garfoDireito.getId() + ").");

                synchronized (garfoDireito) {
                    System.out.println("Filósofo " + id + " pegou ambos os garfos e começou a comer.");

                    Thread.sleep((random.nextInt(3) + 1) * 1000);

                    vezesComeu++;

                    System.out.println("Filósofo " + id + " terminou de comer e soltou os garfos.");
                }
            }
        } finally {
            // Libera a permissão do semáforo para outro filósofo
            semaforo.release();
            System.out.println("Filósofo " + id + " liberou a permissão do semáforo.");
        }
    }

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
