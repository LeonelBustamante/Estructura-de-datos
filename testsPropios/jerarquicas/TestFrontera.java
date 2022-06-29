package testsPropios.jerarquicas;

import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

public class TestFrontera {

    public static void main(String[] args) {
        System.out.println("**** COMIENZA EJECUCION ****");
        ArbolGen a = new ArbolGen();
        a.insertar(1, null);
        a.insertar(2, 1);
        a.insertar(21, 2);
        a.insertar(22, 2);
        a.insertar(23, 2);
        a.insertar(231, 23);
        a.insertar(232, 23);
        a.insertar(24, 2);
        a.insertar(3, 1);
        a.insertar(31, 3);
        a.insertar(311, 31);
        a.insertar(4, 1);
        a.insertar(41, 4);

        ArbolGen b = new ArbolGen();

        ArbolGen c = new ArbolGen();
        c.insertar(1, null);

        System.out.println("ARBOL CARGADO: \n" + a);
        System.out.println("ARBOL VACIO: \n" + b);
        System.out.println("ARBOL CARGADO 1 ELEMENTO: \n" + c);

        System.out.println("ARBOL CARGADO: \n");
        pruebas(a);
        System.out.println("");
        System.out.println("ARBOL VACIO: \n");
        pruebas(b);
        System.out.println("");
        System.out.println("ARBOL CARGADO 1 ELEMENTO: \n");
        pruebas(c);
        System.out.println("");
    }

    public static void pruebas(ArbolGen a) {
        Lista elementoDeMas = new Lista();
        elementoDeMas.insertar(41, elementoDeMas.longitud() + 1);
        elementoDeMas.insertar(311, elementoDeMas.longitud() + 1);
        elementoDeMas.insertar(24, elementoDeMas.longitud() + 1);
        elementoDeMas.insertar(231, elementoDeMas.longitud() + 1);
        elementoDeMas.insertar(232, elementoDeMas.longitud() + 1);
        elementoDeMas.insertar(22, elementoDeMas.longitud() + 1);
        elementoDeMas.insertar(21, elementoDeMas.longitud() + 1);
        elementoDeMas.insertar(23, elementoDeMas.longitud() + 1);

        System.out.println("SonFrontera +elementos \t\t\t" + elementoDeMas + "-> " + a.sonFrontera(elementoDeMas));

        Lista frontera = new Lista();
        frontera.insertar(41, frontera.longitud() + 1);
        frontera.insertar(311, frontera.longitud() + 1);
        frontera.insertar(24, frontera.longitud() + 1);
        frontera.insertar(231, frontera.longitud() + 1);
        frontera.insertar(232, frontera.longitud() + 1);
        frontera.insertar(22, frontera.longitud() + 1);
        frontera.insertar(21, frontera.longitud() + 1);

        System.out.println("SonFrontera \t\t\t\t" + frontera + "-> " + a.sonFrontera(frontera));

        Lista menosElementos = new Lista();
        menosElementos.insertar(41, menosElementos.longitud() + 1);
        menosElementos.insertar(311, menosElementos.longitud() + 1);
        menosElementos.insertar(24, menosElementos.longitud() + 1);
        menosElementos.insertar(231, menosElementos.longitud() + 1);
        menosElementos.insertar(232, menosElementos.longitud() + 1);
        menosElementos.insertar(22, menosElementos.longitud() + 1);

        System.out.println("SonFrontera -elementos \t\t\t" + menosElementos + "-> " + a.sonFrontera(menosElementos));

        Lista vacia = new Lista();

        System.out.println("SonFrontera vacia " + vacia + "-> " + a.sonFrontera(vacia));
    }
}
