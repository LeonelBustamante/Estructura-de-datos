package conjuntistas;

public class NodoABB {

    private Comparable elemento;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Comparable elemento, NodoABB izquierdo, NodoABB derecho) {
        this.elemento = elemento;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public NodoABB(Comparable elemento) {
        this.elemento = elemento;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Comparable getElemento() {
        return elemento;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }

}
