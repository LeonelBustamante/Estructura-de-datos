package conjuntistas;

public class ArbolHeap {

    private int TAMANIO = 10;
    private Comparable[] arreglo;
    private int ultimo;

    public ArbolHeap() {
        arreglo = new Comparable[TAMANIO];
        ultimo = 0;
    }

    public boolean eliminarCima() {
        boolean exito = false;
        if (!esVacio()) {
            this.arreglo[1] = this.arreglo[ultimo];
            this.ultimo -= 1;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private boolean esVacio() {
        return this.ultimo == 0;
    }

    private void hacerBajar(int posPadre) {
        /*
         * Si el hijo izquierdo es menor que el padre, se intercambian los
         * elementos.
         */
        int posHijo;
        Comparable temp = this.arreglo[posPadre];
        boolean salir = false;

        while (!salir) {
            posHijo = posPadre * 2;
            if (posHijo <= this.ultimo) {
                if (posHijo < this.ultimo) {
                    if (this.arreglo[posHijo + 1].compareTo(this.arreglo[posHijo]) < 0) {
                        posHijo += 1;
                    }
                }

                if (this.arreglo[posHijo].compareTo(temp) < 0) {
                    this.arreglo[posPadre] = this.arreglo[posHijo];
                    this.arreglo[posHijo] = temp;
                    posPadre = posHijo;
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }
}
