package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB() {
        raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (esVacio()) {
            raiz = new NodoABB(elemento);
        } else {
            exito = insertarAux(raiz, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB nodo, Comparable elemento) {
        boolean exito = true;

        if (elemento.compareTo(nodo.getElemento()) == 0) {
            exito = false;
        } else if (elemento.compareTo(nodo.getElemento()) < 0) {
            if (nodo.getIzquierdo() == null) {
                nodo.setIzquierdo(new NodoABB(elemento));
                exito = true;
            } else {
                exito = insertarAux(nodo.getIzquierdo(), elemento);
            }
        } else {
            if (nodo.getDerecho() == null) {
                nodo.setDerecho(new NodoABB(elemento));
                exito = true;
            } else {
                exito = insertarAux(nodo.getDerecho(), elemento);
            }
        }

        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        boolean exito = false;
        if (!esVacio()) {
            exito = eliminarAux(elemento, this.raiz);
        }
        return exito;
    }

    private boolean eliminarAux(Comparable elemento, NodoABB nodo) {
        boolean exito = false;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                // Si el elemento a eliminar es el nodo actual
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    // Si es hoja
                    nodo = null;
                } else if (nodo.getIzquierdo() == null) { // tiene hijo derecho
                    nodo = nodo.getDerecho();
                } else if (nodo.getDerecho() == null) { // tiene hijo izquierdo
                    nodo = nodo.getIzquierdo();
                } else { // Si tiene dos hijos
                    NodoABB aux = nodo.getDerecho();
                    while (aux.getIzquierdo() != null) {
                        aux = aux.getIzquierdo();
                    }
                    nodo.setElemento(aux.getElemento());
                    eliminarAux(aux.getElemento(), nodo.getDerecho()); // Elimina el elemento auxiliar
                }
                exito = true;

            } else if (elemento.compareTo(nodo.getElemento()) < 0) { // Bajar a la izquierda
                exito = eliminarAux(elemento, nodo.getIzquierdo());

            } else { // Bajar a la derecha
                exito = eliminarAux(elemento, nodo.getDerecho());
            }
        }
        return exito;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable elemento) {
        boolean pertenece = false;
        if (!esVacio()) {
            pertenece = perteneceAux(elemento, this.raiz);
        }
        return pertenece;
    }

    private boolean perteneceAux(Comparable elemento, NodoABB nodo) {
        boolean pertenece = false;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                pertenece = true;
            } else {
                if (elemento.compareTo(nodo.getElemento()) < 0) {
                    pertenece = perteneceAux(elemento, nodo.getIzquierdo());
                } else {
                    pertenece = perteneceAux(elemento, nodo.getDerecho());
                }
            }
        }
        return pertenece;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Lista listar() {
        Lista lista = new Lista();
        if (!esVacio()) {
            listarAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarAux(NodoABB nodo, Lista lista) {
        if (nodo.getIzquierdo() != null) {
            listarAux(nodo.getIzquierdo(), lista);
        }

        lista.insertar(nodo.getElemento(), lista.longitud() + 1);

        if (nodo.getDerecho() != null) {
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista lista = new Lista();
        if (!esVacio()) {
            listarRangoAux(this.raiz, lista, min, max);
        }
        return lista;
    }

    private void listarRangoAux(NodoABB nodo, Lista lista, Comparable min, Comparable max) {
        // Devuelve una lista con los elementos del árbol que están entre min y max
        int comparacionMin = nodo.getElemento().compareTo(min);
        int comparacionMax = nodo.getElemento().compareTo(max);

        if (nodo.getIzquierdo() != null && comparacionMin > 0) {
            listarRangoAux(nodo.getIzquierdo(), lista, min, max);
        }

        if (comparacionMin >= 0 && comparacionMax <= 0) {
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);
        }

        if (nodo.getDerecho() != null && comparacionMax < 0) {
            listarRangoAux(nodo.getDerecho(), lista, min, max);
        }
    }

    public void eliminarHojasEnRango(Comparable min, Comparable max) {
        eliminarHojasEnRangoAux(this.raiz, min, max);
    }

    private void eliminarHojasEnRangoAux(NodoABB nodo, Comparable min, Comparable max) {
        /*
         * Metodo que elimina del arbol elementos hojas que originalmente esten entre
         * min y max (incluidos) recorrer en preorden
         */
        System.out.println("elemento: " + nodo.getElemento());
        System.out.println("izquierdo: " + nodo.getIzquierdo());
        System.out.println("derecho: " + nodo.getDerecho());
        int comparacionMin = nodo.getElemento().compareTo(min);
        int comparacionMax = nodo.getElemento().compareTo(max);

        if (nodo.getIzquierdo() != null && comparacionMin > 0) {
            eliminarHojasEnRangoAux(nodo.getIzquierdo(), min, max);
        }

        if (comparacionMin >= 0 && comparacionMax <= 0) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                eliminar(nodo.getElemento());

            }
        }
        if (nodo.getDerecho() != null && comparacionMax < 0) {
            eliminarHojasEnRangoAux(nodo.getDerecho(), min, max);
        }

    }

    public Comparable minimoElem() {
        Comparable minimo = null;
        if (!esVacio()) {
            minimo = minimoElemAux(this.raiz).getElemento();
        }
        return minimo;
    }

    private NodoABB minimoElemAux(NodoABB nodo) {
        NodoABB minimo = nodo;
        if (nodo.getIzquierdo() != null) {
            minimo = minimoElemAux(nodo.getIzquierdo());
        }
        return minimo;
    }

    public boolean eliminarMinimo() {
        NodoABB minimo = minimoElemAux(raiz);
        return eliminarAux(minimo.getElemento(), minimo);
    }

    public Comparable maximoElem() {
        Comparable maximo = null;
        if (!esVacio()) {
            maximo = maximoElemAux(this.raiz).getElemento();
        }
        return maximo;
    }

    private NodoABB maximoElemAux(NodoABB nodo) {
        NodoABB maximo = nodo;
        if (nodo.getDerecho() != null) {
            maximo = maximoElemAux(nodo.getDerecho());
        }
        return maximo;
    }

    public boolean eliminarMaximo() {
        NodoABB maximo = maximoElemAux(raiz);
        return eliminarAux(maximo.getElemento(), maximo);
    }

    public String toString() {
        String cadena = "ESTRUCTURA VACIA";
        if (!esVacio()) {
            cadena = "";
            cadena = toStringAux(this.raiz, cadena);
        }
        return cadena;
    }

    private String toStringAux(NodoABB nodo, String texto) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                texto += "NODO: " + nodo.getElemento()
                        + " HI: " + nodo.getIzquierdo().getElemento() + " "
                        + " HD:  " + nodo.getDerecho().getElemento() + "\n";

            } else if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                texto += "NODO: " + nodo.getElemento()
                        + " HI: --"
                        + " HD: " + nodo.getDerecho().getElemento() + "\n";

            } else if (nodo.getDerecho() == null && nodo.getIzquierdo() != null) {
                texto += "NODO: " + nodo.getElemento()
                        + " HI: " + nodo.getIzquierdo().getElemento() + " "
                        + " HD: --" + "\n";

            } else {
                texto += "NODO: " + nodo.getElemento()
                        + " HI: --"
                        + " HD: --" + "\n";

            }
            texto = toStringAux(nodo.getIzquierdo(), texto);
            texto = toStringAux(nodo.getDerecho(), texto);
        }
        return texto;
    }

    public ArbolBB clonarParteInvertida(Comparable elemento) {
        ArbolBB arbol = new ArbolBB();
        if (!esVacio()) {
            NodoABB nodoAux = buscarNodo(raiz, elemento);
            if (nodoAux != null) {
                arbol.raiz = new NodoABB(nodoAux.getElemento());
                clonarParteInvertidaAux(nodoAux, arbol.raiz);
            }
        }
        return arbol;
    }

    private NodoABB buscarNodo(NodoABB nodo, Comparable elemento) {
        NodoABB buscado = null;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                buscado = nodo;
            } else if (elemento.compareTo(nodo.getElemento()) < 0) {
                perteneceAux(elemento, nodo.getIzquierdo());
            } else {
                perteneceAux(elemento, nodo.getDerecho());
            }
        }
        return buscado;
    }

    private void clonarParteInvertidaAux(NodoABB raiz1, NodoABB raiz2) {
        if (raiz1 != null) {
            if (raiz1.getIzquierdo() != null && raiz1.getDerecho() != null) {
                raiz2.setDerecho(raiz1.getIzquierdo());
                raiz2.setIzquierdo(raiz1.getDerecho());
            } else if (raiz1.getIzquierdo() != null) {
                raiz2.setDerecho(raiz1.getIzquierdo());
            } else if (raiz1.getDerecho() != null) {
                raiz2.setIzquierdo(raiz1.getDerecho());
            }
            clonarParteInvertidaAux(raiz1.getIzquierdo(), raiz2.getDerecho());
            clonarParteInvertidaAux(raiz1.getDerecho(), raiz2.getIzquierdo());
        }
    }
}