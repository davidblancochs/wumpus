package business.model;

public class Tablero
{
    private int salidaX;
    private int salidaY;

    private int sizeX;
    private int sizeY;

    private int nPozos;

    /**
     * Creación de un tablero de XY medidas con una salida en la posición XY.
     * 
     * @param Int
     *            sizeX
     * @param Int
     *            sizeY
     * @param Int
     *            salidaX
     * @param Int
     *            salidaY
     * @param Int
     *            nPozos
     */
    public Tablero(int sizeX, int sizeY, int salidaX, int salidaY, int nPozos)
    {
        super();

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.salidaX = salidaX;
        this.salidaY = salidaY;

        this.nPozos = nPozos;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public void setSizeX(int sizeX)
    {
        this.sizeX = sizeX + 1;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public void setSizeY(int sizeY)
    {
        this.sizeY = sizeY + 1;
    }

    public int getSalidaX()
    {
        return salidaX;
    }

    public void setSalidaX(int salidaX)
    {
        this.salidaX = salidaX;
    }

    public int getSalidaY()
    {
        return salidaY;
    }

    public void setSalidaY(int salidaY)
    {
        this.salidaY = salidaY;
    }

    public int getnPozos()
    {
        return nPozos;
    }

    public void setnPozos(int nPozos)
    {
        this.nPozos = nPozos;
    }

    public void restarNPozos()
    {
        this.nPozos = this.nPozos -1;
    }
    
    public void setTamanyoTablero(int tamanyoX, int tamanyoY)
    {
        this.sizeX = tamanyoX;
        this.sizeY = tamanyoY;
    }

    @Override
    public String toString()
    {
        String tableroString = "";
        tableroString += "Tablero de tamaño: " + sizeX + "x" + sizeY;
        tableroString += "\n";
        tableroString += "Salida situada en casilla " + getSalidaX() + "x" + getSalidaY();

        return tableroString;
    }

}
