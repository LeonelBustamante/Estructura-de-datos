package conjuntistas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

public class TablaHash {

    private final int TAMANIO = 10;
    private Nodo[] tabla;
    private int cant;

    public TablaHash() {
        tabla = new Nodo[TAMANIO];
        cant = 0;
    }

    public boolean insertar(Object elemento) {
        int pos = Funciones.hash(elemento) % TAMANIO;
        Nodo aux = tabla[pos];
        boolean encontrado = buscarElemento(aux, elemento); // busca si el elemento ya esta en la tabla
        if (!encontrado) {
            // si no esta en la tabla, la inserto
            tabla[pos] = new Nodo(elemento, tabla[pos]);
            cant++;
        }
        return !encontrado; // devuelve true si no estaba en la tabla
    }

    private boolean buscarElemento(Nodo aux, Object elemento) {
        // busca si el elemento ya esta en la tabla
        boolean encontrado = false;
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elemento);
            aux = aux.getEnlace();
        }
        return encontrado;
    }

    public boolean eliminar(Object elemento) {
        int pos = Funciones.hash(elemento) % TAMANIO;
        Nodo aux = tabla[pos];
        return eliminarElemento(aux, elemento, pos);
    }

    private boolean eliminarElemento(Nodo aux, Object elemento, int pos) {
        // elimina un elemento de la tabla
        boolean encontrado = false;
        Nodo anterior = null;
        while (!encontrado && aux != null) {
            if (aux.getElem().equals(elemento)) {
                // si lo encuentra, lo elimina
                if (anterior == null) {
                    // si es el primer elemento de la lista
                    tabla[pos] = aux.getEnlace();
                } else {
                    // si no es el primer elemento de la lista
                    anterior.setEnlace(aux.getEnlace());
                }
                cant--;
            } else {
                // si no es el elemento a eliminar seguimos buscando
                anterior = aux;
                aux = aux.getEnlace();
            }
        }
        return false;
    }

    public boolean pertenece(Object elemento) {
        // busca si el elemento esta en la tabla
        int pos = Funciones.hash(elemento) % TAMANIO;
        Nodo aux = tabla[pos];
        return buscarElemento(aux, elemento); // devuelve true si no estaba en la tabla
    }

    public boolean esVacia() {
        // devuelve true si la tabla esta vacia
        return cant == 0;
    }

    public Lista listar() {
        Lista lista = new Lista();
        for (int i = 0; i < TAMANIO; i++) {
            // recorre la tabla
            Nodo aux = tabla[i]; // obtiene el primer elemento de la lista
            while (aux != null) {
                // recorre cada celda
                lista.insertar(aux.getElem(), lista.longitud() + 1);
                aux = aux.getEnlace();
            }
        }
        return lista;
    }

}
