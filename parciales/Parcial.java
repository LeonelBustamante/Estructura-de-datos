package parciales;

import jerarquicas.ArbolGen;

public class Parcial {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar(20, null);
        arbol.insertar(54, 20);
        arbol.insertar(13, 20);
        arbol.insertar(12, 13);
        arbol.insertar(15, 13);
        arbol.insertar(4, 54);
        arbol.insertar(27, 54);
        arbol.insertar(11, 54);
        arbol.insertar(17, 27);
        System.out.println(arbol);
        System.out.println(arbol.listarEntreNiveles(1, 2));
    }
}
