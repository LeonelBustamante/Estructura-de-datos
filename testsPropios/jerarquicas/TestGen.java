package testsPropios.jerarquicas;

import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;

public class TestGen {

    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen(); // arbol vacio
        ArbolGen arbol0 = new ArbolGen(); // arbol solo raiz
        ArbolGen arbol1 = new ArbolGen(); // arbol con hojas
        ArbolGen arbol2 = new ArbolGen(); // arbol con hojas y ramas
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
        System.out.println(">>arbol \n" + arbol + "\n");
        System.out.println(">>esVacio(): " + arbol.esVacio());
        System.out.println(">>insertar(1, null): " + arbol.insertar(1, null));
        System.out.println(">>arbol 1 elemento: \n" + arbol + "\n");
        System.out.println(">>insertar(2, 1): " + arbol.insertar(2, 1));
        System.out.println(">>insertar(3, 1): " + arbol.insertar(3, 1));
        System.out.println(">>insertar(4, 3): " + arbol.insertar(4, 3));
        System.out.println(">>arbol 1 c/hijo 2,3 Y 3 c/hijo 4: \n" + arbol + "\n");
        System.out.println(">>pertenece(1): " + arbol.pertenece(1));
        System.out.println(">>pertenece(2): " + arbol.pertenece(2));
        System.out.println(">>pertenece(3): " + arbol.pertenece(3));
        System.out.println(">>pertenece(4): " + arbol.pertenece(4));
        System.out.println(">>pertenece(5) ESPERA FALLA: " + arbol.pertenece(5));
        System.out.println(">>esVacio(): ESPERA FALLA " + arbol.esVacio());
        System.out.println(">>padre(1): ESPERA NULL " + arbol.padre(1));
        System.out.println(">>padre(2): ESPERA 1 " + arbol.padre(2));
        System.out.println(">>padre(3): ESPERA 1 " + arbol.padre(3));
        System.out.println(">>padre(4): ESPERA 3 " + arbol.padre(4));
        System.out.println(">>padre(5): ESPERA FALLA " + arbol.padre(5));
        System.out.println(">>altura(): ESPERA 2 " + arbol.altura());
        System.out.println(">>nivel(1): ESPERA 0 " + arbol.nivel(1));
        System.out.println(">>nivel(2): ESPERA 1 " + arbol.nivel(2));
        System.out.println(">>nivel(3): ESPERA 1 " + arbol.nivel(3));
        System.out.println(">>nivel(4): ESPERA 2 " + arbol.nivel(4));
        System.out.println(">>nivel(5): ESPERA -1 " + arbol.nivel(5));
        System.out.println(">>ancestro(1): ESPERA LISTA VACIA " + arbol.ancestros(1));
        System.out.println(">>ancestro(2): ESPERA [1] " + arbol.ancestros(2));
        System.out.println(">>ancestro(3): ESPERA [1] " + arbol.ancestros(3));
        System.out.println(">>ancestro(4): ESPERA [1,3] " + arbol.ancestros(4));
        System.out.println(">>ancestro(5): ESPERA FALLA " + arbol.ancestros(5));
        arbol0 = arbol.clone();
        System.out.println(">>arbol0=arbol1.clone(): \n" + arbol0 + "\n");
        System.out.println(">>insertar(5, 3): " + arbol0.insertar(5, 3));
        System.out.println(">>arbol0 TIENE QUE TENER A 5 COMO HIJO DE 3 \n" + arbol0 + "\n");
        System.out.println(">>arbol\n" + arbol + "\n");
        System.out.println(">>listarPreorden(): ESPERA [1,3,4,2] " + arbol.listarPreorden());
        System.out.println(">>listarInorden(): ESPERA [4,3,1,2] " + arbol.listarInorden());
        System.out.println(">>listarPosorden(): ESPERA [4,3,2,1] " + arbol.listarPosorden());
        System.out.println(">>listarPorNiveles(): ESPERA [1,3,2,4] " + arbol.listarPorNiveles());
        System.out.println(">>grado(): ESPERA 2 " + arbol.grado());
        System.out.println(">>gradoSubarbol(1): ESPERA 2 " + arbol.gradoSubarbol(1));
        System.out.println(">>gradoSubarbol(2): ESPERA 0 " + arbol.gradoSubarbol(2));
        System.out.println(">>gradoSubarbol(3): ESPERA 1 " + arbol.gradoSubarbol(3));
        System.out.println(">>gradoSubarbol(4): ESPERA 0 " + arbol.gradoSubarbol(4));
        System.out.println(">>gradoSubarbol(5): ESPERA -1 " + arbol.gradoSubarbol(5));
        System.out.println(">>frontera(): ESPERA [4,2] " + arbol.frontera());
        Lista frontera = arbol.frontera();
        Lista fronteraMal = arbol.frontera();
        fronteraMal.insertar(9, fronteraMal.longitud() + 1);
        System.out.println(">>sonFrontera([2,4]): ESPERA TRUE " + arbol.sonFrontera(frontera));
        System.out.println(">>sonFrontera([2,4,9]): ESPERA FALSE " + arbol.sonFrontera(fronteraMal));
        Lista listaJustificaAltura = arbol.listaQueJustificaLaAltura();
        System.out.println(">>listaQueJustificaAltura(): ESPERA [1,3,4] " + listaJustificaAltura);
        System.out.println(">>listarHastaNivel(0): ESPERA [1] " + arbol.listarHastaNivel(0));
        System.out.println(">>listarHastaNivel(1): ESPERA [1,3,2] " + arbol.listarHastaNivel(1));
        System.out.println(">>listarHastaNivel(2): ESPERA [1,3,4,2] " + arbol.listarHastaNivel(2));
        arbol.vaciar();
        System.out.println(">>arbol vacio\n" + arbol + "\n");
    }
}
