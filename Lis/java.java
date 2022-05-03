package Lis;

import lineales.dinamicas.Lista;

public class java {

    public static void main(String[] args) {
        Lista l = new Lista();
        l.insertar(1, l.longitud() + 1);
        l.insertar(2, l.longitud() + 1);
        l.insertar(3, l.longitud() + 1);
        l.insertar(5, l.longitud() + 1);
        System.out.println(l);
        l.insertar(4, 4);
        System.out.println(l);
    }

}
