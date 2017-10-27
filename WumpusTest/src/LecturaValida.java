import java.util.InputMismatchException;
import java.util.Scanner;

public class LecturaValida {
	/**
     * Lee desde un Scanner y devuelve un valor entero en el intervalo [lInferior..lSuperior] tal que
     * Integer.MIN_VALUE<=lInferior y lSuperior<=Integer.MAX_VALUE.
     * 
     * @param tec Scanner, para lectura desde teclado.
     * @param mensaje String, para petición de valor.
     * @param lInferior int, que indica el límite inferior del intervalo de lectura.
     * @param lSuperior int, que indica el límite superior del intervalo de lectura.
     * @return int, entero leído en el intervalo [lInferior..lSuperior].
     */    
    public static int leerEntero(Scanner tec, String mensaje, int lInferior, int lSuperior) {
        int res = 0;
        boolean hayError = true;
        do {
            try {
                System.out.print(mensaje);
                res = tec.nextInt(); 
                if (res < lInferior || res > lSuperior) { 
                    String msg = "Al leer: " + res + " no está en [" + lInferior + "..." + lSuperior + "]";
                    throw new IllegalArgumentException(msg);
                }
                hayError = false;
            } catch (InputMismatchException e) {
                System.out.println("Asegúrate de introducir un entero válido! Inténtalo de nuevo...");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Inténtalo de nuevo...");
            } finally {
                tec.nextLine();
            }
        } while (hayError);
        return res;
    }  
}
