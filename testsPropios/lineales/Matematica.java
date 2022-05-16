package testsPropios.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class Matematica {

    /*
     * En la clase Matematica, que utiliza a los TDA Lista, Pila y Cola vistos
     * en la materia para guardar elementos tipo CHAR que representan una
     * expresión matemática, desarrollar el método verificarBalanceo (Cola q)
     * que recibe por parámetro una cola con una expresión matemática y
     * verifique que los paréntesis, corchetes y llaves estén correctamente
     * balanceados. Debe usar como estructura auxiliar alguno de los TDA
     * lineales vistos, el que considere más apropiado.
     * Ejemplos:
     * Si q es ← { 5 * + [ 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver
     * TRUE
     * Si q es ← { 5 + 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver FALSE
     */
    public static void main(String[] args) {
        // String cadena = "{5*+[8*9-(4/2)+7]-1}";
        String cadena2 = "{5+8*9-(4/2)+7]-1}";
        Cola cola = new Cola();
        for (int i = 0; i < cadena2.length(); i++) {
            cola.poner(cadena2.charAt(i));
        }
        System.out.println("verifica el balanceo:" + verificarBalanceo(cola));
    }

    public static boolean verificarBalanceo(Cola cola) {
        /**
         * precondicion: <cola> debe ser verificada que sea matematicamente
         * correcta no debe haber cosas de la forma "+)" ya que el programa no
         * corroborara que se cumpla matematicamente la cola
         *
         * Eficiencia: El metodo es de Orden n ya que para verificarlo se
         * recorre completamente la cola
         */
        boolean res = false;

        if (!cola.esVacia()) {
            Cola clon = cola.clone();
            Pila pila = new Pila();
            char charCola, charPila;
            boolean bandera = true;

            while (!clon.esVacia() && bandera) {
                charCola = (char) clon.obtenerFrente();
                System.out.println(pila);
                System.out.println(charCola);
                if (esSimbolo(charCola)) {
                    System.out.println("Chequeando simbolo");
                    // Solamente chequeo balanceo de caracteres { [ ( ) ] }
                    if (esSimboloApertura(charCola)) {
                        // Si es simbolo de apertura apilo
                        pila.apilar(charCola);
                    } else if (!pila.esVacia()) {
                        // Si no es de apertura entonces verifico que
                        // no este vacia la pila y tomo el tope para comparar con
                        // complemento de charCola
                        System.out.println("Simbolo inverso");
                        charPila = (char) pila.obtenerTope();
                        switch (charCola) {
                            case '}' -> bandera = charPila == '{';
                            case ']' -> bandera = charPila == '[';
                            case ')' -> bandera = charPila == '(';
                        }
                        if (bandera) {
                            // Si esta el elemento correcto entonces desapilar
                            pila.desapilar();
                        }
                    } else {
                        // Se esperaba elemento en Pila
                        bandera = false;
                    } // Fin consulta simbolo apertura
                } // Fin if es simbolo
                clon.sacar();
            } // Fin while recorre clon
            if (bandera && pila.esVacia()) {
                res = true;
            }
        } // Fin if cola es vacia
        return res;
    }

    private static boolean esSimbolo(char c) {
        return c == '{' || c == '[' || c == '(' || c == ')' || c == ']' || c == '}';
    }

    private static boolean esSimboloApertura(char c) {
        return c == '{' || c == '[' || c == '(';
    }

}
