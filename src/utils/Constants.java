package utils;

public class Constants 
{
	//Codigo de percepciones
	public static final int PERCEP_NADA = 0;
	public static final int PERCEP_HEDOR = 1;
	public static final int PERCEP_BRISA = 2;
	public static final int PERCEP_POZO = 3;
	public static final int PERCEP_WUMPUS = 4;
	public static final int PERCEP_ORO = 5;
	//public static final int PERCEP_GRITO = 6;
	//public static final int PERCEP_MURO = 7;		
	public static final int PERCEP_INICIO = 9;
	
	
	//Percepciones compuestas doble
	public static final int PERCEP_BRISA_HEDOR = 20;
	public static final int PERCEP_ORO_HEDOR = 21;
	public static final int PERCEP_ORO_BRISA = 22;
	public static final int PERCEP_INICIO_HEDOR = 23;
	public static final int PERCEP_INICIO_BRISA = 24;
	public static final int PERCEP_WUMPUS_BRISA = 25;
	
	//Percepciones compuestas triple
	public static final int PERCEP_INICIO_BRISA_HEDOR = 30;
	public static final int PERCEP_ORO_BRISA_HEDOR = 31;
	
	//Textos de percepciones
	
	public static final String PERCEP_TXT_NADA = "Nada hay en esta celda";
	public static final String PERCEP_TXT_HEDOR = "Un nauseabundo hedor se siente en el ambiente \n ¡¡CUIDADO!! La bestia esta cerca";
	public static final String PERCEP_TXT_BRISA = "Se siente una suave brisa \n ¡¡CUIDADO!! Hay un pozo cerca";
	public static final String PERCEP_TXT_POZO = "Te precipitas al interior de un pozo \n Nunca podras salir";
	public static final String PERCEP_TXT_WUMPUS = "Apenas has podido alzar el arco cuando el WUMPUS se ha abalanzado sobre ti.";
	public static final String PERCEP_TXT_ORO = "Tienes ante ti el misterioso tesoro del WUMPUS";
	public static final String PERCEP_TXT_INICIO = "Estas en el inicio.";

	public static final String PERCEP_TXT_GRITO = "Suena fuerte un alarido monstruoso, la bestia ha caido";
	public static final String PERCEP_TXT_MURO = "Un muro no te deja avanzar";
	
	
	//Textos doble
	public static final String PERCEP_TXT_BRISA_HEDOR = "Sientes una suave brisa con un putrefacto olor";
	public static final String PERCEP_TXT_ORO_HEDOR = "Has encontrado el tesoro, pero cuidado, la bestia esta cerca";
	public static final String PERCEP_TXT_ORO_BRISA = "Has encontrado el tesoro. La brisa aqui es mas fuerte.";
	public static final String PERCEP_TXT_INICIO_HEDOR = "Has encontrado por donde entrastes. Cuidado, la bestia esta cerca";
	public static final String PERCEP_TXT_INICIO_BRISA = "Has llegado a donde empezastes. La brisa aqui es mas fuerte.";
	
	//Textos triples
	
	public static final String PERCEP_TXT_INICIO_BRISA_HEDOR = "Has encontrado por donde empezastes. La brisa es fuerte aqui y lleva un olor nauseabundo.";
	public static final String PERCEP_TXT_ORO_BRISA_HEDOR = "Has encontrado el tesoro, pero el viento aqui es fuerte y hueles que la bestia esta cerca.";
	
	
	
	
	
	
	
	//Textos intermedios y de fin
	
	public static final String INTERMEDIO_TXT_ORO = "Ya tienes el tesoro del WUMPUS \n Corre y escapa con el";
	public static final String GAMEOVER_BUENO = "HAS GANADO";
	public static final String GAMEOVER_MALO = "HAS MUERTO";
	
	//Acciones
	
	public static final int ACTION_MOVE = 1;
	public static final int ACTION_TURN_LEFT = 2;
	public static final int ACTION_TURN_RIGHT = 3;
	public static final int ACTION_SHOT = 4;
	public static final int ACTION_EXIT = 5;
	

}
