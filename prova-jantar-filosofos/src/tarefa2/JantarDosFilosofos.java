package tarefa2;

//Inicializa os filósofos e imprime estatísticas após o tempo de execução
public class JantarDosFilosofos {

    public static void main(String[] args) throws InterruptedException {

        int NUM_FILOSOFOS = 5;
        Garfo[] garfos = new Garfo[NUM_FILOSOFOS];
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Garfo(i);
        }

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Garfo esquerdo = garfos[i];
            Garfo direito = garfos[(i + 1) % NUM_FILOSOFOS];

            filosofos[i] = new Filosofo(i, esquerdo, direito);
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
