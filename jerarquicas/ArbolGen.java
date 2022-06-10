package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class ArbolGen {

    private NodoGen raiz;

    // CONSTRUCTOR
    public ArbolGen() {
        this.raiz = null;
    }

    // MODIFICADORES
    public boolean insertar(Object elemNuevo, Object elemPadre) {
        // METODO QUE BUSCA UN ELEMENTO PADRE Y AGREGA UN elemNuevo COMO HIJO
        boolean exito = false;
        NodoGen encontrado, nuevo;
        if (this.esVacio()) {
            this.raiz = new NodoGen(elemNuevo, null, null); // SI EL ARBOL ESTA VACIO LO INGRESA EN LA RAIZ
            exito = true;
        } else {
            encontrado = buscarNodo(this.raiz, elemPadre); // SI EL ARBOL TIENE ALGUN ELEMENTO, BUSCA EL elemPadre
            if (encontrado != null) { // SI ENCUENTRA EL elemPadre
                if (encontrado.getHijoIzquierdo() != null) { // Y TIENE HIJOS
                    nuevo = new NodoGen(elemNuevo, null, encontrado.getHijoIzquierdo());
                    encontrado.setHijoIzquierdo(nuevo); // INSERTA EL elemNueo como primer hijo del elemPadre
                    exito = true;
                } else {
                    encontrado.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                    exito = true;
                }
            }
        }
        return exito;
    }

    private NodoGen buscarNodo(NodoGen nodo, Object buscado) {
        // METODO QUE BUSCA UN DETERMINADO ELEMENTO EN EL ARBOL GENERICO
        NodoGen encontrado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                // SI EL BUSCADO ES nodo, LO DEVUELVE
                encontrado = nodo;
            } else {
                // NO ES EL BUSCADO: BUSCA PRIMERO EL HERMANO DERECHO
                encontrado = buscarNodo(nodo.getHermanoDerecho(), buscado);
                // SI NO LO ENCUENTRA EN EL HERMANO DERECHO, BUSCA EN HIJO IZQUIERDO
                if (encontrado == null) {
                    encontrado = buscarNodo(nodo.getHijoIzquierdo(), buscado);
                }

            }

        }
        return encontrado;
    }

    public void vaciar() {
        // METODO QUE VACIA EL ARBOL GENERICO HACIENDO APUNTAR A NULL A LA RAIZ
        this.raiz = null;
    }

    // VISUALIZADORES
    public boolean pertenece(Object elem) {
        // METODO QUE VERIFICA SI UN ELEMENTO PERTENECE AL ARBOL
        NodoGen encontrado = buscarNodo(this.raiz, elem); // PARA ESTE METODO REUTILIZO EL METODO BUSCAR NODO
        return (encontrado != null);
    }

    public boolean esVacio() {
        // METODO QUE VERIFICA SI EL ARBOL ESTA VACIO O NO
        // SI RETORNA TRUE ESTA VACIO
        return (this.raiz == null);
    }

    public Object padre(Object elem) {
        /*
         * METODO QUE RECIBE ELEMENTO BUSCADO POR PARAMETRO Y LLAMA A UN METODO
         * PRIVADO ""padreAux PARA ENCONTRAR EL PADRE EN ARBOL GENERICO
         */
        NodoGen nodoPadre;
        Object elementoPadre = null;

        if (!this.esVacio() && !this.raiz.getElem().equals(elem)) {
            // SI EL ARBOL NO ESTA VACIO Y LA RAIZ NO ES EL ELEMENTO BUSCADO

            nodoPadre = padreAux(this.raiz, elem); // LLAMAMOS AL METODO PRIVADO
            if (nodoPadre != null) {
                elementoPadre = nodoPadre.getElem();
            }
        }

        return elementoPadre;// EN CASO DE QUE LA RAIZ SEA EL ELEMENTO BUSCADO ESTE NO TIENE PADRE
    }

    private NodoGen padreAux(NodoGen nodo, Object buscado) {
        /*
         * METODO PRIVADO QUE RECIBE POR PARAMETRO UN NODO QUE SERA LA RAIZ DE
         * LOS SUBARBOLES VERIFICADOS EN CADA PASO RECURSIVO Y UN ELEMENTO EL CUAL
         * ESTAMOS BUSCANDO
         */

        NodoGen resultado = null;

        if (nodo != null && nodo.getHijoIzquierdo() != null) {// EN CASO DE NODO NULL RETORNA NULL
            if (nodo.getHijoIzquierdo().getElem().equals(buscado)) {

                resultado = nodo;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                if (resultado == null) {
                    while (hijo != null && resultado == null) {
                        if (hijo.getElem().equals(buscado)) {
                            resultado = nodo;
                        }
                        hijo = hijo.getHermanoDerecho();
                    }
                }

                // LLAMADOS RECURSIVOS CON LOS OTROS HIJOS DE nodo
                if (resultado == null) {

                    hijo = nodo.getHijoIzquierdo();

                    while (resultado == null && hijo != null) {

                        resultado = padreAux(hijo, buscado);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }

        return resultado;
    }

    public int altura() {
        // METODO QUE CALCULA LA ALTURA DEL ARBOL
        return alturaAux(this.raiz);
    }

    private int alturaAux(NodoGen nodo) {
        int aux = -1;
        int res = -1;
        if (nodo != null) {
            NodoGen nuevo = nodo.getHijoIzquierdo();
            while (nuevo != null) {
                aux = alturaAux(nuevo);
                if (aux > res) {
                    res = aux;
                }
                nuevo = nuevo.getHermanoDerecho();
            }
            res++;
        }
        return res;
    }

    public int nivel(Object elem) {
        // METODO QUE CALCULA EL NIVEL DE UN DETERMINADO ELEMENTO MEDIANTE UN METODO
        // PRIVADO
        int nivel;
        nivel = buscarNivel(this.raiz, elem, -1);
        return nivel;
    }

    private int buscarNivel(NodoGen nodo, Object buscado, int a) {
        int nivel = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                nivel = a + 1;
            } else {

                // LLAMADOS RECURSIVOS CON LOS OTROS HIJOS DE nodo
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null && nivel == -1) {
                        nivel = buscarNivel(hijo, buscado, a + 1);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return nivel;
    }

    public Lista ancestros(Object elem) {
        // METODO QUE DEVUELVE UNA LISTA CON TODOS LOS ELEMENTOS DE LOS NODOS ANCESTROS
        Lista lis = new Lista();
        ancestrosAux(this.raiz, lis, elem);
        return lis;
    }

    private boolean ancestrosAux(NodoGen nodo, Lista lis, Object buscado) {

        boolean exito = false;
        if (nodo != null) {

            if (nodo.getElem().equals(buscado)) { // SI ENCUENTRA AL ELEMENTO CORTA

                exito = true;
            } else {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);

                // LLAMADOS RECURSIVOS CON LOS OTROS HIJOS DE nodo
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null && !exito) {
                        exito = ancestrosAux(hijo, lis, buscado);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
                if (!exito) {
                    lis.eliminar(lis.longitud());
                }
            }
        }
        return exito;
    }

    public ArbolGen clone() {
        /*
         * Retorna un arbol clon del arbol original.
         */

        ArbolGen clon = new ArbolGen();

        if (this.raiz != null) {
            clon.raiz = cloneAux(this.raiz);
        }

        return clon;
    }

    private NodoGen cloneAux(NodoGen actual) {
        /*
         * Este metodo recursivo va clonando cada elemento del arbol original,e
         * insertandolo en el arbol clon.
         * 
         * Actual : de tipo NodoGen. Es el nodo del arbol original en que se esta
         * parado.
         */

        NodoGen clonHijo, clonHermano, clonActual = new NodoGen(actual.getElem(), null, null);

        if (actual.getHijoIzquierdo() != null) {
            // Busqueda por hijo izquierdo.
            clonHijo = cloneAux(actual.getHijoIzquierdo());
            clonActual.setHijoIzquierdo(clonHijo);
        }

        if (actual.getHermanoDerecho() != null) {
            // Busqueda por hijo derecho.
            clonHermano = cloneAux(actual.getHermanoDerecho());
            clonActual.setHermanoDerecho(clonHermano);
        }

        return clonActual;
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen nodo, Lista lis) {
        if (nodo != null) {
            // VISTA DEL NODO nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);

            // LLAMADOS RECURSIVOS CON LOS OTROS HIJOS DE nodo
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    listarPreordenAux(hijo, lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen nodo, Lista lis) {
        if (nodo != null) {
            // LLAMADO RECURSIVO CON EL PRIMER HIJO DE nodo
            if (nodo.getHijoIzquierdo() != null) {
                listarInordenAux(nodo.getHijoIzquierdo(), lis);
            }

            // VISTA DEL NODO nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);

            // LLAMADOS RECURSIVOS CON LOS OTROS HIJOS DE nodo
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPosordenAux(NodoGen nodo, Lista lis) {
        if (nodo != null) {

            // LLAMADOS RECURSIVOS CON LOS OTROS HIJOS DE nodo
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    listarPosordenAux(hijo, lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }

            // VISTA DEL NODO nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        Lista lis = new Lista();
        Cola q = new Cola();
        NodoGen nodoActual;
        q.poner(this.raiz);
        if (!this.esVacio()) {
            while (!q.esVacia()) {
                nodoActual = (NodoGen) q.obtenerFrente();
                q.sacar();
                lis.insertar(nodoActual.getElem(), lis.longitud() + 1);

                if (nodoActual.getHijoIzquierdo() != null) {
                    q.poner(nodoActual.getHijoIzquierdo());
                }

                if (nodoActual.getHijoIzquierdo() != null) {
                    NodoGen hijo = nodoActual.getHijoIzquierdo().getHermanoDerecho();
                    while (hijo != null) {
                        q.poner(hijo);
                        hijo = hijo.getHermanoDerecho();
                    }
                }

            }
        }

        return lis;
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen nodo) {
        String s = "";
        if (nodo != null) {
            // VISITA DEL NODO nodo
            s += nodo.getElem().toString() + " ->";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            // COMIENZA RECORRIDO DE LOS HIJOS DE nodo LLAMANDO RECURSIVAMENTE
            // PARA QUE CADA HIJO AGREGUE SU SUBCADENA A LA GENERAL
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public int grado() {
        int grado = -1;
        if (!this.esVacio()) {
            grado = gradoAux(this.raiz);
        }
        return grado;
    }

    private int gradoAux(NodoGen nodo) {
        int gradoMayor = 0;
        int gradoActual = 0;
        int gradoHijo = 0;
        if (nodo != null) {
            // LLAMADOS RECURSIVOS CON LOS OTROS HIJOS DE nodo
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    gradoActual++;
                    gradoHijo = gradoAux(hijo);
                    if (gradoHijo > gradoMayor) {
                        gradoMayor = gradoHijo;
                    }
                    hijo = hijo.getHermanoDerecho();

                }
                if (gradoActual > gradoMayor) {
                    gradoMayor = gradoActual;
                }
            }
        }
        return gradoMayor;
    }

    public int gradoSubarbol(Object elem) {
        int grado = -1;
        if (!this.esVacio()) {
            NodoGen buscado = buscarNodo(this.raiz, elem);
            if (buscado != null) {
                grado = gradoAux(buscado);
            }

        }
        return grado;
    }

    public Lista frontera() {
        /*
         * Metodo sin parametros que llama a meotodo privado "fronteraAux" el cual
         * retornara una lista con las hojas del arbol
         */
        Lista lis = new Lista();
        fronteraAux(this.raiz, lis);
        return lis;
    }

    private void fronteraAux(NodoGen nodo, Lista lis) {
        /*
         * Metodo privado que recibe un nodo que sera la raiz del arbol en primera
         * instancia y una lista donde si el elemento no tiene hijos sera una hoja y
         * este se listara
         */
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            } else {

                // LLAMADOS RECURSIVOS CON LOS OTROS HIJOS DE nodo
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null) {
                        fronteraAux(hijo, lis);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
    }

    public boolean sonFrontera(Lista unaLista) {
        boolean exito;
        int pos = 1;
        exito = sonFronteraAux(this.raiz, unaLista, pos);
        return exito;
    }

    private boolean sonFronteraAux(NodoGen nodo, Lista lista, int pos) {
        boolean exito = false;
        if (nodo != null && pos != -1) {
            if (nodo.getHijoIzquierdo() == null) {
                // Si llego a un hijo izquierdo, tiene que estar en la lista
                if (lista.recuperar(pos) == nodo.getElem()) {
                    exito = true;
                    pos++;
                } else {
                    pos = -1;
                }
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    sonFronteraAux(hijo, lista, pos);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return exito;
    }

    public boolean verificarPatron(Lista patron) {
        boolean exito;
        int pos = 1;
        exito = verificarAux(this.raiz, patron, pos);
        return exito;
    }

    private boolean verificarAux(NodoGen nodo, Lista patron, int pos) {
        boolean exito = false;
        System.out.println(pos);
        // System.out.println("Elemento NODO: "+nodo.getElem());
        // System.out.println("Elemento PATRON: " + patron.recuperar(pos));
        if (pos > patron.longitud()) {
            System.out.println("CAMBIA EXITO A TRUE");
            exito = true;
        } else {
            if (nodo != null && nodo.getElem().equals(patron.recuperar(pos))) {
                System.out.println("ENTRA AL IF: SON IGUALES");

                System.out.println("LLAMA CON HIJO IZQUIERDO. ENTRA");
                exito = verificarAux(nodo.getHijoIzquierdo(), patron, pos + 1);

            } else {
                System.out.println("ENTRA AL ELSE");
                if (nodo != null) {
                    NodoGen hermano = nodo.getHermanoDerecho();
                    // System.out.println("HERMANO: "+ hermano.getElem());
                    while (hermano != null && !exito) {
                        exito = verificarAux(hermano, patron, pos);
                        hermano = hermano.getHermanoDerecho();
                    }
                }
            }

        }
        return exito;
    }

    public Lista listaQueJustificaLaAltura() {
        Lista actual = new Lista();
        Lista res = new Lista();
        if (this.raiz != null) {
            res = listaQueJustificaLaAlturaAux(this.raiz, actual, res);
        }

        return res;
    }

    private Lista listaQueJustificaLaAlturaAux(NodoGen nodo, Lista actual, Lista res) {

        if (nodo != null) {

            if (nodo.getHijoIzquierdo() == null) {
                actual.insertar(nodo.getElem(), actual.longitud() + 1);
                if (actual.longitud() > res.longitud()) {
                    res = actual.clone();
                }
                actual.eliminar(actual.longitud());

            } else {
                actual.insertar(nodo.getElem(), actual.longitud() + 1);

                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null) {
                        res = listaQueJustificaLaAlturaAux(hijo, actual, res);
                        hijo = hijo.getHermanoDerecho();
                    }
                }

                if (actual.longitud() <= res.longitud()) {
                    actual.eliminar(actual.longitud());
                }

            }
        }
        return res;
    }

    public boolean verificarCamino(Lista lis) {
        // Metodo que recibe una lista y verifica si es un camino valido
        boolean exito = false;
        if (!esVacio() && !lis.esVacia()) {
            exito = verificarCaminoAux(this.raiz, lis, 1);
        }
        return exito;
    }

    private boolean verificarCaminoAux(NodoGen nodo, Lista lis, int pos) {
        // Metodo que recibe un nodo y una lista y verifica si es un camino valido
        boolean exito = false;
        if (pos < lis.longitud()) {
            // Si la posicion es menor a la longitud de la lista entonces seguir
            if (nodo != null) {
                if (nodo.getElem().equals(lis.recuperar(pos))) {
                    // Si encuentro el elemento que corresponde a la posicion de la lista entonces
                    // seguir
                    exito = verificarCaminoAux(nodo.getHijoIzquierdo(), lis, pos + 1);
                } else {
                    // Si no encuentro el elemento sigo buscando en el hermano derecho
                    NodoGen hermano = nodo.getHermanoDerecho();
                    while (hermano != null && !exito) {
                        exito = verificarCaminoAux(hermano, lis, pos);
                        hermano = hermano.getHermanoDerecho();
                    }
                }
            }
        } else {
            // Si pos es igual a la longitud de la lista entonces es un camino valido
            exito = true;
        }
        return exito;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        // Metodo que recibe dos niveles y retorna una lista con los elementos de esos
        // niveles
        Lista lista = new Lista();
        if (!esVacio() && niv1 >= 0 && niv2 >= 0 && niv1 <= niv2) {
            listarEntreNivelesAux(this.raiz, niv1, niv2, lista, 0);
        }
        return lista;
    }

    private void listarEntreNivelesAux(NodoGen nodo, int nivInf, int nivSup, Lista lista, int nivelAct) {
        // Metodo que recibe un nodo, dos niveles y una lista y retorna una lista con
        // los elementos de esos niveles con recorrido inorden
        if (nodo != null) {
            if (nivelAct >= nivInf && nivelAct <= nivSup) {
                // Si el nivel actual es mayor o igual al nivel inferior
                // menor o igual al nivel superior
                // entonces seguir
                listarEntreNivelesAux(nodo.getHijoIzquierdo(), nivInf, nivSup, lista, nivelAct + 1);
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
                if (nodo.getHijoIzquierdo() != null) {
                    // Si el hijo izquierdo no es nulo entonces tomo al hermano del hijo izquierdo
                    NodoGen hermanoHijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                    while (hermanoHijo != null) {
                        // Mientras el hermano del hijo izquierdo no sea nulo
                        listarEntreNivelesAux(hermanoHijo, nivInf, nivSup, lista, nivelAct + 1); // llamo al metodo con
                                                                                                 // este hermano
                        hermanoHijo = hermanoHijo.getHermanoDerecho();
                    }
                }
            } else {
                // Si el nivel actual es menor al nivel inferior bajo hasta encontrarlo o
                // encontrar un nodo null
                listarEntreNivelesAux(nodo.getHijoIzquierdo(), nivInf, nivSup, lista, nivelAct + 1);
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen hermanoHijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                    while (hermanoHijo != null) {
                        listarEntreNivelesAux(hermanoHijo, nivInf, nivSup, lista, nivelAct + 1);
                        hermanoHijo = hermanoHijo.getHermanoDerecho();
                    }
                }
            }
        }
    }

}
