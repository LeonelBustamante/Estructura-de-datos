package testsPropios.jerarquicas;

import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;

public class TestGen {

    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen(); // arbol vacio
        ArbolGen arbol0 = new ArbolGen(); // arbol solo raiz
        ArbolGen arbol1 = new ArbolGen(); // arbol con hojas
        ArbolGen arbol2 = new ArbolGen(); // arbol con hojas y ramas
        // arbol 0
        arbol0.insertar(1, null);
        // arbol 1
        arbol1.insertar(1, null);
        arbol1.insertar(5, 1);
        arbol1.insertar(4, 1);
        arbol1.insertar(3, 1);
        arbol1.insertar(2, 1);
        // arbol 2
        arbol2.insertar(1, null);
        arbol2.insertar(5, 1);
        arbol2.insertar(4, 1);
        arbol2.insertar(3, 1);
        arbol2.insertar(2, 1);
        arbol2.insertar(7, 3);
        arbol2.insertar(6, 3);
        arbol2.insertar(8, 7);

        Lista lista = new Lista(); // lista vacia
        Lista lista0 = arbol2.frontera(); // lista todos frontera
        Lista lista1 = new Lista(); // lista algunos frontera
        Lista lista2 = new Lista(); // lista elementos surtidos
        // lista 1
        lista1.insertar(2, lista1.longitud() + 1);
        lista1.insertar(4, lista1.longitud() + 1);
        // lista 2
        lista2.insertar(2, lista2.longitud() + 1);
        lista2.insertar(6, lista2.longitud() + 1);
        lista2.insertar(3, lista2.longitud() + 1);
        lista2.insertar(8, lista2.longitud() + 1);
        lista2.insertar(4, lista2.longitud() + 1);
        lista2.insertar(5, lista2.longitud() + 1);

        System.out.println("arbol \n" + arbol + "\n");
        System.out.println("arbol0 \n" + arbol0 + "\n");
        System.out.println("arbol1 \n" + arbol1 + "\n");
        System.out.println("arbol2 \n" + arbol2 + "\n");
        System.out.println("lista \n" + lista + "\n");
        System.out.println("lista0 \n" + lista0 + "\n");
        System.out.println("lista1 \n" + lista1 + "\n");
        System.out.println("lista2 \n" + lista2 + "\n");
        System.out.println("-----------------------------------------------------");
        System.out.println("Arbol vacio deberia dar false en todos los casos");
        System.out.println("arbol-l :" + arbol.sonFrontera(lista));
        System.out.println("arbol-l0 :" + arbol.sonFrontera(lista0));
        System.out.println("arbol-l1 :" + arbol.sonFrontera(lista1));
        System.out.println("arbol-l2 :" + arbol.sonFrontera(lista2));
        System.out.println();
        System.out.println("Arbol con raiz deberia dar false en todos los casos");
        System.out.println("arbol0-l :" + arbol0.sonFrontera(lista));
        System.out.println("arbol0-l0 :" + arbol0.sonFrontera(lista0));
        System.out.println("arbol0-l1 :" + arbol0.sonFrontera(lista1));
        System.out.println("arbol0-l2 :" + arbol0.sonFrontera(lista2));
        System.out.println();
        System.out.println("Arbol con hojas deberia dar todo false menos lista1");
        System.out.println("arbol1-l :" + arbol1.sonFrontera(lista));
        System.out.println("arbol1-l0 :" + arbol1.sonFrontera(lista0));
        System.out.println("arbol1-l1 :" + arbol1.sonFrontera(lista1));
        System.out.println("arbol1-l2 :" + arbol1.sonFrontera(lista2));
        System.out.println();
        System.out.println("arbol2-l :" + arbol2.sonFrontera(lista));
        System.out.println("arbol2-l0 :" + arbol2.sonFrontera(lista0));
        System.out.println("arbol2-l1 :" + arbol2.sonFrontera(lista1));
        System.out.println("arbol2-l2 :" + arbol2.sonFrontera(lista2));
    }
}
