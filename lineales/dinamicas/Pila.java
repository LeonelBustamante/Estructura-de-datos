package lineales.dinamicas;

public class Pila {

    private Nodo tope;

    public Pila() {
        /**
         * Crea y devuelve la pila vacía.
         */
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        /**
         * Pone el elemento nuevoElem en el tope de la pila. Devuelve verdadero
         * si el elemento se pudo apilar y falso en caso contrario.
         */
        this.tope = new Nodo(nuevoElem, this.tope);
        return true;
    }

    public boolean desapilar() {
        /**
         * Saca el elemento del tope de la pila. Devuelve verdadero si la pila
         * no estaba vacía al momento de desapilar(es decir que se pudo
         * desapilar) y falso en caso contrario.
         */
        boolean exito = false;
        if (!esVacia()) //Chequeo que haya algun elemento para evitar nullPointerException
        {
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    @Override
    public String toString() {
        /**
         * Devuelve una cadena de caracteres formada por todos los elementos de
         * la pila para poder mostrarla por pantalla.
         */
        String cadena = "ESTRUCTURA VACIA";
        if (!esVacia()) {
            cadena = "PILA DINAMICA: (Tope)-> [ ";
            Nodo aux = this.tope;
            while (aux != null) {
                cadena += aux.getEnlace() == null ? aux.getElem() : aux.getElem() + ", ";
                aux = aux.getEnlace();
            }
            cadena += " ]";
        }
        return cadena;
    }

    @Override
    public Pila clone() {
        /**
         * Devuelve una copia exacta de los datos en la estructura original, y
         * respetando el orden de los mismos, en otra estructura del mismo tipo
         */
        Pila clon = new Pila();
        if (!esVacia()) {
            clon.tope = new Nodo(this.tope.getElem(), null);
            Nodo aux1 = this.tope.getEnlace();
            Nodo aux2 = clon.tope;
            while (aux1 != null) {
                aux2.setEnlace(new Nodo(aux1.getElem(), null));
                aux2 = aux2.getEnlace();
                aux1 = aux1.getEnlace();
            }
        }
        return clon;
    }

    public Object obtenerTope() {
        /**
         * Devuelve el elemento en el tope de la pila. Precondición: la pila no
         * está vacía.
         */
        return esVacia() ? null : this.tope.getElem();
    }

    public void vaciar() {
        this.tope = null;
    }

    public boolean esVacia() {
        /**
         * Devuelve verdadero si la pila no tiene elementos y falso en caso
         * contrario.
         */
        return this.tope == null;
    }
}
