package testsPropios.conjuntistas;

import java.util.Random;

import conjuntistas.TablaHash;

public class TestHash {
    public static void main(String[] args) {
        // test clase hash
        TablaHash tabla = new TablaHash();
        for (int i = 0; i < 100; i++) {
            tabla.insertar(new Random().nextInt(100));
        }
        System.out.println(tabla);

    }

}
