package testsPropios.conjuntistas;

import conjuntistas.TablaHash;
import java.util.Random;

public class TestHash {

    public static void main(String[] args) {
        // test clase hash
        TablaHash tabla = new TablaHash();
        int x;
        for (int i = 0; i < 10; i++) {
            x = new Random().nextInt(100);
            System.out.println(x);
            tabla.insertar(x);
        }
        System.out.println(tabla.listar());
    }
}
