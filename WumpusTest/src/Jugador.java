
public class Jugador extends Elemento {
	private int flechas;
    private boolean estaMuerto, tieneOro, partidaGanada;

    // MÉTODO CONSTRUCTOR:
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

    // MÉTODOS CONSULTORES (GETTERS):
    /**
     * Método consultor que devuelve el número de flechas de qué dispone el Jugador.
     * @return int, el número de flechas.
     */
    public int getFlechas(){
        return flechas;
    }

    /**
     * Método consultor que devuelve la condición de si el jugador está muerto.
     * @return boolean, la condición de vida del jugador.
     */
    public boolean getEstaMuerto(){
        return estaMuerto;
    }

    /**
     * Método consultor que devuelve la condición de si el jugador posee el oro.
     * @return boolean, la condición de posesión del oro por parte del jugador.
     */
    public boolean getTieneOro(){
        return tieneOro;
    }

    /**
     * Método consultor que devuelve la condición de si el jugador reune las condiciones necesarias para
     * determinar que el mismo ha ganado la partida.
     * @return boolean, la condición de ganador de la partida.
     */
    public boolean getPartidaGanada(){
        return partidaGanada;       
    }

    /**
     * Devuelve un String con la descripción del jugador.
     * @return String, la descripción.
     */
    public String toString(){
        String resultado = super.toString() +
            "\nNúmero de flechas: " + this.getFlechas() + 
            "\n¿El jugador está vivo?: " + this.getEstaMuerto() +
            "\n¿El jugador tiene el oro?: " + this.getTieneOro() + 
            "\n¿El jugador reúne las condiciones para darse por terminada la partida?: " + this.getPartidaGanada();
        return resultado;
    }

    // MÉTODOS MODIFICADORES (SETTERS):
    /**
     * Modifica el número de flechas de qué dispone el jugador.
     * @param flechas int, que representa a la nueva cantidad de flechas.
     */
    public void setFlechas(int flechas){
        this.flechas = flechas;
    }

    /**
     * Modifica la condición de vida del jugador.
     * @param estaMuerto boolean, que representa a la nueva condición de vida del jugador.
     */
    public void setEstaMuerto(boolean estaMuerto){
        this.estaMuerto = estaMuerto;
    }

    /**
     * Modifica la condición de posesión del oro por parte del jugador.
     * @param tieneOro boolean, que representa a la nueva condición de posesión del oro.
     */
    public void setTieneOro(boolean tieneOro){
        this.tieneOro = tieneOro;
    }

    /**
     * Modifica la condición del cumplimiento de los requisitos para dar por ganada la partida por parte del jugador.
     * @param partidaGanada boolean, que representa a la nueva condición de cumplimiento de los requisitos.
     */
    public void setPartidaGanada(boolean partidaGanada){
        this.partidaGanada = partidaGanada;
    }

    // MÉTODOS FUNCIONALES:
    /**
     * Modifica las coordenadas de la casilla que ocupa el jugador, moviendo al mismo a otra casilla del tablero.
     * Lista de movimientos: 1 para retroceder, 2 para avanzar, 3 para girar a la derecha y 4 para girar a la izquierda.
     * @param muro int, que representa el borde o muro del tablero.
     * @param direccion int, que representa el movimiento de la lista de movimientos que realizará el jugador.
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
     * Método que devuelve la posición que ocupa el jugador en el tablero y que durante la ejecución del mismo
     * genera un mensaje de hacia dónde ha sido disparada la flecha.
     * Lista de direcciones: 1 para retroceder, 2 para avanzar, 3 para girar a la derecha y 4 para girar a la izquierda.
     * @param direccion int, que representa la dirección de hacia dónde saldrá la flecha una vez sea disparada.
     * @return int, la posición del jugador en el tablero.
     */
    public int disparar(int direccion){
        int posicionJugador = this.getX();
        String mensaje = "";
        if(this.flechas > 0){
            this.flechas--;
            switch(direccion){
                case 1: mensaje += "El jugador ha disparado hacia atrás.";
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
