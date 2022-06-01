package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        // Inserta un elemento en el árbol AVL
        boolean exito = true;
        if (esVacio()) {
            raiz = new NodoAVL(elemento, null, null, 0);
        } else {
            exito = insertarAux(elemento, raiz);
        }
        return exito;
    }

    private boolean insertarAux(Comparable elemento, NodoAVL actual) {
        boolean exito, esHijoIzq;
        if (elemento.compareTo(actual.getElem()) == 0) {
            exito = false;
        } else {
            if (elemento.compareTo(actual.getElem()) < 0) {
                if (actual.getIzquierdo() == null) {
                    exito = true;
                    actual.setIzquierdo(new NodoAVL(elemento));
                } else {
                    exito = insertarAux(elemento, actual.getIzquierdo());
                }

                esHijoIzq = true;
            } else {
                if (actual.getDerecho() == null) {
                    exito = true;
                    actual.setDerecho(new NodoAVL(elemento));
                } else {
                    exito = insertarAux(elemento, actual.getDerecho());
                }
                esHijoIzq = false;
            }
            if (exito) {
                actual.recalcularAltura();
                if (esHijoIzq) {
                    rotacion(actual, actual.getIzquierdo(), true);
                } else {
                    rotacion(actual, actual.getDerecho(), false);
                }
                actual.recalcularAltura();
            }
        }
        return exito;
    }

    private void rotacion(NodoAVL padre, NodoAVL hijo, boolean esHijoIzq) {
        /*
         * Este metodo decide si es necesario aplicar una rotacion.En caso de serlo,
         * cual de las cuatro aplicar.
         * 
         * padre : de tipo NodoAVL.Padre del nodo cuyo balance se analizara.
         * hijo : de tipo NodoAVL.Nodo a analizar,y posiblemente, rotar.
         * esHijOizq : de tipo boolean.Define si hijo es izquierdo,o no, de padre.
         */

        int balance = balance(hijo);
        NodoAVL nuevaRaizSubarbol;

        if (balance < -1) {
            // hijo esta desbalanceado a derecha,o esta balanceado.
            if (balance(hijo.getDerecho()) <= 0) {
                System.out.println(hijo.getElem());
                // el hijo de nodo hijo esta desbalanceado a derecha
                nuevaRaizSubarbol = rotarIzquierda(hijo);
            } else {
                // el hijo de nodo hijo esta desbalanceado a izquierda
                nuevaRaizSubarbol = rotarDerechaIzquierda(hijo);
            }
        } else if (balance > 1) {
            // hijo esta desbalanceado a izquierda
            if (balance(hijo.getIzquierdo()) >= 0) {
                // el hijo de nodo hijo esta desbalanceado a izquierda,o esta balanceado.
                nuevaRaizSubarbol = rotarDerecha(hijo);
            } else {
                // el hijo de nodo hijo esta desbalanceado a derecha.
                nuevaRaizSubarbol = rotarIzquierdaDerecha(hijo);
            }
        } else {
            // hijo esta balanceado
            nuevaRaizSubarbol = null;
        }

        if (nuevaRaizSubarbol != null) {
            // si exitio rotacion,a padre de le asigna un nuevo hijo.
            if (padre != null) {
                // si nodo hijo no es raiz,entonces tiene padre.
                if (esHijoIzq) {
                    padre.setIzquierdo(nuevaRaizSubarbol);
                } else {
                    padre.setDerecho(nuevaRaizSubarbol);
                }
            } else {
                // sino,nodo hijo no tiene padre,y se lo asigna a la raiz.
                raiz = nuevaRaizSubarbol;
            }

        }

    }

    private NodoAVL rotarIzquierda(NodoAVL padre) {
        /*
         * Este metodo realiza la operacion de rotar a la izquierda.
         */

        NodoAVL hijo = padre.getDerecho(), temp = hijo.getIzquierdo();

        hijo.setIzquierdo(padre);
        padre.setDerecho(temp);
        padre.recalcularAltura();

        return hijo;
    }

    private NodoAVL rotarDerecha(NodoAVL padre) {
        /*
         * Este metodo realiza la operacion de rotar a la derecha.
         */

        NodoAVL hijo = padre.getIzquierdo(), temp = hijo.getDerecho();

        hijo.setDerecho(padre);
        padre.setIzquierdo(temp);
        padre.recalcularAltura();

        return hijo;
    }

    private NodoAVL rotarIzquierdaDerecha(NodoAVL padre) {
        /*
         * Este metodo realiza la operacion de rotacion doble izquierda-derecha.
         */

        padre.setIzquierdo(rotarIzquierda(padre.getIzquierdo()));
        return rotarDerecha(padre);
    }

    private NodoAVL rotarDerechaIzquierda(NodoAVL padre) {
        /*
         * Este metodo realiza la operacion de rotacion doble derecha-izquierda.
         */

        padre.setDerecho(rotarDerecha(padre.getDerecho()));
        return rotarIzquierda(padre);
    }

    private int balance(NodoAVL padre) {
        /*
         * Este metodo calcula el balance de un nodo padre.
         */

        int altIzq, altDer;

        if (padre.getIzquierdo() != null) {
            altIzq = padre.getIzquierdo().getAltura();
        } else {
            altIzq = -1;
        }

        if (padre.getDerecho() != null) {
            altDer = padre.getDerecho().getAltura();
        } else {
            altDer = -1;
        }

        return altIzq - altDer;
    }

    public boolean eliminar(Comparable elemento) {
        /*
         * Este metodo elimina el elemento del arbol,si es que existe.
         */

        boolean exito;

        if (raiz != null) {
            exito = eliminarAux(elemento, raiz, null, true);
            if (exito) {
                raiz.recalcularAltura();
                rotacion(null, raiz, true);
                raiz.recalcularAltura();
            }
        } else {
            exito = false;
        }

        return exito;
    }

    private boolean eliminarAux(Comparable elemento, NodoAVL actual, NodoAVL padre, boolean esHijoIzq) {
        /*
         * Este metodo busca un nodo con elemento en el arbol,y decide mediante cual
         * caso
         * se lo eliminara.En caso de no existir,la operacion no tiene exito.
         * 
         * actual : de tipo NodoABB.Nodo actual que analizamos.
         * padre : de tipo NodoABB. Nodo padre de actual.
         * esHijoIzq : de tipo boolean.Define si el nodo actual es hijo izquierdo.Si
         * es la raiz,por defecto es valor true.
         */

        int comparacion;
        char hijos;
        boolean exito;

        if (actual != null) {
            comparacion = elemento.compareTo(actual.getElem());
            if (comparacion == 0) {
                hijos = cantidadHijos(actual);

                switch (hijos) {
                    case '0':
                        caso1(padre, esHijoIzq);
                        break;

                    case 'i':
                        caso2(padre, esHijoIzq, true);
                        break;

                    case 'd':
                        caso2(padre, esHijoIzq, false);
                        break;

                    case '2':
                        caso3(actual);
                        break;
                }

                exito = true;
            } else {
                if (comparacion < 0) {
                    exito = eliminarAux(elemento, actual.getIzquierdo(), actual, true);
                } else {
                    exito = eliminarAux(elemento, actual.getDerecho(), actual, false);
                }

                if (exito) {
                    // Si se inserto el elemento, se procede a evaluar posibles rotaciones.

                    actual.recalcularAltura();

                    System.out.println(actual.getAltura());
                    if (esHijoIzq) {
                        rotacion(padre, actual, true);
                    } else {
                        rotacion(padre, actual, false);
                    }
                    actual.recalcularAltura();
                }

            }
        } else {
            exito = false;
        }

        return exito;
    }

    private char cantidadHijos(NodoAVL actual) {
        /*
         * Este metodo retorna un char dependiendo de la cantidad de hijos que tenga
         * actual,0 de si es Izquierdo o Derecho(en caso de que tenga solo 1)/
         */

        char resultado;

        if (actual.getIzquierdo() != null) {
            if (actual.getDerecho() != null) {
                resultado = '2';
            } else {
                resultado = 'i';
            }
        } else {
            if (actual.getDerecho() != null) {
                resultado = 'd';
            } else {
                resultado = '0';
            }
        }

        return resultado;
    }

    private void caso1(NodoAVL padre, boolean esHijoIzq) {
        /*
         * Este metodo elimina del arbol un nodo que es hoja.
         * 
         * padre : de tipo NodoArbol.Representa al padre del nodo a eliminar.
         * esHijoIzq : de tipo boolean.Su valor depende de si el nodo a eliminar es hijo
         * izquierdo de padre, o no lo es.
         */

        if (padre != null) {
            // El nodo a eliminar no es raiz.
            if (esHijoIzq) {
                // El nodo a eliminar es hijo izquierdo.
                padre.setIzquierdo(null);
            } else {
                // El nodo a eliminar es hijo derecho.
                padre.setDerecho(null);
            }
        } else {
            // El nodo a eliminar es la raiz.
            raiz = null;
        }
    }

    private void caso2(NodoAVL padre, boolean esHijoIzq, boolean esNietoIzq) {
        /*
         * Este metodo elimina del arbol un nodo que tiene 1 hijo.
         * 
         * padre : de tipo NodoArbol.Representa al padre del nodo a eliminar.
         * esHijoIzq : de tipo boolean.Su valor depende de si el nodo a eliminar es hijo
         * izquierdo de padre, o no lo es.
         * esNietoIzq : de tipo boolean.Su valor depende de si el hijo del nodo a
         * eliminar
         * es hijo izquierdo de este, o no lo es.
         */

        if (padre != null) {
            // El nodo a eliminar no es raiz.
            if (esHijoIzq) {
                // El nodo a eliminar es hijo izquierdo.
                if (esNietoIzq) {
                    // El hijo del nodo a eliminar es hijo izquierdo.
                    padre.setIzquierdo(padre.getIzquierdo().getIzquierdo());
                } else {
                    // El hijo del nodo a eliminar es hijo derecho.
                    padre.setIzquierdo(padre.getIzquierdo().getDerecho());
                }
            } else {
                // El nodo a eliminar es hijo izquierdo.
                if (esNietoIzq) {
                    // El hijo del nodo a eliminar es hijo izquierdo.
                    padre.setDerecho(padre.getDerecho().getIzquierdo());
                } else {
                    // El hijo del nodo a eliminar es hijo derecho.
                    padre.setDerecho(padre.getDerecho().getDerecho());
                }
            }
        } else {
            // El nodo a eliminar es raiz.
            if (esHijoIzq) {
                // El nodo a eliminar es hijo izquierdo.
                raiz = raiz.getIzquierdo();
            } else {
                raiz = raiz.getDerecho();
            }
        }
    }

    private void caso3(NodoAVL actual) {
        /*
         * Este metodo elimina un nodo con dos hijos.
         * 
         * actual : de tipo NodoArbol.Representa el nodo a eliminar.
         */

        actual.setElem(candidato(actual.getDerecho(), actual));
    }

    private Comparable candidato(NodoAVL actual, NodoAVL padre) {
        /*
         * Este metodo busca un candidato para reemplazar el valor de la raiz.Este
         * metodo funciona
         * en contexto al objetivo de eliminarNodoConDosHijos()
         * 
         * actual : de tipo NodoArbol.Nodo actual que se esta analizando.
         */
        if (actual.getIzquierdo() != null) {
            // Si todavia no se encontro el candidato.
            do {
                // Se busca hijo izquierdo hasta que se llegue a una hoja.
                padre = actual;
                actual = actual.getIzquierdo();
            } while (actual.getIzquierdo() != null);

            // Se elimina el nodo actual,ya que su valor tomara la posicion del valor a
            // eliminar.
            padre.setIzquierdo(actual.getDerecho());
        } else {
            // Si se encontro con el candidato,se elimina el nodo actual,
            // ya que su valor tomara la posicion del valor a eliminar.
            padre.setDerecho(actual.getDerecho());
        }

        return (Comparable) actual.getElem();
    }

    public void vaciar() {
        /*
         * Este metodo vacia el arbol.
         */

        raiz = null;
    }

    // Observadores

    public boolean pertenece(Comparable elemento) {
        /*
         * Este metodo retorna un boolean,dependiendo de si elemento pertenece, o no,
         * al arbol.
         */

        boolean pertenece;

        if (raiz != null) {
            pertenece = perteneceAux(elemento, raiz);
        } else {
            pertenece = false;
        }

        return pertenece;
    }

    private boolean perteneceAux(Comparable elemento, NodoAVL actual) {
        /*
         * Este metodo recursivo evalua los nodos necesarios para ver si existe elemento
         * en el arbol,y retorna un booleano.
         * 
         * actual : de tipo NodoArbol.Nodo actual que se esta analizando.
         */

        boolean exito;
        int comparacion = elemento.compareTo(actual.getElem());

        if (comparacion == 0) {
            // Si se encuentra el elemento,hubo exito en la busqueda.
            exito = true;
        } else {
            // Sino,se intetara seguir buscando.
            if (comparacion < 0 && actual.getIzquierdo() != null) {
                // Si existe subarbol izquierdo,y corresponde,se sigue buscando ahi.
                exito = perteneceAux(elemento, actual.getIzquierdo());
            } else if (comparacion > 0 && actual.getDerecho() != null) {
                // Sino,si existe subarbol derecho,y corresponde,se sigue buscando ahi.
                exito = perteneceAux(elemento, actual.getDerecho());
            } else {
                // Si no existen subarboles,la busqueda llego a su fin sin exito.
                exito = false;
            }
        }

        return exito;
    }

    public boolean esVacio() {
        /*
         * Este metodo retorna un boolean,dependiendo de si el arbol es vacio.
         */

        return raiz != null;
    }

    // Propios del tipo

    public Lista listar() {
        /*
         * Este metodo genera una lista ordenada de elementos del arbol.
         */

        Lista lista = new Lista();

        if (raiz != null) {
            listarAux(raiz, lista, 1);
        }

        return lista;
    }

    private int listarAux(NodoAVL actual, Lista inorden, int posc) {
        // Va al subarbol izquierdo,y repite proceso.
        if (actual.getIzquierdo() != null) {
            posc = listarAux(actual.getIzquierdo(), inorden, posc);
        }

        // inserta la raiz del subarbol actual.
        inorden.insertar(actual.getElem(), posc);
        posc++;

        // Va al subarbol derecho y repite proceso.
        if (actual.getDerecho() != null) {
            posc = listarAux(actual.getDerecho(), inorden, posc);
        }

        return posc;
    }

    public Lista listarRango(Comparable min, Comparable max) {
        /*
         * Este metodo genera una lista ordenada de elementos del arbol.
         */

        Lista lista = new Lista();

        if (raiz != null) {
            listarRangoAux(raiz, lista, 1, min, max);
        }

        return lista;
    }

    private int listarRangoAux(NodoAVL actual, Lista inorden, int posc, Comparable min, Comparable max) {
        double comparacionMin = actual.getElem().compareTo(min), comparacionMax = actual.getElem().compareTo(max);

        // Va al subarbol izquierdo,y repite proceso.
        if (actual.getIzquierdo() != null && comparacionMin > 0) {
            posc = listarRangoAux(actual.getIzquierdo(), inorden, posc, min, max);
        }

        // inserta la raiz del subarbol actual.
        if (comparacionMin >= 0 && comparacionMax <= 0) {
            inorden.insertar(actual.getElem(), posc);
            posc++;
        }

        // Va al subarbol derecho y repite proceso.
        if (actual.getDerecho() != null && comparacionMax < 0) {
            posc = listarRangoAux(actual.getDerecho(), inorden, posc, min, max);
        }

        return posc;
    }

    public Comparable minimoElem() {
        /*
         * Este metodo retorna el menor elemento del arbol.
         */

        NodoAVL actual = raiz;
        Comparable resultado;

        if (actual != null) {
            // Si existe la raiz,se hace la busqueda.
            while (actual.getIzquierdo() != null) {
                // Se busca el menor elemento.
                actual = actual.getIzquierdo();
            }

            resultado = actual.getElem();
        } else {
            // Sino,no existe resultado.
            resultado = null;
        }

        return resultado;
    }

    public Comparable maximoElem() {
        /*
         * Este metodo retorna el mayor elemento del arbol.
         */

        NodoAVL actual = raiz;
        Comparable resultado;

        if (actual != null) {
            // Si existe la raiz,se hace la busqueda.
            while (actual.getDerecho() != null) {
                // Se busca el mayor elemento.
                actual = actual.getDerecho();
            }

            resultado = actual.getElem();
        } else {
            // Sino,no existe resultado.
            resultado = null;
        }

        return resultado;
    }

    @Override
    public String toString() {
        /*
         * Este metodo retorna un string con los elementos del arbol.
         */

        String resultado = "";

        if (raiz != null) {
            resultado = generaToString(raiz);
        }

        return resultado;
    }

    private String generaToString(NodoAVL actual) {
        boolean existeIzq, existeDer;
        String resultado, izq, der;

        if (actual.getIzquierdo() != null) {
            // Si existe hijo izquierdo.
            izq = "" + actual.getIzquierdo().getElem();
            existeIzq = true;
        } else {
            // Sino existe hijo izquierdo.
            izq = "-";
            existeIzq = false;

        }

        if (actual.getDerecho() != null) {
            // Si existe hijo derecho.
            der = "" + actual.getDerecho().getElem();
            existeDer = true;
        } else {
            // Si no existe hijo derecho.
            der = "-";
            existeDer = false;

        }

        // Se genera string en elemento actual.
        resultado = actual.getElem() + "  HI: " + izq + "  HD: " + der + "\n";

        if (existeIzq) {
            // Si existe hijo izq,se procede con el.
            resultado += generaToString(actual.getIzquierdo());
        }

        if (existeDer) {
            // Si existe hijo der,se procede con el.
            resultado += generaToString(actual.getDerecho());
        }

        return resultado;
    }
}
