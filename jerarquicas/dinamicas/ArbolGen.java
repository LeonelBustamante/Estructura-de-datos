package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;

public class ArbolGen {
    /*
     * Arbol con n cantidad de hijos, usualmente usado para sistema de archivos
     * Arbol generico no hace distincion de sus hijos
     */

    private NodoGen raiz;

    public ArbolGen() {
        // Crea un árbol genérico vacío
        this.raiz = null;
    }

    public Lista listarPreorden() {
        Lista lista = new Lista();
        listarPreordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPreordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            if (nodo.getHijoIzquierdo() != null) {
                listarPreordenAux(nodo.getHijoIzquierdo(), lista);
            }

            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermandoDerecho();
                while (hijo != null) {
                    listarPreordenAux(hijo, lista);
                    hijo = hijo.getHermandoDerecho();
                }

            }
        }
    }

    public Lista listarPosorden() {
        Lista lista = new Lista();
        if (!esVacio()) {
            listarPosordenAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarPosordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null) {
                listarPreordenAux(nodo.getHijoIzquierdo(), lista);
            }

            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermandoDerecho();
                while (hijo != null) {
                    listarPreordenAux(hijo, lista);
                    hijo = hijo.getHermandoDerecho();
                }

                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
        }
    }

    public Lista listarInorden() {
        Lista lista = new Lista();
        if (!esVacio()) {
            listarInordenAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarInordenAux(NodoGen nodo, Lista lista) {
        lista.insertar(nodo.getElem(), lista.longitud() + 1);
    }

    public Lista listarPorNiveles() {
        return null;
    }

    public String toString() {
        return null;
    }

    public void vaciar() {
    }

    public ArbolGen clone() {
        return null;
    }

    public Lista ancestros(Object elem) {
        return null;
    }

    public int altura(Object elem) {
        return -1;
    }

    public int nivel(Object elem) {
        return -1;
    }

    public Object padre(Object elem) {
        return null;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public boolean pertenece(Object elem) {
        return false;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        /*
         * Dado un elemento elemNuevo y un elemento elemPadre, agrega elemNuevo como
         * hijo de la primer aparición de elemPadre. Para que la operación termine con
         * éxito debe existir un nodo en el árbol con elemento = elemPadre. No se
         * establece ninguna preferencia respecto a la posición del hijo respecto a sus
         * posibles hermanos. Esta operación devuelve verdadero cuando se pudo agregar
         * elemNuevo a la estructura y falso en caso contrario.
         */
        boolean res = false;
        if (this.esVacio()) {
            this.raiz = new NodoGen(elemNuevo, null, null);
            res = true;
        } else {
            NodoGen padre = buscarNodo(this.raiz, elemPadre);
            if (padre != null) {
                if (padre.getHijoIzquierdo() != null) {
                    padre.setHijoIzquierdo(new NodoGen(elemNuevo, null, padre.getHijoIzquierdo()));
                } else {
                    padre.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                }
                res = true;
            }
        }
        return res;
    }

    private NodoGen buscarNodo(NodoGen nodo, Object buscado) {
        NodoGen res = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                res = nodo;
            } else {
                res = buscarNodo(nodo.getHermandoDerecho(), buscado);
                if (res == null) {
                    res = buscarNodo(nodo.getHijoIzquierdo(), buscado);
                }
            }
        }
        return res;
    }

}
