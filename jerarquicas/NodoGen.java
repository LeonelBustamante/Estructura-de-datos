package jerarquicas;

public class NodoGen {

    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;

    NodoGen(Object elem, NodoGen hei, NodoGen hd) {
        this.elem = elem;
        this.hijoIzquierdo = hei;
        this.hermanoDerecho = hd;
    }

    public Object getElem() {
        return this.elem;
    }

    public NodoGen getHermanoDerecho() {
        return this.hermanoDerecho;
    }

    public NodoGen getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setHermanoDerecho(NodoGen nodo) {
        this.hermanoDerecho = nodo;
    }

    public void setHijoIzquierdo(NodoGen nodo) {
        this.hijoIzquierdo = nodo;
    }
}
