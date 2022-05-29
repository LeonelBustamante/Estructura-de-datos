package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public int nivel(Object buscado) {
        return nivelAux(this.raiz, buscado, -1);
    }

    private int nivelAux(NodoArbol nodo, Object buscado, int nivel) {
        int encontrado = -1;

        if (nodo != null) {
            // En caso de nodo null se retorna -1
            if (nodo.getElem().equals(buscado)) {
                // Una vez encontrado asignamos a encontrado el nivel +1
                encontrado = nivel + 1;

            } else if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                // Si llegamos a una hoja encontrado se asigna con -1 y se retorna
                encontrado = -1;

            } else if (nodo.getIzquierdo() != null) {
                encontrado = nivelAux(nodo.getIzquierdo(), buscado, nivel + 1);
            }

            if (nodo.getDerecho() != null && encontrado == -1) {
                encontrado = nivelAux(nodo.getDerecho(), buscado, nivel + 1);
            }

        }

        return encontrado;
    }

    public int altura() {
        int altura;
        altura = alturaAux(this.raiz);
        return altura;
    }

    public ArbolBin clone() {
        ArbolBin copia = new ArbolBin();

        if (!this.esVacia()) {
            copia.raiz = cloneAux(this.raiz);
        }

        return copia;
    }

    public boolean esVacia() {
        // Retorna true en caso de estructura vacia, false caso contrario
        return this.raiz == null;
    }

    public boolean insertar(Object nuevoElem, Object padre, char lado) {
        boolean res = true;
        if (esVacia()) {
            // Se inserta como raiz en caso de estar vacio el arbol
            this.raiz = new NodoArbol(nuevoElem, null, null);

        } else {
            // Se busca el nodo padre mediante <obtenerNodo>
            NodoArbol nodoPadre = obtenerNodo(this.raiz, padre);
            char ladoMayuscula = Character.toUpperCase(lado);
            if (nodoPadre != null) {
                // Si el padre existe entonces inserto en lado correspondiente
                if (ladoMayuscula == 'I' && nodoPadre.getIzquierdo() != null) {
                    nodoPadre.setIzquierdo(new NodoArbol(nuevoElem, null, null));

                } else if (ladoMayuscula == 'D' && nodoPadre.getDerecho() != null) {
                    nodoPadre.setDerecho(new NodoArbol(nuevoElem, null, null));

                } else {
                    // Si ya tiene hijo en <lado> es false
                    res = false;
                } // {fin eleccion de lado}

            } else {
                // Si el padre no existe entonces false
                res = false;
            } // {fin nodopadre != null}

        } // {Fin esVacia()}
        return res;
    }

    public Lista listarInorden() {
        Lista lista = new Lista();
        listarInordenAux(this.raiz, lista);
        return lista;
    }

    public Lista listarPorNiveles() {
        // Metodo que realiza un recorrido por nivel y se retorna en una lista el
        // resultado de esta
        Lista lista = new Lista();
        Cola cola = new Cola();
        cola.poner(this.raiz);
        if (!esVacia()) {

            while (!cola.esVacia()) {
                NodoArbol nodo = (NodoArbol) cola.obtenerFrente();
                cola.sacar();
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
                if (nodo.getIzquierdo() != null) {
                    cola.poner(nodo.getIzquierdo());
                }
                if (nodo.getDerecho() != null) {
                    cola.poner(nodo.getDerecho());
                }
            } // {Fin mientras}
        } // fin{if}
        return lista;
    }

    public Lista listarPosorden() {
        Lista lista = new Lista();
        listarPosordenAux(this.raiz, lista);
        return lista;
    }

    public Lista listarPreorden() {
        Lista lista = new Lista();
        listarPreordenAux(this.raiz, lista);
        return lista;
    }

    public Object padre(Object elem) {
        Object padre = null;
        if (!esVacia() && !this.raiz.getElem().equals(elem)) {
            padre = padreAux(this.raiz, elem);
        }
        return padre;
    }

    public String toString() {
        String cadena = "ESTRUCTURA VACIA";
        if (!esVacia()) {
            cadena = toStringAux(this.raiz, cadena);
        }
        return cadena;
    }

    public void vaciar() {
        // Se vacia la estructura mediante garbage collector
        this.raiz = null;
    }

    private int alturaAux(NodoArbol nodo) {
        int alturaIzquierda = -1, alturaDerecha = -1;

        if (nodo != null) {
            // Se baja por cada lado y a la vuelta se aumenta en 1

            alturaIzquierda = alturaAux(nodo.getIzquierdo()) + 1;
            alturaDerecha = alturaAux(nodo.getDerecho()) + 1;
        }
        return Math.max(alturaIzquierda, alturaDerecha);
    }

    private NodoArbol cloneAux(NodoArbol nodo) {
        // Metodo privado que clona el arbol mediante un recorrido en preorden

        // Se crea el nodo en el que nos encontramos
        NodoArbol nuevoNodo = new NodoArbol(nodo.getElem(), null, null);

        if (nodo.getIzquierdo() != null) {
            nuevoNodo.setIzquierdo(cloneAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nuevoNodo.setDerecho(cloneAux(nodo.getDerecho()));
        }
        return nuevoNodo;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lista) {
        // Metodo recursivo que visita HI, RAIZ, HD
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lista);
        }
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lista) {
        // Metodo recursivo que visita HI, HD, RAIZ
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), lista);
            listarPosordenAux(nodo.getDerecho(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lista) {
        // Metodo recursivo que visita RAIZ, HI, HD
        if (nodo != null) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarPreordenAux(nodo.getIzquierdo(), lista);
            listarPreordenAux(nodo.getDerecho(), lista);
        }
    }

    private NodoArbol obtenerNodo(NodoArbol nodo, Object buscado) {
        // Se busca el elemento <buscado> dentro del arbol en caso de no encontrarse se
        // retorna null
        NodoArbol res = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                // Si se encuentra el elemento buscado se retorna
                res = nodo;
            } else {
                // Si no se lo encuentra sigue por HI
                obtenerNodo(nodo.getIzquierdo(), buscado);

                if (res == null) {
                    // Si aun no se encuentra se busca por rama derecha
                    obtenerNodo(nodo.getDerecho(), buscado);
                } // {fin res == null}
            } // {fin equals}
        } // {fin nodo != null}
        return res;
    }

    private Object padreAux(NodoArbol nodo, Object elem) {
        // Metodo que busca el padre consultando a cada nodo si es <elem> en caso de
        // serlo se retorna
        Object res = null;
        if (nodo != null) {
            if (nodo.getIzquierdo() != null && nodo.getIzquierdo().equals(elem)) {
                // Si el elemento es el hijo izquierdo se asigna como resultado
                res = nodo.getElem();

            } else if (nodo.getIzquierdo() != null && nodo.getIzquierdo().equals(elem)) {
                // Si el elemento es el hijo derecho se asigna como resultado
                res = nodo.getElem();

            } else {
                // Si no es se seguira buscando en el resto del arbol en preorden
                res = padreAux(nodo.getIzquierdo(), elem);
                if (res == null) {
                    res = padreAux(nodo.getDerecho(), elem);
                } // {fin res == null}

            } // {fin busqueda del hijo}
        } // {fin nodo!=null}
        return res;
    }

    private String toStringAux(NodoArbol nodo, String texto) {
        if (nodo != null) {
            /*
             * Casos posibles:
             * Ambos hijos
             * Sin hijos
             * Hijo izq
             * Hijo derecho
             */
            if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                texto += "NODO: " + nodo.getElem()
                        + " HI: " + nodo.getIzquierdo().getElem() + " "
                        + " HD:  " + nodo.getDerecho().getElem() + "\n";

            } else if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                texto += "NODO: " + nodo.getElem()
                        + " HI: --"
                        + " HD: " + nodo.getDerecho().getElem() + "\n";

            } else if (nodo.getDerecho() == null && nodo.getIzquierdo() != null) {
                texto += "NODO: " + nodo.getElem()
                        + " HI: " + nodo.getIzquierdo().getElem() + " "
                        + " HD: --" + "\n";

            } else {
                texto += "NODO: " + nodo.getElem()
                        + " HI: --"
                        + " HD: --" + "\n";

            }
            // Si existieran nodos visitariamos estos hasta llegar al null
            texto = toStringAux(nodo.getIzquierdo(), texto);
            texto = toStringAux(nodo.getDerecho(), texto);
        }
        return texto;
    }

    public boolean verificarPatron(Lista patron) {
        /**
         * Implementar la operación boolean verificarPatron(Lista patron), que
         * recibe por parámetro una lista patron y determine si coincide
         * exactamente con al menos un camino del árbol que comience en la raíz
         * y termine en una hoja. El método debe ser eficiente, es decir,
         * recorrer el árbol lo estrictamente necesario.
         */
        boolean res = false;
        if (!esVacia()) {
            Lista lis = patron.clone();
            int pos = 1;
            verificarPatronAux(this.raiz, lis, pos);
        }
        return res;
    }

    private boolean verificarPatronAux(NodoArbol nodo, Lista patron, int pos) {
        boolean exito = false;
        if (pos > patron.longitud()) {
            exito = true;
        } else {
            if (nodo != null && nodo.getElem().equals(patron.recuperar(pos))) {
                exito = verificarPatronAux(nodo.getIzquierdo(), patron, pos + 1);
                if (!exito) {
                    exito = verificarPatronAux(nodo.getDerecho(), patron, pos + 1);
                }
            }

        }
        return exito;
    }

    public Lista frontera() {
        /**
         * Implementar la operación frontera() que devuelve una lista con la
         * secuencia formada por los elementos almacenados en las hojas del
         * árbol binario, tomadas de izquierda a derecha
         */
        Lista lis = new Lista();
        if (!esVacia()) {
            fronteraAux(this.raiz, lis);
        }
        return lis;
    }

    private void fronteraAux(NodoArbol nodo, Lista lis) {
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        } else {
            fronteraAux(nodo.getIzquierdo(), lis);
            fronteraAux(nodo.getDerecho(), lis);
        }
    }

    public ArbolBin clonarInvertido() {
        ArbolBin clon = new ArbolBin();
        if (this.esVacia()) {
            clon.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            clonarInvertidoAux(this.raiz, clon.raiz);
        }
        return clon;
    }

    private void clonarInvertidoAux(NodoArbol orig, NodoArbol clon) {
        if (orig != null) {
            if (orig.getIzquierdo() != null) {
                clon.setDerecho(new NodoArbol(orig.getIzquierdo().getElem(), null, null));
            }
            if (orig.getDerecho() != null) {
                clon.setIzquierdo(new NodoArbol(orig.getDerecho().getElem(), null, null));
            }
            clonarInvertidoAux(orig.getIzquierdo(), clon.getDerecho());
            clonarInvertidoAux(orig.getDerecho(), clon.getIzquierdo());
        }
    }

    /*
     * ArbolBin
     * -raiz: NodoArbol
     * +insertar(Object padre, Object elemento, char lado): boolean
     * +listarPreorden(): Lista
     * +listarPosorden(): Lista
     * +listarInorden(): Lista
     * +listarPorNiveles(): Lista
     * +altura(Object x): int
     * +nivel(Object x): int
     * +padre(Object x): Object
     * +esVacia(): boolean
     * +vaciar(): void
     * +clone(): ArbolBin
     * +toString(): String
     */
}
