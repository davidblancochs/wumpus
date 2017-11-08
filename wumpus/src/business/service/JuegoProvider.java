package business.service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import business.model.Elemento;
import business.model.EstadoElemento;
import business.model.Hunter;
import business.model.Oro;
import business.model.Pozo;
import business.model.Tablero;
import business.model.Wumpus;
import globals.Constantes;

public class JuegoProvider
{
    Scanner ordenUsuario = new Scanner(System.in);

    // Valores por defecto
    static int nFlechas = 9;
    static int nPozos = 1;
    static int tamanyoX = 4;
    static int tamanyoY = 4;
    static int salidaX = 0;
    static int salidaY = 0;

    //Elementos
    Tablero tablero;
    public Hunter hunter;
    public Wumpus wumpus;
    public ArrayList<Pozo> listaPozos = new ArrayList<Pozo>();
    public Oro oro;

    /**
     * 
     * Constructor crea un tablero de valores por defecto ó elegidos en configuración.
     * 
     * */
    public JuegoProvider()
    {
        super();
        tablero = new Tablero(tamanyoX, tamanyoY, salidaX, salidaY, nPozos);
    }

    @Override
    public String toString()
    {
        String informacionJuego = "";

        informacionJuego += "===================== Información del juego =========================== \n";
        informacionJuego += "============================ Tablero ================================== \n";
        informacionJuego += tablero.toString() + "\n";
        informacionJuego += "============================ Cazador ================================== \n";
        informacionJuego += hunter.toString();
        informacionJuego += "=========================== Elementos ================================= \n";
        informacionJuego += oro.toString();
        informacionJuego += wumpus.toString();
        for (int i = 0; i < listaPozos.size(); i++)
        {
            informacionJuego += listaPozos.get(i).toString();
        }

        return informacionJuego;
    }

    /**
     * 
     * Menu de configuración. 
     *      1) Tamaño tablero 
     *      2) Número de pozos. 
     *      3) Número de flechas del cazador.
     * 
     */
    public void menuConfiguracion(int opcionConfiguracion)
    {

        switch (opcionConfiguracion)
        {
            // Tamaño del tablero
            case 1:
                System.out.println("Introduzca tamaño del tablero: 4x4 || 5x5.");
                System.out.println("Opción 1:4x4 || Opción 2: 5x5.");

                int opcionTablero = ordenUsuario.nextInt();
                if (opcionTablero == 1)
                {
                    tablero.setTamanyoTablero(4, 4);
                }
                else
                {
                    tablero.setTamanyoTablero(5, 5);
                }

                System.out.println(tablero.toString());
                break;

            // Número de pozos en el tablero
            case 2:
                System.out.println("Introduzca número de pozos entre 1 - 4.");

                nPozos = ordenUsuario.nextInt();
                tablero.setnPozos(nPozos);
                
                System.out.println("Su tablero tendrá " + nPozos + " pozos.");

                break;

            // Flechas del cazador
            case 3:
                System.out.println("Introduzca número de flechas del cazador.");

                nFlechas = ordenUsuario.nextInt();

                System.out.println("Su cazador tendrá " + nFlechas + " flechas.");
                break;

            case 4:
                System.out.println(Constantes.SALIENDO);
                break;

            default:

                System.out.println(Constantes.OPCION_NO_EXISTE);

                break;
        }
    }

    /**
     * 
     * Iniciamos los elementos del Tablero. Hunter. Elementos Aleatorios.
     * 
     */
    public void iniciarMenuJugar()
    {
        // Creamos un cazador
        Hunter hunter = new Hunter(salidaX, salidaY);
        hunter.setNFlechas(nFlechas);
        this.hunter = hunter;

        crearElementosAleatorios();
    }

    /**
     * Creación de elementos de forma aleatoria.
     * 
     */
    public void crearElementosAleatorios()
    {
        Random valorRandom = new Random();
        oro = new Oro();
        wumpus = new Wumpus();

        // Oro
        do
        {
            crearElementoAleatorio(oro, valorRandom);
        }
        while (!existeElementoEnTablero(oro));

        // Wumpus
        do
        {
            crearElementoAleatorio(wumpus, valorRandom);
        }
        while (!existeElementoEnTablero(wumpus));

        // Pozos
        do
        {
            crearElementoAleatorio(null, valorRandom);
        }
        while (tablero.getnPozos() > 0);
        
    }

    /**
     * Crea un elemento en base a una posicion XY de forma aleatoria.
     * 
     */
    public void crearElementoAleatorio(Elemento elemento, Random valorRandom)
    {
        int tamanyoTablero = tablero.getSizeX() - 1;
        int posicionX = valorRandom.nextInt(tamanyoTablero);
        int posicionY = valorRandom.nextInt(tamanyoTablero);

        // Generamos posiciones aleatorias que no estén ocupadas
        while (posicionOcupada(posicionX, posicionY))
        {
            posicionX = valorRandom.nextInt(tamanyoTablero);
            posicionY = valorRandom.nextInt(tamanyoTablero);
        }

        if (elemento == null)
        {
            if (tablero.getnPozos() > 0)
            {
                for (int i = 0; i < tablero.getnPozos(); i++)
                {
                    listaPozos.add(new Pozo(posicionX, posicionY));
                    tablero.restarNPozos();
                }
            }
        }
        else
        {
            if (elemento instanceof Oro)
            {
                oro = new Oro(posicionX, posicionY);
            }

            if (elemento instanceof Wumpus)
            {
                wumpus = new Wumpus(posicionX, posicionY);
            }
        }

    }

    /**
     * Comprueba si la posición XY está ocupada por algun elemento del juego
     * 
     */
    public boolean posicionOcupada(int posicionX, int posicionY)
    {
        boolean ocupada = false;

        //Comprobamos si está ocupada la posicion por Oro/ Wumpus/ Hunter o salida
        if (oro.posicionOcupada(posicionX, posicionY) 
                || wumpus.posicionOcupada(posicionX, posicionY) 
                || hunter.posicionOcupada(posicionX, posicionY)
                || (posicionX == 0 && posicionY == 0))
        {
            ocupada = true;
        }
        //Comprobamos si la posición la tiene algún pozo
        else
        {
            for (int i = 0; i < listaPozos.size(); i++)
            {
                if (listaPozos.get(i).posicionOcupada(posicionX, posicionY))
                {
                    ocupada = true;
                    break;
                }

            }
        }

        return ocupada;
    }

    /**
     * Comprueba si existe el elemento en el tablero.
     * 
     */
    public boolean existeElementoEnTablero(Elemento elemento)
    {
        boolean existeElemento = false;

        for (int i = 0; i < tablero.getSizeX() - 1; i++)
        {
            for (int j = 0; j < tablero.getSizeY() - 1; j++)
            {
                if (i == elemento.getPosicionX() && j == elemento.getPosicionY())
                {
                    existeElemento = true;
                }
            }
        }
        return existeElemento;
    }

    /**
     * Muestra por pantalla percepciones.
     * 
     * Por cada elemento comprobamos percepciones:
     *          Encima de hunter.
     *          Abajo de hunter.
     *          A la derecha de hunter.
     *          A la izquierda de hunter.
     */
    public String encontrarPercepcionAdyacente()
    {
        String percepcion = "";

        if (hunter.getPosicionX() + 1 == oro.getPosicionX() && hunter.getPosicionY() == oro.getPosicionY()
                || hunter.getPosicionX() == oro.getPosicionX() && hunter.getPosicionY() + 1 == oro.getPosicionY()
                || hunter.getPosicionX() + 1 == oro.getPosicionX() && hunter.getPosicionY() + 1 == oro.getPosicionY()
                || hunter.getPosicionX() - 1 == oro.getPosicionX() && hunter.getPosicionY() == oro.getPosicionY()
                || hunter.getPosicionX() == oro.getPosicionX() && hunter.getPosicionY() - 1 == oro.getPosicionY()
                || hunter.getPosicionX() - 1 == oro.getPosicionX() && hunter.getPosicionY() - 1 == oro.getPosicionY())
        {
            percepcion += Constantes.PERCEPCION_ORO + "\n";
        }

        for (int i = 0; i < listaPozos.size(); i++)
        {
            Pozo pozo = listaPozos.get(i);
            
            if (hunter.getPosicionX() + 1 == pozo.getPosicionX() && hunter.getPosicionY() == pozo.getPosicionY()
                    || hunter.getPosicionX() == pozo.getPosicionX() && hunter.getPosicionY() + 1 == pozo.getPosicionY()
                    || hunter.getPosicionX() + 1 == pozo.getPosicionX() && hunter.getPosicionY() + 1 == pozo.getPosicionY()
                    || hunter.getPosicionX() - 1 == pozo.getPosicionX() && hunter.getPosicionY() == pozo.getPosicionY()
                    || hunter.getPosicionX() == pozo.getPosicionX() && hunter.getPosicionY() - 1 == pozo.getPosicionY()
                    || hunter.getPosicionX() - 1 == pozo.getPosicionX() && hunter.getPosicionY() - 1 == pozo.getPosicionY())
            {
                percepcion += Constantes.PERCEPCION_POZO + "\n";
            }
        }
       

        if (hunter.getPosicionX() + 1 == wumpus.getPosicionX() && hunter.getPosicionY() == wumpus.getPosicionY()
                || hunter.getPosicionX() == wumpus.getPosicionX() && hunter.getPosicionY() + 1 == wumpus.getPosicionY()
                || hunter.getPosicionX() + 1 == wumpus.getPosicionX() && hunter.getPosicionY() + 1 == wumpus.getPosicionY()
                || hunter.getPosicionX() - 1 == wumpus.getPosicionX() && hunter.getPosicionY() == wumpus.getPosicionY()
                || hunter.getPosicionX() == wumpus.getPosicionX() && hunter.getPosicionY() - 1 == wumpus.getPosicionY()
                || hunter.getPosicionX() - 1 == wumpus.getPosicionX() && hunter.getPosicionY() - 1 == wumpus.getPosicionY())
        {
            percepcion += Constantes.PERCEPCION_WUMPUS + "\n";
        }
        
        System.out.println(percepcion);
        
        return percepcion;
    }

    /**
     * Realizamos el movimiento elegido por el jugador.
     * 
     */
    public void moverHunter(int opcionJugada)
    {
        int hunterPosX = hunter.getPosicionX();
        int hunterPosY = hunter.getPosicionY();

        switch (opcionJugada)
        {
            case Constantes.SUBIR:
                if (hunterPosY + 1 <= tablero.getSizeY() - 1)
                {
                    hunter.setPosicionY(hunterPosY + 1);
                    System.out.println(Constantes.SUBIR_TEXTO);
                }
                else
                {
                    System.out.println(Constantes.FUERA_lIMITES_TABLERO);
                }
                break;

            case Constantes.BAJAR:
                if (hunterPosY - 1 >= 0)
                {
                    hunter.setPosicionY(hunterPosY - 1);
                    System.out.println(Constantes.BAJAR_TEXTO);
                }
                else
                {
                    System.out.println(Constantes.FUERA_lIMITES_TABLERO);
                }
                break;
                
            case Constantes.DERECHA:
                if (hunterPosX + 1 <= tablero.getSizeX() - 1)
                {
                    hunter.setPosicionX(hunterPosX + 1);
                    System.out.println(Constantes.DERECHA_TEXTO);
                }
                else
                {
                    System.out.println(Constantes.FUERA_lIMITES_TABLERO);
                }
                break;
                
            case Constantes.IZQUIERDA:
                if (hunterPosX - 1 >= 0)
                {
                    hunter.setPosicionX(hunterPosX - 1);
                    System.out.println(Constantes.IZQUIERDA_TEXTO);
                }
                else
                {
                    System.out.println(Constantes.FUERA_lIMITES_TABLERO);
                }
                break;
                
            case Constantes.DISPARAR:
                disparar();
                break;
                
            case Constantes.SALIR_JUEGO:
                System.out.println(Constantes.COMPROBANDO_SALIR);
                break;
                
            default:
                System.out.println(Constantes.MOVIMIENTO_DESCONOCIDO);
                break;
        }

        // Comprobamos si hay algún elemento en la casilla y realizamos la acción
        comprobarHayAlgoEnCasilla();
    }

    /**
     * 
     * Realizamos le disparo y mostramos:
     *      Ha matado al Wumpus.
     *      Ha disparado a la pared.
     * 
     * */
    public void disparar()
    {
        if (hunter.getNFlechas() > 0)
        {
            System.out.println("======================= Dirección del disparo ======================");
            System.out.println("                                1) Arriba.");
            System.out.println("                                2) Abajo.");
            System.out.println("                                3) Derecha.");
            System.out.println("                                4) Izquierda.");
            System.out.println("====================================================================");

            int direccionFlecha = ordenUsuario.nextInt();

            System.out.println(Constantes.DISPARANDO_FLECHA);

            switch (direccionFlecha)
            {
                case 1:
                    // DISPARAR ARRIBA
                    for (int i = hunter.getPosicionY(); i < tablero.getSizeY(); i++)
                    {
                        // Si nos encontramos al wumpus. Lo matamos
                        if (wumpus.getPosicionY() == i && hunter.getPosicionX() == wumpus.getPosicionX())
                        {
                            wumpus.setEstadoWumpus(EstadoElemento.muerto);
                        }
                    }
                    break;
                case 2:
                    // DISPARAR ABAJO
                    for (int i = hunter.getPosicionY(); i < tablero.getSizeY(); i--)
                    {
                        // Si nos encontramos al wumpus. Lo matamos
                        if (wumpus.getPosicionY() == i && hunter.getPosicionX() == wumpus.getPosicionX())
                        {
                            wumpus.setEstadoWumpus(EstadoElemento.muerto);
                        }
                    }
                    break;
                case 3:
                    // DISPARAR DERECHA
                    for (int i = hunter.getPosicionX(); i < tablero.getSizeX(); i++)
                    {
                        // Si nos encontramos al wumpus. Lo matamos
                        if (wumpus.getPosicionX() == i && hunter.getPosicionY() == wumpus.getPosicionY())
                        {
                            wumpus.setEstadoWumpus(EstadoElemento.muerto);
                        }
                    }
                    break;
                case 4:
                    // DISPARAR IZQUIERDA
                    for (int i = hunter.getPosicionX(); i < tablero.getSizeX(); i--)
                    {
                        // Si nos encontramos al wumpus. Lo matamos
                        if (wumpus.getPosicionX() == i && hunter.getPosicionY() == wumpus.getPosicionY())
                        {
                            wumpus.setEstadoWumpus(EstadoElemento.muerto);
                        }
                    }
                    break;
                default:
                    System.out.println(Constantes.OPCION_NO_EXISTE);
                    break;
            }

            // Si lo ha matado
            if (wumpus.getEstadoWumpus() == EstadoElemento.muerto)
            {
                System.out.println(Constantes.WUMPUS_MUERTO);
            }
            else
            {
                System.out.println(Constantes.DISPARO_PARED);
            }

            hunter.gastarFlecha();

        }
        else
        {
            System.out.println(Constantes.SIN_FLECHAS);
        }

    }

    /**
     * 
     * Método post-Movimiento Comprobamos si en la casilla hay Wumpus/ Pozo/ Oro
     * 
     */
    public void comprobarHayAlgoEnCasilla()
    {
        int hunterPosX = hunter.getPosicionX();
        int hunterPosY = hunter.getPosicionY();

        // Si está el wumpus en la casilla
        if (wumpus.posicionOcupada(hunterPosX, hunterPosY))
        {
            hunter.setMotivoMuerte("Ha sido matado por el Wumpus");
            hunter.matarHunter();
        }

        // Si está un pozo en la casilla
        for (int i = 0; i < listaPozos.size(); i++)
        {
            Pozo pozo = listaPozos.get(i);
            
            if (pozo.posicionOcupada(hunterPosX, hunterPosY))
            {
                hunter.setMotivoMuerte("Ha sido matado por un pozo");
                hunter.matarHunter();
            }
        }

        // Si hay oro en la casilla
        if (oro.posicionOcupada(hunterPosX, hunterPosY))
        {
            hunter.setOro(true);
        }
    }

    /**
     * 
     * Comprobamos si se puede seguir jugando.
     * 
     * */
    public boolean comprobarSeguimosJugando(int opcionJugada)
    {
        boolean continuar = true;

        if ((hunter.estaEnCasillaSalida() && opcionJugada == Constantes.SALIR_JUEGO) 
                || hunter.comprobarHaGanado() 
                || !hunter.comprobarSigueVivo())
        {
            continuar = false;
        }

        return continuar;
    }

    /**
     * 
     * Mostramos el motivo del fin del juego.
     * 
     *      Ha ganado.
     *      Ha muerto.
     *      Ha salido sin el oro.
     * 
     * */
    public String motivoFinJuego(int opcionJugada)
    {
        String motivo = "";
        String motivoMuerte = hunter.getMotivoMuerte();

        // Si ha ganado
        if (hunter.comprobarHaGanado())
        {
            System.out.println(Constantes.GAME_WIN);
        }

        // Si ha muerto
        if (motivoMuerte != "" && motivoMuerte != null)
        {
            System.out.println(Constantes.GAME_OVER);
            System.out.println(motivoMuerte);
        }

        // Si ha elegido salir sin ganar
        if (hunter.estaEnCasillaSalida() && opcionJugada == Constantes.SALIR_JUEGO)
        {
            System.out.println(Constantes.SALIR_SIN_GANAR);
        }

        return motivo;
    }

}
