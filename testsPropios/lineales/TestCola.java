package testsPropios.lineales;

import lineales.dinamicas.Cola;

public class TestCola {

    public static void main(String[] args) {
        Cola cola = new Cola();
        System.out.println("esVacia s/elementos: " + cola.esVacia());
        System.out.println("obtenerFrente s/elementos: " + cola.obtenerFrente());
        System.out.println("desapilar s/elementos: " + cola.sacar());
        System.out.println("toString s/elementos: " + cola.toString());
        System.out.println("poner: " + cola.poner(1));
        System.out.println("poner: " + cola.poner(2));
        System.out.println("poner: " + cola.poner(3));
        System.out.println("poner: " + cola.poner(4));
        System.out.println("poner: " + cola.poner(5));
        System.out.println("poner espera false en estatica: " + cola.poner(6));
        System.out.println("toString despues de poner: " + cola.toString());
        System.out.println("esVacia c/elementos: " + cola.esVacia());
        System.out.println("obtenerFrente c/elementos: " + cola.obtenerFrente());
        System.out.println("sacar c/elementos: " + cola.sacar());
        System.out.println("toString c/elementos: " + cola.toString());
        System.out.println("vaciar");
        cola.vaciar();
        System.out.println("esVacia s/elementos: " + cola.esVacia());
        System.out.println("obtenerFrente s/elementos: " + cola.obtenerFrente());
        System.out.println("sacar s/elementos: " + cola.sacar());
        System.out.println("toString s/elementos: " + cola.toString());
        System.out.println("poner: " + cola.poner(3));
        System.out.println("poner: " + cola.poner(4));
        System.out.println("poner: " + cola.poner(5));

        Cola cola2 = cola.clone();
        System.out.println("toString c/elementos cola2: " + cola2.toString());
        System.out.println("desapilar c/elementos cola2: " + cola.sacar());
        System.out.println("toString c/elementos cola2 despues de sacar: " + cola2.toString());
        System.out.println("toString c/elementos cola1 despues de sacar en cola2: " + cola.toString());

        Cola cola3 = new Cola();
        System.out.println("toString s/elementos cola3: " + cola3.toString());
        System.out.println("poner: " + cola3.poner(1));
        System.out.println("poner: " + cola3.poner(1));
        System.out.println("poner: " + cola3.poner(1));
        System.out.println("toString c/elementos cola3: " + cola3.toString());
    }

}
