import java.util.Scanner;

public class Juego {
	public static void main(String[] args){ 
        Scanner teclado = new Scanner(System.in); 
        int opcionExterna;
        do{
            opcionExterna = mostrarMenu(teclado);
            switch(opcionExterna){
                case 0: // Finalizar el juego.
                System.out.print("\f");
                break;

                case 1: // Jugar.
                System.out.print("\fIntroduce el tama�o que desea para crear el tablero: ");
                int tama�oTablero = LecturaValida.leerEntero(teclado, "Escoge una opci�n (2 - 1000): ", 2, 1000);    

                Partida Partida = new Partida(tama�oTablero);
                Jugador Jugador = Partida.getJugador();

                System.out.println("\f-------- Informaci�n de la casilla actual: --------");
                System.out.println(Partida.mostrarInformacion());

                int opcionJugar;
                boolean wumpusMuerto = false;
                do{
                    opcionJugar = mostrarOpciones(teclado);
                    switch(opcionJugar){                      
                        case 1: // Mostrar lista de movimiento permitidos:
                        System.out.print("\f");
                        int opcionMovimiento = mostrarMovimientos(teclado);

                        System.out.println("\f-------- Informaci�n de la casilla actual: --------");
                        Jugador.moverse(tama�oTablero, opcionMovimiento);
                        System.out.println(Partida.mostrarInformacion());
                        break;

                        case 2: // Mostrar lista de disparos permitidos:
                        System.out.print("\f");
                        int opcionDisparo = mostrarDisparos(teclado);

                        System.out.println("\f-------- Informaci�n de la casilla actual: --------");
                        System.out.println(Partida.mostrarInformacion());
                        if(!wumpusMuerto){
                            wumpusMuerto = Partida.disparo(Jugador.getX(), Jugador.getY(), opcionDisparo);
                        } else {
                            System.out.println("El Wumpus ya est� muerto, �no desperdicies m�s flechas!");
                        }
                        break;
                    }
                }while (!Jugador.getEstaMuerto() || !Jugador.getPartidaGanada());
                break;
            }
        }while(opcionExterna != 0);
    }

    /**
     * Muestra un men� de juego por pantalla y permite al usuario elegir entre diferentes opciones.
     * @param teclado Scanner, para realizar la lectura de datos.
     * @return int, entero que representa la opci�n elegida.
     */
    private static int mostrarMenu(Scanner teclado){
        System.out.println("----------------- Men� de juego: ------------------");
        System.out.println(" 0) Salir.");
        System.out.println(" 1) Jugar.");
        System.out.println("---------------------------------------------------");

        return LecturaValida.leerEntero(teclado, "Escoge una opci�n (0 - 1): ", 0, 1);
    }

    /**
     * Muestra un men� por pantalla de las opciones que puede llevar a cabo el jugador dentro del juego 
     * y permite al usuario elegir entre ellas.
     * @param teclado Scanner, para realizar la lectura de datos.
     * @return int, entero que representa la opci�n elegida.
     */
    private static int mostrarOpciones(Scanner teclado){
        System.out.println("----------------- Men� de opciones: ---------------");
        System.out.println(" 1) Moverse.");
        System.out.println(" 2) Disparar.");
        System.out.println("---------------------------------------------------");

        return LecturaValida.leerEntero(teclado, "Escoge una opci�n (1 - 2): ", 1, 2);         
    }

    /**
     * Muestra un men� por pantalla de los movimientos que puede llevar a cabo el jugador dentro del juego
     * y permite al usuario elegir entre ellos.
     * @param teclado Scanner, para realizar la lectura de datos.
     * @return int, entero que representa la opci�n elegida.
     */
    private static int mostrarMovimientos(Scanner teclado){
        System.out.println("----------------- Men� de movimiento: -------------");
        System.out.println(" 0) Volver al men� de opciones.");
        System.out.println(" 1) Retroceder.");
        System.out.println(" 2) Avanzar.");
        System.out.println(" 3) Derecha.");
        System.out.println(" 4) Izquierda.");
        System.out.println("---------------------------------------------------");

        return LecturaValida.leerEntero(teclado, "Elija la direcci�n hacia d�nde desea moverse (0 - 4): ", 0, 4);         
    }

    /**
     * Muestra un men� por pantalla de los disparos que puede llevar a cabo el jugador 
     * y permite al usuario elegir entre ellos.
     * @param teclado Scanner, para realizar la lectura de datos.
     * @return int, entero que representa la opci�n elegida.
     */
    private static int mostrarDisparos(Scanner teclado){
        System.out.println("----------------- Men� de disparo: ----------------");
        System.out.println(" 0) Volver al men� de opciones.");
        System.out.println(" 1) Disparar hacia atr�s.");
        System.out.println(" 2) Disparar hacia adelante.");
        System.out.println(" 3) Disparar hacia la derecha.");
        System.out.println(" 4) Disparar hacia la izquierda.");
        System.out.println("---------------------------------------------------");

        return LecturaValida.leerEntero(teclado, "Elija la direcci�n hacia d�nde desea disparar (0 - 4): ", 0, 4);         
    }
}
