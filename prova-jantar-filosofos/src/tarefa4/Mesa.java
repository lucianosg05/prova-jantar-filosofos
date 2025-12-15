package tarefa4;

import java.util.LinkedList;
import java.util.Queue;

//A classe Mesa funciona como um monitor.
//Ela controla o uso dos garfos e garante que todos os filósofos tenham chance de comer

public class Mesa {

    // Estado dos garfos: true = disponível, false = ocupado
    private final boolean[] garfos;

    // Fila para garantir que os filósofos comam por ordem de chegada
    private final Queue<Integer> fila;

    public Mesa(int numFilosofos) {
        this.garfos = new boolean[numFilosofos];
        this.fila = new LinkedList<>();

        // Inicialmente, todos os garfos estão livres
        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = true;
        }
    }

    //Método sincronizado para solicitar os garfos.
    //O filósofo só pode comer se:
    //-Estiver no início da fila
    //-Seus dois garfos estiverem disponíveis
    public synchronized void pegarGarfos(int id) throws InterruptedException {

        // Adiciona o filósofo à fila de espera
        fila.add(id);
        System.out.println("Filósofo " + id + " entrou na fila para comer.");

        // Enquanto não for a vez dele ou os garfos não estiverem livres, espera
        while (fila.peek() != id || !garfos[id] || !garfos[(id + 1) % garfos.length]) {
            wait();
        }

        // Agora é a vez dele e os garfos estão livres
        garfos[id] = false;
        garfos[(id + 1) % garfos.length] = false;

        // Remove da fila, pois já vai comer
        fila.remove();
        System.out.println("Filósofo " + id + " pegou os dois garfos e começou a comer.");
    }

    //Método sincronizado para liberar os garfos após comer.
    
    public synchronized void soltarGarfos(int id) {

        // Libera os dois garfos
        garfos[id] = true;
        garfos[(id + 1) % garfos.length] = true;

        System.out.println("Filósofo " + id + " soltou os garfos.");

        // Acorda todos para reavaliar as condições
        notifyAll();
    }
}
