package controller;

import java.util.Scanner;
import business.service.JuegoProvider;
import globals.Constantes;

/**
 * @author samuel
 *
 */
public class App
{
    static Scanner ordenUsuario = new Scanner(System.in);

    static JuegoProvider juegoProvider = new JuegoProvider();

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // Menu Principal
        int opcionUsuario;
        do
        {
            opcionUsuario = verMenuPrincipal();

            switch (opcionUsuario)
            {
                // Configuración
                case 1:

                    verMenuConfiguracion();
                    break;

                // Jugar
                case 2:
                    verMenuJugar();
                    break;

                // Salir
                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Si desea salir escoja la opción 3.");
                    break;
            }
        }
        while (opcionUsuario == Constantes.PRINCIPAL_CONFIGURACION || opcionUsuario == Constantes.SALIR_PRINCIPAL);

    }
    
    /**
     * 
     * Ver menu Principal
     *     Configuración
     *     Jugar
     *     Salir
     * 
     * */
    private static int verMenuPrincipal()
    {
        System.out.println("============================= Menú Principal ===========================");
        System.out.println("                                  1) Configuración.");
        System.out.println("                                  2) Jugar.");
        System.out.println("                                  3) Salir.");
        System.out.println("========================================================================");

        return ordenUsuario.nextInt();
    }
    
    /**
     * 
     * Ver menu Configuración
     *     Tamaño Tablero.
     *     Número de flechas
     *     Número de pozos
     *     Salir
     * 
     * */
    private static void verMenuConfiguracion()
    {
        int opcionConfiguracion;
        do
        {
            System.out.println("");
            System.out.println("========================== Menú Configuración ======================");
            System.out.println("                                1) Tamaño de tablero.");
            System.out.println("                                2) Número de pozos.");
            System.out.println("                                3) Número de flechas.");
            System.out.println("                                4) Salir.");
            System.out.println("====================================================================");

            opcionConfiguracion = ordenUsuario.nextInt();

            juegoProvider.menuConfiguracion(opcionConfiguracion);
        }
        while (opcionConfiguracion != Constantes.SALIR_CONFIG);
    }

    /**
     * 
     * Ver menu Jugar.
     *      Movimientos.
     *      Disparar.
     *      Salir.
     * 
     * */
    private static void verMenuJugar()
    {
        int opcionJugada;

        juegoProvider.iniciarMenuJugar();

        int ayuda = verMenuAyuda();

        do
        {
            if (ayuda == Constantes.AYUDA_SI)
            {
                // Mostramos información
                System.out.println(juegoProvider.toString());

                // Buscamos percepciones
                System.out.println("========================== Percepciones ======================== \n");

                juegoProvider.encontrarPercepcionAdyacente();
            }

            System.out.println("==================================================================== \n");
            System.out.println("");
            System.out.println("============================ Menú Jugar ============================");
            System.out.println("                                1) Arriba.");
            System.out.println("                                2) Abajo.");
            System.out.println("                                3) Derecha.");
            System.out.println("                                4) Izquierda.");
            System.out.println("                                5) Disparar. (Hacia la dirección en la que se ha movido)");
            System.out.println("                                6) Salir. (Debe estar en la casilla de salida)");
            System.out.println("====================================================================");

            opcionJugada = ordenUsuario.nextInt();

            // Movemos al cazador
            juegoProvider.moverHunter(opcionJugada);

        }
        while (juegoProvider.comprobarSeguimosJugando(opcionJugada));

        // Mostramos el motivo del fin del juego
        juegoProvider.motivoFinJuego(opcionJugada);

        System.exit(0);
    }

    /**
     * 
     * Comprobamos si el usuario quiere ver ayudas o no.
     * 
     * */
    public static int verMenuAyuda()
    {
        System.out.println("========================== ¿Desea jugar con ayuda? =====================");
        System.out.println("                                1) Si.");
        System.out.println("                                2) No.");
        System.out.println("========================================================================");

        return ordenUsuario.nextInt();
    }
}
