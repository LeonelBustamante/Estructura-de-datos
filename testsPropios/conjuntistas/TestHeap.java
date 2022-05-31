package testsPropios.conjuntistas;

import conjuntistas.ArbolHeap;

public class TestHeap {

    public static void main(String[] args) {
        ArbolHeap a = new ArbolHeap();
        System.out.println("toString a:\n" + a);
        System.out.println("esVacio: " + a.esVacio());
        System.out.println("recuperarCima: " + a.recuperarCima());
        System.out.println("elimiarCima: " + a.eliminarCima());
        ArbolHeap b = a.clon();
        System.out.println("toString b:\n" + b);
        a.insertar(1);
        a.insertar(2);
        a.insertar(3);
        a.insertar(4);
        a.insertar(5);
        a.insertar(6);
        a.insertar(7);
        b = a.clon();
        System.out.println("-----------------------------------------------------");
        System.out.println("toString a:\n" + a);
        System.out.println("esVacio: " + a.esVacio());
        System.out.println("recuperarCima: " + a.recuperarCima());
        System.out.println("elimiarCima: " + a.eliminarCima());
        System.out.println("toString a:\n" + a);
        System.out.println("toString b:\n" + b);
    }
}