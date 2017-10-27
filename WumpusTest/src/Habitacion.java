
public class Habitacion extends Elemento{
    private boolean hayWumpus, hayAgujero, hayHedor, hayBrisa, hayOro;

    // MÉTODO CONSTRUCTOR:
    /**
     * Constructor que dadas unas coordenadas x e y crea una Habitacion. Los atributos "hayWumpus", "hayAgujero",
     * "hayHedor", "hayBrisa" y "hayOro" se declaran por defecto a false. 
     * @param x int, que representa la coordenada de la habitación en el eje horizontal.
     * @param y int, que representa la coordenada de la habitación en el eje vertical.
     * @return Habitacion, la Habitacion creada.
     */
    public Habitacion(int x, int y){
        super(x,y);
    }

    // MÉTODOS CONSULTORES (GETTERS):
    /**
     * Método consultor que devuelve la condición de si en la habitación está el Wumpus.
     * @return boolean, la condición de presencia del Wumpus.
     */
    public boolean getHayWumpus(){
        return hayWumpus;
    }

    /**
     * Método consultor que devuelve la condición de si en la habitación hay un agujero.
     * @return boolean, la condición de presencia de un agujero.
     */
    public boolean getHayAgujero(){
        return hayAgujero;
    }

    /**
     * Método consultor que devuelve la condición de si en la habitación se percibe el hedor del Wumpus.
     * @return boolean, la condición de presencia del hedor del Wumpus.
     */
    public boolean getHayHedor(){
        return hayHedor;
    }

    /**
     * Método consultor que devuelve la condición de si en la habitación se percibe la brisa de algún agujero.
     * @return boolean, la condición de presencia de la brisa de algún agujero.
     */
    public boolean getHayBrisa(){
        return hayBrisa;
    }

    /**
     * Método consultor que devuelve la condición de si en la habitación se percibe el brillo del oro.
     * @return boolean, la condición de presencia del brillo del oro.
     */
    public boolean getHayOro(){
        return hayOro;
    }

    /**
     * Devuelve un String con la descripción de la Habitacion.
     * @return String, la descripción.
     */
    public String toString(){
        String info = super.toString() +
            "\nEn la habitación está el Wumpus?: " + getHayWumpus() +
            "\nEn la habitación hay un agujero?: " + getHayAgujero() +
            "\nEn la habitación se percibe el hedor del Wumpus?: " + getHayHedor() +
            "\nEn la habitación se percibe una brisa?: " + getHayBrisa() +
            "\nEn la habitación se percibe cierto brillo?: " + getHayOro();
        return info;
    }

    // MÉTODOS MODIFICADORES (SETTERS):
    /**
     * Modifica la condición de si en la habitación se encuentra el Wumpus.
     * @param hayWumpus boolean, que representa a la nueva condición de presencia del Wumpus.
     */
    public void setHayWumpus(boolean hayWumpus){
        this.hayWumpus = hayWumpus;
    }

    /**
     * Modifica la condición de si en la habitación se encuentra un agujero.
     * @param hayAgujero boolean, que representa a la nueva condición de presencia de un agujero.
     */
    public void setHayAgujero(boolean hayAgujero){
        this.hayAgujero = hayAgujero;
    }

    /**
     * Modifica la condición de si en la habitación se percibe el hedor del Wumpus.
     * @param hayHedor boolean, que representa a la nueva condición de presencia del hedor del Wumpus.
     */
    public void setHayHedor(boolean hayHedor){
        this.hayHedor = hayHedor;
    }

    /**
     * Modifica la condición de si en la habitación se percibe la brisa de un agujero.
     * @param hayBrisa boolean, que representa a la nueva condición de presencia de la brisa de un agujero.
     */
    public void setHayBrisa(boolean hayBrisa){
        this.hayBrisa = hayBrisa;
    }

    /**
     * Modifica la condición de si en la habitación se percibe el brillo del oro.
     * @param hayWumpus boolean, que representa a la nueva condición de presencia del brillo del oro.
     */
    public void setHayOro(boolean hayOro){
        this.hayOro = hayOro;
    }
}
