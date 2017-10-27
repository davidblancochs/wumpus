
public class Jugador extends Elemento {
	private int flechas;
    private boolean estaMuerto, tieneOro, partidaGanada;

    // M�TODO CONSTRUCTOR:
    /**
     * Constructor que dadas unas coordenadas x e y crea un Jugador. Los atributos "estaMuerto", "tieneOro" y "partidaGanada"
     * se declaran por defecto a false. El atributo "flechas" se declara por defecto a un valor de 5.
     * @param x int, que representa la coordenada del jugador en el eje horizontal.
     * @param y int, que representa la coordenada del jugador en el eje vertical.
     * @return Jugador, el Jugador creado.
     */
    public Jugador(int x, int y){
        super(x,y);
        this.flechas = 5;
        this.estaMuerto = false;
        this.tieneOro = false;
        this.partidaGanada = false;
    }

    // M�TODOS CONSULTORES (GETTERS):
    /**
     * M�todo consultor que devuelve el n�mero de flechas de qu� dispone el Jugador.
     * @return int, el n�mero de flechas.
     */
    public int getFlechas(){
        return flechas;
    }

    /**
     * M�todo consultor que devuelve la condici�n de si el jugador est� muerto.
     * @return boolean, la condici�n de vida del jugador.
     */
    public boolean getEstaMuerto(){
        return estaMuerto;
    }

    /**
     * M�todo consultor que devuelve la condici�n de si el jugador posee el oro.
     * @return boolean, la condici�n de posesi�n del oro por parte del jugador.
     */
    public boolean getTieneOro(){
        return tieneOro;
    }

    /**
     * M�todo consultor que devuelve la condici�n de si el jugador reune las condiciones necesarias para
     * determinar que el mismo ha ganado la partida.
     * @return boolean, la condici�n de ganador de la partida.
     */
    public boolean getPartidaGanada(){
        return partidaGanada;       
    }

    /**
     * Devuelve un String con la descripci�n del jugador.
     * @return String, la descripci�n.
     */
    public String toString(){
        String resultado = super.toString() +
            "\nN�mero de flechas: " + this.getFlechas() + 
            "\n�El jugador est� vivo?: " + this.getEstaMuerto() +
            "\n�El jugador tiene el oro?: " + this.getTieneOro() + 
            "\n�El jugador re�ne las condiciones para darse por terminada la partida?: " + this.getPartidaGanada();
        return resultado;
    }

    // M�TODOS MODIFICADORES (SETTERS):
    /**
     * Modifica el n�mero de flechas de qu� dispone el jugador.
     * @param flechas int, que representa a la nueva cantidad de flechas.
     */
    public void setFlechas(int flechas){
        this.flechas = flechas;
    }

    /**
     * Modifica la condici�n de vida del jugador.
     * @param estaMuerto boolean, que representa a la nueva condici�n de vida del jugador.
     */
    public void setEstaMuerto(boolean estaMuerto){
        this.estaMuerto = estaMuerto;
    }

    /**
     * Modifica la condici�n de posesi�n del oro por parte del jugador.
     * @param tieneOro boolean, que representa a la nueva condici�n de posesi�n del oro.
     */
    public void setTieneOro(boolean tieneOro){
        this.tieneOro = tieneOro;
    }

    /**
     * Modifica la condici�n del cumplimiento de los requisitos para dar por ganada la partida por parte del jugador.
     * @param partidaGanada boolean, que representa a la nueva condici�n de cumplimiento de los requisitos.
     */
    public void setPartidaGanada(boolean partidaGanada){
        this.partidaGanada = partidaGanada;
    }

    // M�TODOS FUNCIONALES:
    /**
     * Modifica las coordenadas de la casilla que ocupa el jugador, moviendo al mismo a otra casilla del tablero.
     * Lista de movimientos: 1 para retroceder, 2 para avanzar, 3 para girar a la derecha y 4 para girar a la izquierda.
     * @param muro int, que representa el borde o muro del tablero.
     * @param direccion int, que representa el movimiento de la lista de movimientos que realizar� el jugador.
     */
    public void moverse(int muro, int direccion){
        int x = this.getX(), y = this.getY();
        switch(direccion){
            case 1:
            if(x > 0) {
                this.setX(x - 1); 
                System.out.println("Has retrocedido una casilla.");
            } else {
                System.out.println("Has chocado con un muro.");
            }
            break;
            case 2:
            if(x < (muro-1)){
                this.setX(x + 1); 
                System.out.println("Has avanzado una casilla.");
            } else {
                System.out.println("Has chocado con un muro.");
            }
            break;
            case 3:
            if(y < (muro-1)){
                this.setY(y + 1);
                System.out.println("Has girado una casilla hacia la derecha.");
            } else {
                System.out.println("Has chocado con un muro.");
            }
            break;
            case 4:
            if(y > 0){
                this.setY(y - 1); 
                System.out.println("Has girado una casilla hacia la izquierda.");
            } else {
                System.out.println("Has chocado con un muro.");
            }
            break;
        }
    }

    /**
     * M�todo que devuelve la posici�n que ocupa el jugador en el tablero y que durante la ejecuci�n del mismo
     * genera un mensaje de hacia d�nde ha sido disparada la flecha.
     * Lista de direcciones: 1 para retroceder, 2 para avanzar, 3 para girar a la derecha y 4 para girar a la izquierda.
     * @param direccion int, que representa la direcci�n de hacia d�nde saldr� la flecha una vez sea disparada.
     * @return int, la posici�n del jugador en el tablero.
     */
    public int disparar(int direccion){
        int posicionJugador = this.getX();
        String mensaje = "";
        if(this.flechas > 0){
            this.flechas--;
            switch(direccion){
                case 1: mensaje += "El jugador ha disparado hacia atr�s.";
                break;
                case 2: mensaje += "El jugador ha disparado hacia adelante.";
                break;
                case 3: mensaje += "El jugador ha disparado hacia la derecha.";
                break;
                case 4: mensaje += "El jugador ha disparado hacia la izquierda.";
                break;
            }
            System.out.println(mensaje);
        }
        return posicionJugador;
    }
}
