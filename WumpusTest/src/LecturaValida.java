import java.util.InputMismatchException;
import java.util.Scanner;

public class LecturaValida {
	/**
     * Lee desde un Scanner y devuelve un valor entero en el intervalo [lInferior..lSuperior] tal que
     * Integer.MIN_VALUE<=lInferior y lSuperior<=Integer.MAX_VALUE.
     * 
     * @param tec Scanner, para lectura desde teclado.
     * @param mensaje String, para petici�n de valor.
     * @param lInferior int, que indica el l�mite inferior del intervalo de lectura.
     * @param lSuperior int, que indica el l�mite superior del intervalo de lectura.
     * @return int, entero le�do en el intervalo [lInferior..lSuperior].
     */    
    public static int leerEntero(Scanner tec, String mensaje, int lInferior, int lSuperior) {
        int res = 0;
        boolean hayError = true;
        do {
            try {
                System.out.print(mensaje);
                res = tec.nextInt(); 
                if (res < lInferior || res > lSuperior) { 
                    String msg = "Al leer: " + res + " no est� en [" + lInferior + "..." + lSuperior + "]";
                    throw new IllegalArgumentException(msg);
                }
                hayError = false;
            } catch (InputMismatchException e) {
                System.out.println("Aseg�rate de introducir un entero v�lido! Int�ntalo de nuevo...");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Int�ntalo de nuevo...");
            } finally {
                tec.nextLine();
            }
        } while (hayError);
        return res;
    }  
}
