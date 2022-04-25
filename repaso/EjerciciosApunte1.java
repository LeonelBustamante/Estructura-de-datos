package repaso;

public class EjerciciosApunte1 {

    public static void main(String[] args) {
        String[] cadena = {"uno", "dos", "tres"};
        String string = "palabra";
        int[] arr = {10, 5, 3, 7, 11, 1};
        System.out.println("factorial: " + factorial(-2));
        System.out.println("factorial: " + factorial(0));
        System.out.println("factorial: " + factorial(4));
        System.out.println("buscarElem: " + buscarElem("uno", cadena, 0));
        System.out.println("buscarElem: " + buscarElem("tres", cadena, 0));
        System.out.println("buscarElem: " + buscarElem("cuatro", cadena, 0));
        System.out.println("fib: " + fib(0));
        System.out.println("fib: " + fib(1));
        System.out.println("fib: " + fib(5));
        System.out.println("primerPosLetra: " + primerPosLetra(string, 'h'));
        System.out.println("primerPosLetra: " + primerPosLetra(string, 'a'));
        System.out.println("ultimaPosLetra: " + ultimaPosLetra(string, 'a'));
        System.out.println("agregoCadenaInvertida: " + agregoCadenaInvertida(string));
        System.out.println("agregoCadenaInvertida: " + agregoCadenaInvertida(""));
        System.out.println("agregoCadenaInvertida: " + agregoCadenaInvertida("a"));
        System.out.println("menorQueElPromedio: ");
        menorQueElPromedio(arr);
    }

    public static int factorial(int n) {
        int res;
        if (n > 1) {
            res = factorial(n - 1) * n;
        } else {
            res = 1;
        }
        if (n < 0) {
            res = -1;
        }
        return res;
    }

    public static int buscarElem(String buscado, String[] arr, int pos) {
        //busca el elemento desde la posicion pos en adelante
        int salida;
        if (pos >= arr.length) {
            salida = -1;
        } else if (arr[pos].compareTo(buscado) == 0) {
            salida = pos;
        } else {
            salida = buscarElem(buscado, arr, pos + 1);
        }
        return salida;
    }

    public static int fib(int n) {
        int res;
        if (n <= 1) {
            res = n;
        } else {
            res = fib(n - 1) + fib(n - 2);
        }
        return res;
    }

    public static int primerPosLetra(String cadena, char buscado) {
        return primerPosLetra(cadena, buscado, 0);
    }

    private static int primerPosLetra(String cadena, char buscado, int pos) {
        int salida;
        if (pos >= cadena.length()) //control de longitud
        {
            salida = -1;
        } else if (cadena.charAt(pos) == buscado) //Si lo encuentro
        {
            salida = pos;
        } else //paso recursivo
        {
            salida = primerPosLetra(cadena, buscado, pos + 1);
        }
        return salida;
    }

    public static int ultimaPosLetra(String cadena, char buscado) {
        return ultimaPosLetra(cadena, buscado, cadena.length() - 1);
    }

    private static int ultimaPosLetra(String cadena, char buscado, int pos) {
        int salida;
        if (pos < 0) //control de longitud
        {
            salida = -1;
        } else if (cadena.charAt(pos) == buscado) //Si lo encuentro
        {
            salida = pos;
        } else //paso recursivo
        {
            salida = primerPosLetra(cadena, buscado, pos - 1);
        }
        return salida;
    }

    public static String agregoCadenaInvertida(String cadena) {

        return cadena + invertirCadena(cadena);
    }

    private static String invertirCadena(String cadena) {
        int longitud = cadena.length() - 1;
        String res = "";
        for (int i = longitud; i >= 0; i--) {
            res += cadena.charAt(i);
        }
        return res;
    }

    public static void menorQueElPromedio(int[] arr) {
        menorQueElPromedioAux(arr, "", 0.0, 0, 0);
    }

    private static double menorQueElPromedioAux(int[] arr, String cadena, double promedio, int suma, int pos) {
        if (pos >= arr.length) {
            promedio = suma / pos;
        } else {
            suma += arr[pos];
            promedio = menorQueElPromedioAux(arr, cadena, promedio, suma, pos + 1);
            if (arr[pos] < promedio) {
                System.out.print(arr[pos] + " ");
            }
        }
        return promedio;
    }

}
