package testsPropios.jerarquicas;

import conjuntistas.ArbolBB;

public class TestABB {
    public static void main(String[] args) {
        ArbolBB a = new ArbolBB();
        a.insertar(20);
        a.insertar(14);
        a.insertar(44);
        a.insertar(6);
        a.insertar(18);
        a.insertar(22);
        a.insertar(56);
        a.insertar(1);
        a.insertar(16);
        a.insertar(19);
        System.out.println(a);
        a.eliminarHojasEnRango(10, 30);
        System.out.println("----------------------------------------------------");
        System.out.println(a);
    }

}
