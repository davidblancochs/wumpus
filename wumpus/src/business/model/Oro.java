package business.model;

public class Oro extends Elemento
{

    public Oro(int posicionX, int posicionY)
    {
        super(posicionX, posicionY);
    }

    
    public Oro()
    {
        super();
    }


    @Override
    public String toString()
    {
        String oroString = "";

        oroString += "Oro situado en casilla " + posicionX + "x" + posicionY + "\n";

        return oroString;
    }

}
