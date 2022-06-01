package conjuntistas;

public class NodoAVL {

    private Comparable elemento;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;

    public NodoAVL(Comparable elemento, NodoAVL izquierdo, NodoAVL derecho, int altura) {
        this.elemento = elemento;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 0;
    }

    public Comparable getElemento() {
        return elemento;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

    public void recalcularAltura() {
        int alturaIzquierdo = 0;
        int alturaDerecho = 0;
        if (izquierdo != null) {
            alturaIzquierdo = izquierdo.getAltura();
        }
        if (derecho != null) {
            alturaDerecho = derecho.getAltura();
        }
        altura = Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }
}
