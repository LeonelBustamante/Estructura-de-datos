package lineales.estaticas;

public class Pila {

    private static final int TAMANIO = 10;
    private Object[] arr;
    private int tope;

    public Pila() {
        /**
         * Crea y devuelve la pila vacía.
         */
        arr = new Object[TAMANIO];
        tope = -1;
    }

    public boolean apilar(Object nuevoElem) {
        /**
         * Pone el elemento nuevoElem en el tope de la pila. Devuelve verdadero
         * si el elemento se pudo apilar y falso en caso contrario.
         */
        boolean exito;
        if (tope + 1 >= TAMANIO) // Error: pila llena
        {
            exito = false;
        } else // pone el elemento en el tope de la pila e incrementa tope 
        {
            tope++;
            arr[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    @Override
    public Pila clone() {
        /**
         * Devuelve una copia exacta de los datos en la estructura original, y
         * respetando el orden de los mismos, en otra estructura del mismo tipo
         */
        Pila clon = new Pila();
        clon.arr = arr.clone();
        clon.tope = tope;
        return clon;
    }

    public boolean desapilar() {
        /**
         * Saca el elemento del tope de la pila. Devuelve verdadero si la pila
         * no estaba vacía al momento de desapilar(es decir que se pudo
         * desapilar) y falso en caso contrario.
         */
        boolean exito = false;
        if (!esVacia()) {
            arr[tope] = null;
            tope = tope - 1;
            exito = true;
        }
        return exito;
    }

    public boolean esVacia() {
        /**
         * Devuelve verdadero si la pila no tiene elementos y falso en caso
         * contrario.
         */
        return tope == -1;
    }

    public Object obtenerTope() {
        /**
         * Devuelve el elemento en el tope de la pila. Precondición: la pila no
         * está vacía.
         */
        return esVacia() ? null : arr[tope];
    }

    @Override
    public String toString() {
        /**
         * Devuelve una cadena de caracteres formada por todos los elementos de
         * la pila para poder mostrarla por pantalla.
         */
        String cadena = "ESTRUCTURA VACIA";
        if (!esVacia()) {
            cadena = "PILA ESTATICA: [ ";
            for (int i = 0; i <= this.tope; i++) {
                cadena += i == 0 ? this.arr[i] : ", " + this.arr[i];
            }
            cadena += " ]<-(Tope)";
        }
        return cadena;
    }

    public void vaciar() {
        /**
         * Saca todos los elementos de la pila.
         */
        for (int i = 0; i < tope; i++) {
            arr[i] = null;
        }
        this.tope = -1;
    }
}
