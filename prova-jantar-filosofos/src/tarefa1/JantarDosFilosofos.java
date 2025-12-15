package tarefa1;

public class JantarDosFilosofos {

    public static void main(String[] args) {

        // Número fixo de filósofos e garfos
        int NUM_FILOSOFOS = 5;

        // Vetor de garfos
        Garfo[] garfos = new Garfo[NUM_FILOSOFOS];

        // Vetor de filósofos
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];

        // Criação dos garfos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Garfo(i);
        }

        // Criação dos filósofos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {

            // Garfo à esquerda do filósofo
            Garfo esquerdo = garfos[i];

            // Garfo à direita (o último filósofo pega o garfo 0)
            Garfo direito = garfos[(i + 1) % NUM_FILOSOFOS];

            // Cria o filósofo com seus dois garfos
            filosofos[i] = new Filosofo(i, esquerdo, direito);

            // Inicia a thread do filósofo
            filosofos[i].start();
        }
    }
}
