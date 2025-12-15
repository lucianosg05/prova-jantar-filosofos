package tarefa4;

public class JantarDosFilosofos {

    public static void main(String[] args) throws InterruptedException {

        int NUM_FILOSOFOS = 5;

        Mesa mesa = new Mesa(NUM_FILOSOFOS);
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new Filosofo(i, mesa);
            filosofos[i].start();
        }

        Thread.sleep(120_000);

        System.out.println("\n=== Estatísticas de Execução ===");
        for (Filosofo f : filosofos) {
            System.out.println("Filósofo " + f.getName() + " comeu " + f.getVezesComeu() + " vezes.");
        }

        System.exit(0);
    }
}
