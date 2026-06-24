package inventario;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

    private static Scanner sc = new Scanner(System.in);

    public static Scanner getScanner() {
        return sc;
    }

    public static boolean validarNombre(String texto) {

        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");

        Matcher matcher = patron.matcher(texto);

        if (!matcher.matches()) {
            System.out.println("Solo se permiten letras.");
        }

        return matcher.matches();
    }

    public static int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Integer.parseInt(sc.nextLine());

            } catch (Exception e) {

                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    public static double leerDouble(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Double.parseDouble(sc.nextLine());

            } catch (Exception e) {

                System.out.println("Ingrese un decimal valido.");
            }
        }
    }
}