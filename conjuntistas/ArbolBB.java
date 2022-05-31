package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB() {
        // Constructor
        raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        // Inserta un elemento en el árbol
        // Devuelve true si se ha podido insertar
        // Devuelve false si ya existe un elemento igual
        boolean exito = true;
        if (esVacio()) {
            this.raiz = new NodoABB(elemento, null, null);
        } else {
            exito = insertarAux(elemento, raiz);
        }
        return exito;
    }

    private boolean insertarAux(Comparable elemento, NodoABB nodo) {
        boolean exito = true;
        if (elemento.compareTo(nodo.getElemento()) == 0) {
            // ya existe
            exito = false;
        } else {
            if (elemento.compareTo(nodo.getElemento()) < 0) {
                // insertar a la izquierda
                if (nodo.getIzquierdo() == null) {
                    // insertar
                    nodo.setIzquierdo(new NodoABB(elemento, null, null));
                } else {
                    // recursivo
                    exito = insertarAux(elemento, nodo.getIzquierdo());
                }
            } else {
                // insertar a la derecha
                if (nodo.getDerecho() == null) {
                    // insertar
                    nodo.setDerecho(new NodoABB(elemento, null, null));
                } else {
                    // recursivo
                    exito = insertarAux(elemento, nodo.getDerecho());
                }
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        // Elimina un elemento del árbol
        boolean exito = false;
        if (!esVacio()) {
            exito = eliminarAux(elemento, this.raiz);
        }
        return exito;
    }

    private boolean eliminarAux(Comparable elemento, NodoABB nodo) {
        // Elimina un elemento del árbol
        boolean exito = false;
        if (elemento.compareTo(nodo.getElemento()) == 0) {
            // encontrado
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                // hoja
                nodo = null;
            } else if (nodo.getIzquierdo() == null) {
                // solo tiene un hijo a la derecha
                nodo = nodo.getDerecho();
            } else if (nodo.getDerecho() == null) {
                // solo tiene un hijo a la izquierda
                nodo = nodo.getIzquierdo();
            } else {
                // tiene dos hijos (elimina el más chico de la derecha)
                NodoABB aux = nodo.getDerecho();
                while (aux.getIzquierdo() != null) {
                    // busca el más chico de la derecha
                    aux = aux.getIzquierdo();
                }
                nodo.setElemento(aux.getElemento()); // sustituye el nodo a aeliminar
                eliminar(aux.getElemento()); // elimina el más chico de la derecha
            }
        }
        return exito;
    }

    public void vaciar() {
        // Vacía el árbol
        this.raiz = null;
    }

    public boolean pertenece(Comparable elemento) {
        // Devuelve true si el elemento está en el árbol
        boolean pertenece = false;
        if (this.raiz != null) {
            pertenece = perteneceAux(elemento, this.raiz);
        }
        return pertenece;
    }

    private boolean perteneceAux(Comparable elemento, NodoABB nodo) {
        // Devuelve true si el elemento está en el árbol
        boolean pertenece = false;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                // encontrado
                pertenece = true;
            } else {
                if (elemento.compareTo(nodo.getElemento()) < 0) {
                    // recursivo
                    pertenece = perteneceAux(elemento, nodo.getIzquierdo());
                } else {
                    // recursivo
                    pertenece = perteneceAux(elemento, nodo.getDerecho());
                }
            }
        }
        return pertenece;
    }

    public boolean esVacio() {
        // Devuelve true si el árbol está vacío
        return this.raiz == null;
    }

    public Lista listar() {
        // Devuelve una lista con los elementos del árbol
        Lista lista = new Lista();
        if (!esVacio()) {
            listarAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarAux(NodoABB nodo, Lista lista) {
        // Devuelve una lista con los elementos del árbol
        if (nodo.getIzquierdo() != null) {
            listarAux(nodo.getIzquierdo(), lista);
        }

        lista.insertar(nodo.getElemento(), lista.longitud() + 1);

        if (nodo.getDerecho() != null) {
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        // Devuelve una lista con los elementos del árbol que están entre min y max
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

    public Comparable minimoElem() {
        // Devuelve el elemento más chico del árbol
        Comparable minimo = null;
        if (!esVacio()) {
            minimo = minimoElemAux(this.raiz);
        }
        return minimo;
    }

    private Comparable minimoElemAux(NodoABB nodo) {
        // Devuelve el elemento más chico del árbol
        Comparable minimo = null;
        if (nodo.getIzquierdo() != null) {
            minimo = minimoElemAux(nodo.getIzquierdo());
        } else {
            minimo = nodo.getElemento();
        }
        return minimo;
    }

    public Comparable maximoElem() {
        // Devuelve el elemento más grande del árbol
        Comparable maximo = null;
        if (!esVacio()) {
            maximo = maximoElemAux(this.raiz);
        }
        return maximo;
    }

    private Comparable maximoElemAux(NodoABB nodo) {
        // Devuelve el elemento más grande del árbol
        Comparable maximo = null;
        if (nodo.getDerecho() != null) {
            maximo = maximoElemAux(nodo.getDerecho());
        } else {
            maximo = nodo.getElemento();
        }
        return maximo;
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

}