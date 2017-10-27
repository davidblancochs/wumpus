
public abstract class Elemento {
	private int x, y;

    // M�TODO CONSTRUCTOR:
    /**
     * Constructor que dadas unas coordenadas x e y crea un elemento.
     * @param x int, que representa la coordenada del elemento en el eje horizontal.
     * @param y int, que representa la coordenada del elemento en el eje vertical.
     * @return Elemento, el elemento creado.
     */
    public Elemento(int x, int y){
        this.x = x;
        this.y = y;
    }

    // M�TODOS CONSULTORES (GETTERS):
    /**
     * M�todo consultor que devuelve la coordenada del eje horizontal.
     * @return int, la coordenada del eje horizontal.
     */
    public int getX(){
        return x;
    }

    /**
     * M�todo consultor que devuelve la coordenada del eje vertical.
     * @return int, la coordenada del eje vertical.
     */
    public int getY(){
        return y;
    }

    /**
     * Devuelve un String con la descripci�n del elemento.
     * @return String, la descripci�n.
     */
    public String toString(){
        return "Posici�n: (" + x + "," + y + ")";
    }

    // M�TODOS MODIFICADORES (SETTERS):
    /**
     * Modifica la coordenada del eje horizontal.
     * @param x int, que representa a la nueva coordenada.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Modifica la coordenada del eje vertical.
     * @param y int, que representa a la nueva coordenada.
     */
    public void setY(int y){
        this.y = y;
    }
}
