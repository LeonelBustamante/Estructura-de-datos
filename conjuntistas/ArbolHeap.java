package conjuntistas;

public class ArbolHeap {
    private final int TAMANIO = 20;
    private Comparable[] heap;
    private int ultimo;

    public ArbolHeap() {
        // Crea un árbol vacío.
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;// la pos 0 no es utilizada
    }

    public boolean insertar(Comparable elem) {
        // Inserta un elemento en el árbol.
        boolean exito = false;
        if (this.TAMANIO > this.ultimo + 1) {
            this.heap[this.ultimo + 1] = elem;
            this.ultimo++;
            hacerSubir(ultimo);
            exito = true;
        }
        return exito;
    }

    private void hacerSubir(int posHijo) {
        int posPadre;
        Comparable temp = this.heap[posHijo];
        boolean salir = false;
        while (!salir) {
            posPadre = posHijo / 2;
            if (this.heap[1].compareTo(this.heap[posHijo]) == 0) {
                salir = true;
            } else if (this.heap[posHijo].compareTo(this.heap[posPadre]) > 0) {
                salir = true;
            } else {
                temp = this.heap[posPadre];
                this.heap[posPadre] = this.heap[posHijo];
                this.heap[posHijo] = temp;
                posHijo = posHijo / 2;
            }
        }
    }

    public boolean eliminarCima() {
        // Elimina el elemento de la cima del árbol.
        boolean exito = false;
        if (!esVacio()) {
            this.heap[1] = this.heap[ultimo]; // el ultimo elemento se pone en la cima
            this.ultimo--;
            hacerBajar(1); // se hace bajar el elemento de la cima
            exito = true;
        }
        return exito; // devuelve true si se ha podido eliminar la cima
    }

    private void hacerBajar(int posPadre) {
        // Hace bajar el elemento de la posición posPadre.
        int posHijo;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posHijo = posPadre * 2;
            if (posHijo <= this.ultimo) {
                if (posHijo < this.ultimo) {
                    if (this.heap[posHijo + 1].compareTo(this.heap[posHijo]) < 0) {
                        // el hijo izquierdo es menor que el derecho
                        posHijo++;
                    }
                }
                if (this.heap[posHijo].compareTo(temp) < 0) {
                    // el hijo es menor que el padre
                    this.heap[posPadre] = this.heap[posHijo];
                    this.heap[posHijo] = temp;
                    posPadre = posHijo;
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }

    public Comparable recuperarCima() {
        // Recupera el elemento de la cima del árbol.
        Comparable exito = null;
        if (!esVacio()) {
            exito = this.heap[1];
        }
        return exito;
    }

    public boolean esVacio() {
        // Indica si el árbol está vacío.
        return this.ultimo == 0;
    }

    public void vaciar() {
        // Vacía el árbol.
        this.ultimo = 0;
    }

    public ArbolHeap clon() {
        // Devuelve un clon del árbol.
        ArbolHeap clon = new ArbolHeap();
        clon.heap = this.heap.clone();
        clon.ultimo = this.ultimo;
        return clon;
    }

    public String toString() {
        String cadena = "ESTRUCTURA VACIA";
        if (!esVacio()) {
            cadena = "";
            for (int i = 1; i <= this.ultimo; i++) {
                cadena += this.heap[i] + "\t--> HI: " + this.heap[2 * i] + "\tHD: " + this.heap[(2 * i) + 1] + "\n";
            }

        }
        return cadena;
    }

}