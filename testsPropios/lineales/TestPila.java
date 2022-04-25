package testsPropios.lineales;

import lineales.dinamicas.Pila;

public class TestPila {

    public static void main(String[] args) {
        Pila p = new Pila();
        System.out.println("esVacia s/elementos: " + p.esVacia());
        System.out.println("obtenerTope s/elementos: " + p.obtenerTope());
        System.out.println("desapilar s/elementos: " + p.desapilar());
        System.out.println("toString s/elementos: " + p.toString());
        System.out.println("apilar: " + p.apilar(1));
        System.out.println("apilar: " + p.apilar(2));
        System.out.println("apilar: " + p.apilar(3));
        System.out.println("apilar: " + p.apilar(4));
        System.out.println("apilar: " + p.apilar(5));
        System.out.println("apilar espera false en estatica: " + p.apilar(6));
        System.out.println("toString despues de apilar: " + p.toString());
        System.out.println("esVacia c/elementos: " + p.esVacia());
        System.out.println("obtenerTope c/elementos: " + p.obtenerTope());
        System.out.println("desapilar c/elementos: " + p.desapilar());
        System.out.println("toString c/elementos: " + p.toString());
        System.out.println("vaciar");
        p.vaciar();
        System.out.println("esVacia s/elementos: " + p.esVacia());
        System.out.println("obtenerTope s/elementos: " + p.obtenerTope());
        System.out.println("desapilar s/elementos: " + p.desapilar());
        System.out.println("toString s/elementos: " + p.toString());
        System.out.println("apilar: " + p.apilar(3));
        System.out.println("apilar: " + p.apilar(4));
        System.out.println("apilar: " + p.apilar(5));

        Pila p2 = p.clone();
        System.out.println("toString c/elementos pila2: " + p2.toString());
        System.out.println("desapilar c/elementos pila2: " + p.desapilar());
        System.out.println("toString c/elementos pila2 despues de desapilar: " + p2.toString());
        System.out.println("toString c/elementos pila1 despues de desapilar en pila2: " + p.toString());

        Pila p3 = new Pila();
        System.out.println("toString s/elementos pila3: " + p3.toString());
        System.out.println("apilar: " + p3.apilar(1));
        System.out.println("apilar: " + p3.apilar(2));
        System.out.println("apilar: " + p3.apilar(3));
        System.out.println("apilar: " + p3.apilar(4));
        System.out.println("apilar: " + p3.apilar(5));
        System.out.println("toString c/elementos pila3: " + p3.toString());
    }

}
