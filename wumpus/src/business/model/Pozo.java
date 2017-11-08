/**
 * 
 */
package business.model;

/**
 * @author samuel
 *
 */
public class Pozo extends Elemento {

    public Pozo(int posicionX, int posicionY)
    {
        super(posicionX, posicionY);
    }


    public Pozo()
    {
        super();
    }


    @Override
    public String toString()
    {
        String pozoString = "";

        pozoString += "Pozo situado en casilla " + posicionX + "x" + posicionY + "\n";

        return pozoString;
    }
}
