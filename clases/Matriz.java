import java.util.List;
public class Matriz{
    private List<Vector> filas;
    private int numFilas;
    private int numColumnas;

    public Matriz(List<Vector> filas, int numFilas, int numColumnas) {
        this.filas = filas;
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
    }

    public List<Vector> getFilas() {
        return filas;
    }

    public int getNumFilas() {
        return numFilas;
    }

    public int getNumColumnas() {
        return numColumnas;
    }
    public void addFila(Vector fila) {
        filas.add(fila);
        numFilas++;
    }
    public void removeFila(int index) {
        if (index >= 0 && index < numFilas) {
            filas.remove(index);
            numFilas--;
        } else {
            throw new IndexOutOfBoundsException("Índice de fila fuera de rango");
        }
    }
    public Vector getFila(int index) {
        if (index >= 0 && index < numFilas) {
            return filas.get(index);
        } else {
            throw new IndexOutOfBoundsException("Índice de fila fuera de rango");
        }
    }
}