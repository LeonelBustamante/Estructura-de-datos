package testProfes.lineales;

import lineales.dinamicas.*;

public class PruebaLista {

    public static void main(String[] args) {
        Lista lista1 = new Lista();
        Lista lista2 = new Lista();
        Lista lista3 = new Lista();

        lista1.insertar(2, 1);
        lista1.insertar(4, 2);
        lista1.insertar(6, 3);

        lista2.insertar(5, 1);
        lista2.insertar(1, 2);
        lista2.insertar(6, 3);
        lista2.insertar(7, 4);

        lista3.insertar(9, 1);
        lista3.insertar(6, 2);
        lista3.insertar(5, 3);
        lista3.insertar(0, 4);
        lista3.insertar(9, 5);
        lista3.insertar(6, 6);
        lista3.insertar(5, 7);
        lista3.insertar(0, 8);
        lista3.insertar(5, 9);
        lista3.insertar(6, 10);
        lista3.insertar(9, 11);
        System.out.println("lista1: " + lista1);
        System.out.println("lista2: " + lista2);
        System.out.println("lista3: " + lista3);
        System.out.println("Concatenar: " + concatenar(lista1, lista2));
        System.out.println("Comprobar: " + comprobar(lista3));
        System.out.println("Invertir: " + invertir(lista1));
    }

    private static boolean comprobar(Lista lista1) {
        boolean res = false;
        if (!lista1.esVacia()) {
            Lista clon = lista1.clone(); // Lista que voy a romper
            Cola colaAux = new Cola(); // Cola para corroborar orden original
            Pila pilaAux = new Pila(); // Pila para corroborar inversa
            while (!clon.esVacia() && (int) clon.recuperar(1) != 0) {
                //Cargaremos las estructuras para verificar la informacion
                pilaAux.apilar(clon.recuperar(1));
                colaAux.poner(clon.recuperar(1));
                clon.eliminar(1);
            }
            if (!clon.esVacia() && (int) clon.recuperar(1) == 0) {
                clon.eliminar(1);//Quitamos el primer 0

                while (!colaAux.esVacia() && !res && !clon.esVacia()) {
                    //Debemos verificar que los elementos tengan el orden correcto
                    //Que colaAux se vacie completamente 
                    //Que la Lista clon no se termine
                    res = colaAux.obtenerFrente() != clon.recuperar(1);
                    colaAux.sacar();
                    clon.eliminar(1);
                }
                if (!res && !clon.esVacia() && (int) clon.recuperar(1) == 0) {
                    //Si sigue siendo falso, no se vacio la lista y el siguiente elemento es un 0
                    while (!pilaAux.esVacia() && !res && !clon.esVacia()) {
                        //Debemos verificar que los elementos tengan el orden inverso
                        //Que pilaAux se vacie completamente 
                        //Que la Lista clon no se termine
                        res = colaAux.obtenerFrente() != clon.recuperar(1);
                        colaAux.sacar();
                        clon.eliminar(1);
                    }
                    if (clon.esVacia()) {
                        // Si ya no quedan mas elementos entonces se cumple los requisitos de la cadena
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    private static Lista concatenar(Lista lista1, Lista lista2) {
        /**
         * Se devuelve una nueva lista con los elementos de ambas listas
         * concatenados
         */
        Lista listaRetornada = lista1.clone();
        int corte = lista1.longitud() + lista2.longitud();
        int i = lista1.longitud() + 1;
        int j = 1;
        while (i <= corte) {
            listaRetornada.insertar(lista2.recuperar(j), i);
            i++;
            j++;
        }
        return listaRetornada;
    }

    private static Lista invertir(Lista lista1) {
        Lista listaRetornada = new Lista();
        if (!lista1.esVacia()) {
            Pila pilaAux = new Pila(); // Usada para invertir los elementos
            Lista clon = lista1.clone(); // Copia de la lista original
            while (!clon.esVacia()) {
                pilaAux.apilar(clon.recuperar(1));
                clon.eliminar(1);
            }
            int i = 1;
            while (!pilaAux.esVacia()) {
                listaRetornada.insertar(pilaAux.obtenerTope(), i);
                pilaAux.desapilar();
                i++;
            }
        }
        return listaRetornada;
    }
}
