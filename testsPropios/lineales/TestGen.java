package testsPropios.lineales;

import jerarquicas.dinamicas.ArbolGen;

public class TestGen {
    public static void main(String[] args) {
        ArbolGen a = new ArbolGen();
        System.out.println(a.listarPreorden());
        System.out.println(a.listarPosorden());
        a.insertar('1', null);
        a.insertar('2', 1);
        a.insertar('3', 1);
        a.insertar('4', 1);
        a.insertar('5', 2);
        a.insertar('6', 3);
        a.insertar('7', 2);
        System.out.println("TERMINADO");
        System.out.println(a.listarPreorden());
        System.out.println(a.listarPosorden());
    }

}
