
public class Habitacion extends Elemento{
    private boolean hayWumpus, hayAgujero, hayHedor, hayBrisa, hayOro;

    // M�TODO CONSTRUCTOR:
    /**
     * Constructor que dadas unas coordenadas x e y crea una Habitacion. Los atributos "hayWumpus", "hayAgujero",
     * "hayHedor", "hayBrisa" y "hayOro" se declaran por defecto a false. 
     * @param x int, que representa la coordenada de la habitaci�n en el eje horizontal.
     * @param y int, que representa la coordenada de la habitaci�n en el eje vertical.
     * @return Habitacion, la Habitacion creada.
     */
    public Habitacion(int x, int y){
        super(x,y);
    }

    // M�TODOS CONSULTORES (GETTERS):
    /**
     * M�todo consultor que devuelve la condici�n de si en la habitaci�n est� el Wumpus.
     * @return boolean, la condici�n de presencia del Wumpus.
     */
    public boolean getHayWumpus(){
        return hayWumpus;
    }

    /**
     * M�todo consultor que devuelve la condici�n de si en la habitaci�n hay un agujero.
     * @return boolean, la condici�n de presencia de un agujero.
     */
    public boolean getHayAgujero(){
        return hayAgujero;
    }

    /**
     * M�todo consultor que devuelve la condici�n de si en la habitaci�n se percibe el hedor del Wumpus.
     * @return boolean, la condici�n de presencia del hedor del Wumpus.
     */
    public boolean getHayHedor(){
        return hayHedor;
    }

    /**
     * M�todo consultor que devuelve la condici�n de si en la habitaci�n se percibe la brisa de alg�n agujero.
     * @return boolean, la condici�n de presencia de la brisa de alg�n agujero.
     */
    public boolean getHayBrisa(){
        return hayBrisa;
    }

    /**
     * M�todo consultor que devuelve la condici�n de si en la habitaci�n se percibe el brillo del oro.
     * @return boolean, la condici�n de presencia del brillo del oro.
     */
    public boolean getHayOro(){
        return hayOro;
    }

    /**
     * Devuelve un String con la descripci�n de la Habitacion.
     * @return String, la descripci�n.
     */
    public String toString(){
        String info = super.toString() +
            "\nEn la habitaci�n est� el Wumpus?: " + getHayWumpus() +
            "\nEn la habitaci�n hay un agujero?: " + getHayAgujero() +
            "\nEn la habitaci�n se percibe el hedor del Wumpus?: " + getHayHedor() +
            "\nEn la habitaci�n se percibe una brisa?: " + getHayBrisa() +
            "\nEn la habitaci�n se percibe cierto brillo?: " + getHayOro();
        return info;
    }

    // M�TODOS MODIFICADORES (SETTERS):
    /**
     * Modifica la condici�n de si en la habitaci�n se encuentra el Wumpus.
     * @param hayWumpus boolean, que representa a la nueva condici�n de presencia del Wumpus.
     */
    public void setHayWumpus(boolean hayWumpus){
        this.hayWumpus = hayWumpus;
    }

    /**
     * Modifica la condici�n de si en la habitaci�n se encuentra un agujero.
     * @param hayAgujero boolean, que representa a la nueva condici�n de presencia de un agujero.
     */
    public void setHayAgujero(boolean hayAgujero){
        this.hayAgujero = hayAgujero;
    }

    /**
     * Modifica la condici�n de si en la habitaci�n se percibe el hedor del Wumpus.
     * @param hayHedor boolean, que representa a la nueva condici�n de presencia del hedor del Wumpus.
     */
    public void setHayHedor(boolean hayHedor){
        this.hayHedor = hayHedor;
    }

    /**
     * Modifica la condici�n de si en la habitaci�n se percibe la brisa de un agujero.
     * @param hayBrisa boolean, que representa a la nueva condici�n de presencia de la brisa de un agujero.
     */
    public void setHayBrisa(boolean hayBrisa){
        this.hayBrisa = hayBrisa;
    }

    /**
     * Modifica la condici�n de si en la habitaci�n se percibe el brillo del oro.
     * @param hayWumpus boolean, que representa a la nueva condici�n de presencia del brillo del oro.
     */
    public void setHayOro(boolean hayOro){
        this.hayOro = hayOro;
    }
}
