package conjuntistas;

public class Funciones {

    public static int hash(Object elemento) {
        int res = 0;
        if (elemento instanceof Integer) {
            res = hashEnteros((int) elemento);
        } else {
            res = hashString((String) elemento);
        }
        return res;
    }

    private static int hashEnteros(int numero) {
        // calcula un hash de un numero recibido por parametro
        return sumaFactoresPrimos(numero);
    }

    private static int hashString(String cadena) {
        // calcula un hash de una cadena recibida por parametro
        int numero = 0;
        for (int i = 0; i < cadena.length(); i++) {
            numero += cadena.charAt(i);
        }
        return sumaFactoresPrimos(numero);
    }

    private static int sumaFactoresPrimos(int numero) {
        int x = 2, contador = 0;
        while (numero != 1) {
            if (numero % x == 0) {
                contador += x;
                numero = numero / x;
            } else
                x = x + 1;
        }
        return contador;
    }

}
