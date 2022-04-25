package testsPropios.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class TestCadenas {

    /**
     * En una clase TestCadenas, que utilice los TDA Lista, Pila y Cola vistos
     * en la materia, para guardar elementos de tipo CHAR, implementar el método
     * generar (Cola c1) que recibe por parámetro una estructura cola c1 que
     * tiene el siguiente formato: a1#a2#a3#….#an, donde cada ai en una sucesión
     * de letras mayúsculas y a partir de c1 debe generar como salida otra Cola
     * de la forma: a1a1´a1#a2a2´a2#….#anan´an donde cada ai´ es la secuencia de
     * letras mayúsculas ai pero invertida. Ejemplo.: Si c1 es : AB#C#DEF ,
     * entonces la operación generar devolverá una Cola con el siguiente
     * formato: ABBAAB#CCC#DEFFEDDEF.
     */
    public static void main(String[] args) {
        String cadena = "AB#C#DEF";
        Cola cola = new Cola();
        cadena = cadena.toUpperCase();
        for (int i = 0; i < cadena.length(); i++) {
            cola.poner(cadena.charAt(i));
        }
        System.out.println("generarCola:" + generar(cola));
    }

    public static Cola generar(Cola c1) {
        Cola res = new Cola();
        if (!c1.esVacia()) {
            Cola clon = c1.clone();
            while (!clon.esVacia()) {
                //Creamos estructuras necesarias
                Cola colaAux = new Cola();
                Cola colaAux2 = new Cola();
                Pila pilaAux = new Pila();
                while (!clon.esVacia() && !clon.obtenerFrente().equals('#')) {
                    //Cargamos estructuras auxiliares hasta encontrar separarador o terminar c1
                    colaAux.poner(clon.obtenerFrente());
                    pilaAux.apilar(clon.obtenerFrente());
                    colaAux2.poner(clon.obtenerFrente());
                    clon.sacar();
                }
                if (!clon.esVacia()) {
                    //Si no se termino sacamos el #
                    clon.sacar();
                }
                //Completamos <res> con los datos correctos
                while (!colaAux.esVacia()) {
                    res.poner(colaAux.obtenerFrente());
                    colaAux.sacar();
                }
                while (!pilaAux.esVacia()) {
                    res.poner(pilaAux.obtenerTope());
                    pilaAux.desapilar();
                }
                while (!colaAux2.esVacia()) {
                    res.poner(colaAux2.obtenerFrente());
                    colaAux2.sacar();
                }
                if (!clon.esVacia()) {
                    //Si no se termino coloco el elemento
                    res.poner('#');
                }
            }
        }
        return res;
    }
//
/*
     * PILA
     * -tope: Nodo
     * +apilar(Object x): boolean
     * +desapilar(): boolean
     * +esVacia(): boolean
     * +vaciar(): void
     * +obtenerTope(): Object
     * +clone(): Pila
     * +toString(): String
     */
 /*
     * COLA
     * -frente: Nodo
     * -fin: Nodo
     * +poner(Object x): boolean
     * +sacar(): boolean
     * +esVacia(): boolean
     * +vaciar(): void
     * +obtenerFrente(): Object
     * +clone(): Cola
     * +toString(): String
     */
 /*
     * LISTA
     * -cabecera: Nodo
     * -longitud: int
     * +insertar(Object x, int pos): boolean
     * +eliminar(int pos): boolean
     * +esVacia(): boolean
     * +vaciar(): void
     * +recuperar(int pos): Object
     * +localizar(Object x): int
     * +longitud(): int
     * +clone(): Lista
     * +toString(): String
     */
}
