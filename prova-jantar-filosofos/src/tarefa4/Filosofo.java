package tarefa4;

import java.util.Random;

//Cada filósofo é uma thread que interage com a Mesa 
public class Filosofo extends Thread {

    private final int id;
    private final Mesa mesa;

    // Estatística: quantas vezes o filósofo comeu
    private int vezesComeu = 0;

    private final Random random = new Random();

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    private void comer() throws InterruptedException {
        mesa.pegarGarfos(id);

        // Simula o tempo de alimentação
        Thread.sleep((random.nextInt(3) + 1) * 1000);
        vezesComeu++;

        mesa.soltarGarfos(id);
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
