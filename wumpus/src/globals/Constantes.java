package globals;

public class Constantes
{
    // Opciones de menú
    public static final int PRINCIPAL_CONFIGURACION = 1;
    public static final int PRINCIPAL_JUGAR = 2;

    public static final int CONFIGURACION_TAMANYO = 1;
    public static final int CONFIGURACION_FLECHAS = 2;

    public static final int AYUDA_SI = 1;
    public static final int AYUDA_NO = 2;
    
    public static final int SALIR_PRINCIPAL = 4;
    public static final int SALIR_CONFIG = 4;
    public static final String SALIENDO = "Saliendo...";
    
    // Movimientos
    public static final int SUBIR = 1;
    public static final int BAJAR = 2;
    public static final int DERECHA = 3;
    public static final int IZQUIERDA = 4;
    public static final int DISPARAR = 5;
    public static final int SALIR_JUEGO = 6;
    public static final String SUBIR_TEXTO = "Acaba de subir una casilla.";
    public static final String BAJAR_TEXTO = "Acaba de bajar una casilla.";
    public static final String IZQUIERDA_TEXTO = "Acaba de moverse a la izquierda una casilla.";
    public static final String DERECHA_TEXTO = "Acaba de girar a la derecha.";
    public static final String DISPARANDO_FLECHA = "...Disparando Flecha...";
    public static final String WUMPUS_MUERTO = "¡¡¡AAAARGGH!!! El Wumpus ha muerto.";
    public static final String DISPARO_PARED = "Has disparado a la pared.";
    public static final String SIN_FLECHAS = "¡No te quedan flechas!.";
    
    // PERCEPCIONES
    public static final String PERCEPCION_ORO = "Notas que algo brilla muy cerca..";
    public static final String PERCEPCION_POZO = "Notas una brisa marina muy cerca..";
    public static final String PERCEPCION_WUMPUS = "Notas un hedor maligno muy cerca..";

    // FINALES
    public static final String GAME_OVER = "Has perdido.";
    public static final String GAME_WIN = "Has ganado.";

    // JUEGO
    public static final String FUERA_lIMITES_TABLERO = "¡¡Pero!!.. ¡Que te chocas con la pared!";
    public static final String MOVIMIENTO_DESCONOCIDO = "¿Perdona? ¿Hacia dónde quieres moverte?";
    public static final String COMPROBANDO_SALIR = "Comprobando si puede salir...";
    public static final String SALIR_CASILLA_SALIDA = "Debe estar en la casilla de salida";
    public static final String SALIR_SIN_GANAR = "Usted ha elegido salir. ¡Ha perdido!";
    public static final String OPCION_NO_EXISTE= "Perdón, esa opción no existe.";
}
