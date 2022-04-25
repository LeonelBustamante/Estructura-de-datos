package testsPropios.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;
import util.TecladoIn;

public class MixLineales {

    public static void main(String[] args) {
        Cola cola = new Cola();
        System.out.println("Ingrese la opcion que le guste:");
        System.out.println("1.  AB$C$DEF");
        System.out.println("2.  ABCDEF");
        System.out.println("3.  $F");
        System.out.println("4.  {VACIA}");
        System.out.print("OPCION:");
        switch (TecladoIn.readLineInt()) {
            case 1 -> {
                cola.poner('A');
                cola.poner('B');
                cola.poner('$');
                cola.poner('C');
                cola.poner('$');
                cola.poner('D');
                cola.poner('E');
                cola.poner('F');
                System.out.println("ORGINAL: " + cola);
                System.out.println(generarOtraCola(cola));
            }
            case 2 -> {
                cola.poner('A');
                cola.poner('B');
                cola.poner('C');
                cola.poner('D');
                cola.poner('E');
                cola.poner('F');
                System.out.println("ORGINAL: " + cola);
                System.out.println(generarOtraCola(cola));
            }
            case 3 -> {
                cola.poner('$');
                cola.poner('F');
                System.out.println("ORGINAL: " + cola);
                System.out.println(generarOtraCola(cola));
            }
            case 4 -> {
                generarOtraCola(cola);
                System.out.println("ORGINAL: " + cola);
                System.out.println(generarOtraCola(cola));
            }
            default ->
                System.out.println("No se ingreso una opcion correcta");
        }
    }

    private static Cola generarOtraCola(Cola c) {
        /*
         * Metodo que recibe una cola de caracteres en mayuscula con el formato
         * a1$a2$...an y se retorna a1a1*$a2a2*$...anan* con an* siendo an de manera
         * invertida
         * EJEMPLO: AB$C$DEF ==> ABBA$CC$DEFFED
         */
        Cola colaRetornada = new Cola();
        if (!c.esVacia()) // Si la cola por parametro no esta vacia se procede
        {
            // Se crean estructuras auxiliares
            Cola clon = c.clone();// Cola que se rompe para no alterar la original
            Cola colaAux = new Cola();// Cola para colocar orden correcto
            Pila pilaAux = new Pila();// Pila para colocar orden inverso
            while (!clon.esVacia())// Hasta terminar la cola
            {
                while (!clon.esVacia() && (char) clon.obtenerFrente() != '$') // Cargo las est auxiliares
                {
                    colaAux.poner(clon.obtenerFrente());
                    pilaAux.apilar(clon.obtenerFrente());
                    clon.sacar();
                }
                while (!colaAux.esVacia()) // Colocando en orden correcto
                {
                    colaRetornada.poner(colaAux.obtenerFrente());
                    colaAux.sacar();
                }
                while (!pilaAux.esVacia()) // Colocando en orden inverso
                {
                    colaRetornada.poner(pilaAux.obtenerTope());
                    pilaAux.desapilar();
                }
                if (!clon.esVacia()) // Si se termina la cola no coloco signo
                {
                    colaRetornada.poner('$');
                    clon.sacar();
                }
            }
        }
        return colaRetornada;
    }

}
