package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolAVL {

    private NodoAVL raiz;

    // CONSTRUCTOR
    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.esVacio()) {
            this.raiz = new NodoAVL(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
            if (Math.abs(verificarBalanceo(this.raiz)) > 1) {
                this.raiz = balancear(this.raiz);
            }
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL nodo, Comparable elem) {
        boolean exito = true;
        int balanceo;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElemento()) == 0) { // ENCUENTRA UN ELEMENTO REPETIDO
                exito = false;
            } else if (elem.compareTo(nodo.getElemento()) < 0) {
                // elem es menor que nodo.getElemento()
                // si tiene HI baja a la izquierda, sino agrega elemento

                if (nodo.getIzquierdo() != null) {
                    exito = insertarAux(nodo.getIzquierdo(), elem);

                    nodo.recalcularAltura();
                    balanceo = verificarBalanceo(nodo.getIzquierdo());

                    if (Math.abs(balanceo) > 1) {

                        nodo.setIzquierdo(balancear(nodo.getIzquierdo()));
                        nodo.recalcularAltura();
                    }
                } else {
                    nodo.setIzquierdo(new NodoAVL(elem, null, null));
                    nodo.recalcularAltura();
                }
            } else {
                // elem es mayor que n.getElem()
                // si tiene HD baja a la derecha, sino agrega elemento
                if (nodo.getDerecho() != null) {
                    exito = insertarAux(nodo.getDerecho(), elem);
                    nodo.recalcularAltura();
                    balanceo = verificarBalanceo(nodo.getDerecho());
                    if (Math.abs(balanceo) > 1) {

                        nodo.setDerecho(balancear(nodo.getDerecho()));
                        nodo.recalcularAltura();
                    }
                } else {
                    nodo.setDerecho(new NodoAVL(elem, null, null));
                    nodo.recalcularAltura();
                }
            }
        }
        return exito;
    }

    private int verificarBalanceo(NodoAVL nodo) {
        int balanceo, hI = -1, hD = -1;
        if (nodo.getIzquierdo() != null) {
            hI = nodo.getIzquierdo().getAltura();
        }

        if (nodo.getDerecho() != null) {
            hD = nodo.getDerecho().getAltura();
        }

        balanceo = hI - hD;

        return balanceo;
    }

    private NodoAVL balancear(NodoAVL nodo) {
        NodoAVL h = null;
        int balanceo = verificarBalanceo(nodo);

        if (balanceo > 0 && verificarBalanceo(nodo.getIzquierdo()) >= 0) {

            h = rotarDerecha(nodo);
            h.recalcularAltura();
        } else {

            if (balanceo < 0 && verificarBalanceo(nodo.getDerecho()) <= 0) {
                h = rotarIzquierda(nodo);
                h.recalcularAltura();
            } else {
                if (balanceo > 0 && verificarBalanceo(nodo.getIzquierdo()) < 0) {
                    h = rotarIzquierdaDerecha(nodo);
                    h.recalcularAltura();
                } else {
                    if (balanceo < 0 && verificarBalanceo(nodo.getDerecho()) > 0) {
                        h = rotarDerechaIzquierda(nodo);
                        h.recalcularAltura();
                    }
                }
            }
        }

        return h;
    }

    private NodoAVL rotarDerecha(NodoAVL nodo) {

        NodoAVL hijo = nodo.getIzquierdo();
        NodoAVL temp = hijo.getDerecho();
        hijo.setDerecho(nodo);
        nodo.setIzquierdo(temp);
        nodo.recalcularAltura();
        return hijo;
    }

    private NodoAVL rotarIzquierda(NodoAVL nodo) {

        NodoAVL hijo = nodo.getDerecho();
        NodoAVL temp = hijo.getIzquierdo();
        hijo.setIzquierdo(nodo);
        nodo.setDerecho(temp);
        nodo.recalcularAltura();
        return hijo;
    }

    private NodoAVL rotarIzquierdaDerecha(NodoAVL nodo) {

        nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));

        return rotarDerecha(nodo);
    }

    private NodoAVL rotarDerechaIzquierda(NodoAVL nodo) {
        nodo.setDerecho(rotarDerecha(nodo.getDerecho()));

        return rotarIzquierda(nodo);
    }

    public boolean eliminar(Comparable elem) {
        boolean exito = false;
        NodoAVL encontrado = null;
        exito = eliminarAux(this.raiz, null, elem);
        return exito;
    }

    private boolean eliminarAux(NodoAVL nodo, NodoAVL padre, Comparable elem) {
        boolean exito = false;
        int balanceo;
        if (nodo != null) {
            if (nodo.getElemento().compareTo(elem) == 0) {
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    caso1(padre, nodo);
                    padre.recalcularAltura();

                } else {
                    if (nodo.getIzquierdo() != null && nodo.getDerecho() == null
                            || nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                        caso2(padre, nodo);
                        padre.recalcularAltura();

                    } else {
                        if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                            caso3(nodo);
                            if (padre != null) {
                                padre.recalcularAltura();
                            }
                        }
                    }
                }
                exito = true;

                balanceo = verificarBalanceo(nodo);
                if (padre != null && padre.getIzquierdo() == nodo) {
                    if (balanceo > 1) {
                        padre.setIzquierdo(balancear(nodo));
                    }
                } else if (padre != null && padre.getDerecho() == nodo) {
                    if (balanceo > 1) {
                        padre.setDerecho(balancear(nodo));
                    }
                } else {
                    if (Math.abs(verificarBalanceo(this.raiz)) > 1) {
                        this.raiz = balancear(nodo);
                    }
                }

            } else {
                if (nodo.getIzquierdo() != null && nodo.getElemento().compareTo(elem) > 0 && !exito) {
                    exito = eliminarAux(nodo.getIzquierdo(), nodo, elem);

                    nodo.recalcularAltura();
                    balanceo = verificarBalanceo(nodo);
                    if (Math.abs(balanceo) > 1) {
                        if (padre.getDerecho() == nodo) {
                            padre.setDerecho(balancear(nodo));
                        } else {
                            padre.setIzquierdo(balancear(nodo));
                        }

                    } else {
                        this.raiz.recalcularAltura();
                        if (Math.abs(verificarBalanceo(this.raiz)) > 1) {
                            this.raiz = balancear(nodo);
                        }
                    }
                } else {

                    if (nodo.getDerecho() != null && nodo.getElemento().compareTo(elem) < 0 && !exito) {
                        exito = eliminarAux(nodo.getDerecho(), nodo, elem);

                        nodo.recalcularAltura();
                        balanceo = verificarBalanceo(nodo);
                        if (Math.abs(balanceo) > 1) {
                            if (padre.getIzquierdo() == nodo) {
                                padre.setIzquierdo(balancear(nodo));
                            } else {
                                padre.setDerecho(balancear(nodo));
                            }

                        } else {
                            this.raiz.recalcularAltura();
                            if (Math.abs(verificarBalanceo(this.raiz)) > 1) {
                                this.raiz = balancear(nodo);
                            }
                        }

                    }
                }
            }

        }
        return exito;
    }

    private void caso3(NodoAVL nodo) {
        NodoAVL candidato = nodo.getDerecho();
        NodoAVL padre = nodo;
        // BUSCO COMO CANDIDATO AL MENOR DE LOS HD
        while (candidato.getIzquierdo() != null) {
            padre = candidato;
            candidato = candidato.getIzquierdo();
        }
        // seteo el candidato en el elemento a eliminar
        nodo.setElemento(candidato.getElemento());

        if (candidato.getIzquierdo() == null && candidato.getDerecho() == null) {
            caso1(padre, candidato);
        } else {
            if (candidato.getIzquierdo() != null && candidato.getDerecho() == null
                    || candidato.getIzquierdo() == null && candidato.getDerecho() != null) {
                caso2(padre, candidato);
            }
        }

    }

    private void caso2(NodoAVL padre, NodoAVL nodo) {
        if (padre == null) {
            if (nodo.getIzquierdo() == null) {
                this.raiz = nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                this.raiz = nodo.getIzquierdo();
            }

        }
        if (padre.getIzquierdo() != null && padre.getIzquierdo().getElemento().compareTo(nodo.getElemento()) == 0) {
            if (nodo.getIzquierdo() == null) {
                padre.setIzquierdo(nodo.getDerecho());
            }
            if (nodo.getDerecho() == null) {
                padre.setIzquierdo(nodo.getIzquierdo());
            }
        }
        if (padre.getDerecho() != null && padre.getDerecho().getElemento().compareTo(nodo.getElemento()) == 0) {
            if (nodo.getIzquierdo() == null) {
                padre.setDerecho(nodo.getDerecho());
            }
            if (nodo.getDerecho() == null) {
                padre.setDerecho(nodo.getIzquierdo());
            }
        }
    }

    private void caso1(NodoAVL padre, NodoAVL nodo) {

        if (padre == null) {
            this.raiz = null;
        } else {
            if (padre.getIzquierdo() != null && padre.getIzquierdo().getElemento().compareTo(nodo.getElemento()) == 0) {
                padre.setIzquierdo(null);

            } else {
                if (padre.getDerecho() != null && padre.getDerecho().getElemento().compareTo(nodo.getElemento()) == 0) {
                    padre.setDerecho(null);

                }
            }
        }

    }

    public boolean pertenece(Comparable elem) {
        boolean exito = false;
        if (!this.esVacio()) {
            exito = perteneceAux(this.raiz, elem);
        }
        return exito;
    }

    public boolean perteneceAux(NodoAVL nodo, Comparable elem) {
        boolean exito = false;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElemento()) == 0) { // ENCUENTRA UN ELEMENTO REPETIDO
                exito = true;
            } else if (elem.compareTo(nodo.getElemento()) < 0) {
                // elem es menor que nodo.getElemento()
                // si tiene HI baja a la izquierda
                if (nodo.getIzquierdo() != null) {
                    exito = perteneceAux(nodo.getIzquierdo(), elem);
                }

            } else {
                // elem es mayor que n.getElem()
                // si tiene HD baja a la derecha
                if (nodo.getDerecho() != null) {
                    exito = perteneceAux(nodo.getDerecho(), elem);
                }

            }
        }
        return exito;
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public Lista listar() {
        /*
         * Llama a un metodo privado "ListarAux" donde se envia la raiz
         * del arbol mas una lista creada en este modulo
         */
        Lista lis = new Lista();
        listarAux(this.raiz, lis);
        return lis;
    }

    private void listarAux(NodoAVL nodo, Lista lis) {
        /*
         * Metodo que recibe por parametro un nodo que es la raiz del subarbol
         * recorrido y una lista donde se listaran los elementos del arbol en in orden
         */

        if (nodo != null) {
            // RECORRE A SUS HIJOS EN INORDEN
            listarAux(nodo.getIzquierdo(), lis);

            // VISITA EL ELEMENTO EN EL NODO
            lis.insertar(nodo.getElemento(), lis.longitud() + 1);

            listarAux(nodo.getDerecho(), lis);
        }

    }

    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
        Lista lis = new Lista();

        listarRangoAux(this.raiz, elemMinimo, elemMaximo, lis);

        return lis;
    }

    private void listarRangoAux(NodoAVL nodo, Comparable elemMin, Comparable elemMax, Lista lis) {
        if (nodo != null) {
            // RECORRE A SUS HIJOS EN INORDEN
            if (nodo.getIzquierdo() != null && nodo.getElemento().compareTo(elemMin) > 0) {
                listarRangoAux(nodo.getIzquierdo(), elemMin, elemMax, lis);
            }

            if (nodo.getElemento().compareTo(elemMin) >= 0 && nodo.getElemento().compareTo(elemMax) <= 0) {
                lis.insertar(nodo.getElemento(), lis.longitud() + 1);
            }
            if (nodo.getDerecho() != null && nodo.getElemento().compareTo(elemMax) < 0) {
                listarRangoAux(nodo.getDerecho(), elemMin, elemMax, lis);
            }

        }
    }

    public Comparable minimoElem() {
        Comparable elem = null;
        if (!this.esVacio()) {
            elem = minimoElemAux(this.raiz);
        }
        return elem;
    }

    private Comparable minimoElemAux(NodoAVL nodo) {
        Comparable buscado = null;
        if (nodo != null) {
            if (nodo.getIzquierdo() == null) {
                buscado = nodo.getElemento();
            } else {
                buscado = minimoElemAux(nodo.getIzquierdo());
            }
        }
        return buscado;
    }

    public Comparable maximoElem() {
        Comparable elem = null;
        if (!this.esVacio()) {
            elem = maximoElemAux(this.raiz);
        }
        return elem;
    }

    private Comparable maximoElemAux(NodoAVL nodo) {
        Comparable buscado = null;
        if (nodo != null) {
            if (nodo.getDerecho() == null) {
                buscado = nodo.getElemento();
            } else {
                buscado = maximoElemAux(nodo.getDerecho());
            }
        }
        return buscado;
    }

    public ArbolAVL clone() {
        /*
         * Metodo que llama a metodo privado si el arbol no es vacio con elemento
         * raiz como parametro. Si el arbol esta vacio se retorna un arbol vacio
         */
        ArbolAVL copia = new ArbolAVL();

        if (!this.esVacio()) {
            copia.raiz = clonAux(this.raiz);
        }

        return copia;
    }

    private NodoAVL clonAux(NodoAVL nodo) {
        /*
         * Metodo privado que recibe nodo con raiz del arbol en primera instancia
         * clona un arbol recursivamente donde se va creando un nuevo nodo que sera
         * raiz del arbol clonado y se repite hasta las hojas del arbol original
         */
        NodoAVL nuevoNodo = new NodoAVL(nodo.getElemento(), null, null);

        if (nodo.getIzquierdo() != null) {
            nuevoNodo.setIzquierdo(clonAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nuevoNodo.setDerecho(clonAux(nodo.getDerecho()));
        }
        return nuevoNodo;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public String toString() {
        /*
         * Metodo sin parametros para testeo de programa se recomienda comentar
         * cuando no se esta testeando. En caso de ser vacio se retorna un mensaje
         * "ARBOL VACIO" sino se llama a un metodo privado "toStringAux"
         */
        String texto = "";

        if (this.esVacio()) {
            texto = "ARBOL VACIO";
        } else {
            texto = toStringAux(this.raiz, texto);
        }

        return texto;
    }

    private String toStringAux(NodoAVL nodo, String texto) {
        /*
         * Metodo privado que recibe un nodo siendo este la raiz en una primera
         * instancia junto con una cadena vacia que sera la retornada
         */

        if (nodo != null) {// En caso de nodo null se retorna una cadena vacia
            if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                // En caso de tener ambos hijos se imprimen
                texto = texto + "NODO: " + nodo.getElemento().toString()
                        + " HI: " + nodo.getIzquierdo().getElemento().toString() + " "
                        + " HD:  " + nodo.getDerecho().getElemento().toString() + "\n";
            } else {
                // 3 posibles casos donde no se encuentra a ambos hijos se puede tener un hijo
                // null y el otro no o no tener ninguno
                if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                    texto = texto + "NODO: " + nodo.getElemento().toString() + " HI: --" + " HD: "
                            + nodo.getDerecho().getElemento().toString() + "\n";
                } else if (nodo.getDerecho() == null && nodo.getIzquierdo() != null) {
                    texto = texto + "NODO: " + nodo.getElemento().toString() + " HI: "
                            + nodo.getIzquierdo().getElemento().toString() + " " + " HD: --" + "\n";
                } else {
                    texto = texto + "NODO: " + nodo.getElemento().toString() + " HI: --" + " HD: --" + "\n";
                }
            }
            // Si existieran nodos visitariamos estos hasta llegar al null
            texto = toStringAux(nodo.getIzquierdo(), texto);
            texto = toStringAux(nodo.getDerecho(), texto);
        }

        return texto;
    }
}