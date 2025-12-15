package tarefa1;

// Cada garfo é um recurso compartilhado entre dois filósofos. 
// O próprio objeto Garfo será usado como monitor (synchronized).
public class Garfo {

    private final int id;

    public Garfo(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
