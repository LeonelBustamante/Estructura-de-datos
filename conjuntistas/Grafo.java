package conjuntistas;

public class Grafo {

    private NodoVert inicio;

    public boolean esVacio() {
        return inicio == null;
    }

    public boolean insertarVertice(Object elemento) {
        NodoVert nuevo = new NodoVert(elemento);
        if (esVacio()) {
            // Si el grafo está vacío, el nuevo nodo es el inicio
            inicio = nuevo;
        } else {
            // Si el grafo no está vacío, se inserta al final de la lista
            NodoVert aux = inicio;
            while (aux.getSigVertice() != null) {
                aux = aux.getSigVertice();
            }
            aux.setSigVertice(nuevo);
        }
        return true;
    }

    public boolean eliminarVertice(Object elemento) {
        boolean res = false;
        if (!esVacio()) {
            NodoVert aux = inicio;
            NodoVert anterior = null;
            while (aux.getSigVertice() != null) {
                if (aux.getElem().equals(elemento)) {
                    if (anterior == null) {
                        inicio = aux.getSigVertice();
                    } else {
                        anterior.setSigVertice(aux.getSigVertice());
                    }
                }
                anterior = aux;
                aux = aux.getSigVertice();
            }
            res = true;
        }
        return res;
    }

    public boolean existeVertice(Object elemento) {
        boolean res = false;
        if (!esVacio()) {
            NodoVert aux = inicio;
            while (aux.getSigVertice() != null && res == false) {
                if (aux.getElem().equals(elemento)) {
                    res = true;
                }
                aux = aux.getSigVertice();
            }
        }
        return res;
    }

    public boolean insertarArco(Object vert1, Object vert2) {
        boolean res = false;
        if (!esVacio()) {
            NodoVert aux = inicio;
            while (aux.getSigVertice() != null && res == false) {
                
            }
        }
        return res;

    }
}
