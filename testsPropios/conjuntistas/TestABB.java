package testsPropios.conjuntistas;

import conjuntistas.ArbolBB;

public class TestABB {
    public static void main(String[] args) {
        ArbolBB a = new ArbolBB();
        System.out.println("toString a:\n" + a);
        System.out.println("esVacio: " + a.esVacio());
        System.out.println("listar: " + a.listar());
        System.out.println("listar rango: " + a.listarRango(1, 3));
        System.out.println("pertenece: " + a.pertenece(1));
        System.out.println("minimo: " + a.minimoElem());
        System.out.println("maximo: " + a.maximoElem());
        a.insertar(5);
        a.insertar(2);
        a.insertar(3);
        a.insertar(10);
        a.insertar(8);
        a.insertar(9);
        a.insertar(7);
        System.out.println("-----------------------------------------------------");
        System.out.println("toString a:\n" + a);
        System.out.println("esVacio: " + a.esVacio());
        System.out.println("listar: " + a.listar());
        System.out.println("listar rango: " + a.listarRango(3, 8));
        System.out.println("pertenece: " + a.pertenece(9));
        System.out.println("no pertenece: " + a.pertenece(1));
        System.out.println("minimo: " + a.minimoElem());
        System.out.println("maximo: " + a.maximoElem());
    }
}
