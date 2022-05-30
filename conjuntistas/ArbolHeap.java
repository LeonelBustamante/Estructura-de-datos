package conjuntistas;

public class ArbolHeap {

    private final int TAMANIO = 20;
    private Comparable[] heap;
    private int ultimo;

    public ArbolHeap() {
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;// la pos 0 no es utilizada
    }

    public boolean insertar(Comparable elem) {
        boolean exito = false;
        if (this.TAMANIO > this.ultimo + 1) {
            this.heap[this.ultimo + 1] = elem;
            this.ultimo++;
            hacerSubir(ultimo);
            exito = true;
        }
        return exito;
    }

    public void hacerSubir(int posHijo) {
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
        boolean exito;
        if (this.ultimo == 0) {
            exito = false;
        } else {
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    public void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                if (posH < this.ultimo) {
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        posH++;
                    }
                }
                if (this.heap[posH].compareTo(temp) < 0) {
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }

    public Comparable recuperarCima() {
        Comparable exito;
        if (!this.esVacio()) {
            exito = this.heap[1];
        } else {
            exito = null;
        }
        return exito;
    }

    public boolean esVacio() {
        return (this.heap[1] == null);
    }

    public void vaciar() {
        for (int i = 1; i <= this.ultimo; i++) {
            this.heap[i] = null;
        }
        this.ultimo = 0;
    }

    public ArbolHeap clon() {
        ArbolHeap copia = new ArbolHeap();
        copia.ultimo = this.ultimo;
        copia.heap = this.heap.clone();
        return copia;
    }

    public String toString() {
        int i;
        String texto, texto1 = "";
        for (i = 1; i <= this.ultimo; i++) {
            if (texto1 == "") {
                texto1 = texto1 + this.heap[i].toString() + " --> HI: " + this.heap[2] + " HD: " + this.heap[3] + "\n";
            } else {
                texto1 = texto1 + this.heap[i].toString() + " --> HI: " + this.heap[2 * i] + " HD: "
                        + this.heap[(2 * i) + 1] + "\n";
            }

        }
        return texto = "ULTIMO: " + this.ultimo + "\nARBOL: \n" + texto1;
    }

}