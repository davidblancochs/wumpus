package business.model;

public class Wumpus extends Elemento
{
    private EstadoElemento estadoWumpus;

    public Wumpus(int posicionX, int posicionY)
    {
        super(posicionX, posicionY);
        estadoWumpus = EstadoElemento.vivo;
    }

    public Wumpus()
    {
        super();
    }

    public EstadoElemento getEstadoWumpus()
    {
        return estadoWumpus;
    }

    public void setEstadoWumpus(EstadoElemento estadoWumpus)
    {
        this.estadoWumpus = estadoWumpus;
    }

    @Override
    public String toString()
    {
        String wumpusString = "";

        wumpusString += "Wumpus situado en casilla " + posicionX + "x" + posicionY + "\n";
        wumpusString += "Estado Wumpus: " + estadoWumpus + "\n";
        return wumpusString;
    }

}
