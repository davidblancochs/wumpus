package business.model;

import org.junit.Test;

public abstract class Elemento
{
    protected int posicionX;
    protected int posicionY;

    public Elemento(int posicionX, int posicionY)
    {
        super();
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public Elemento()
    {
        super();
    }

    public int getPosicionX()
    {
        return posicionX;
    }
    
    public void setPosicionX(int posicionX)
    {
        this.posicionX = posicionX;
    }

    public int getPosicionY()
    {
        return posicionY;
    }

    public void setPosicionY(int posicionY)
    {
        this.posicionY = posicionY;
    }

    public boolean posicionOcupada(int posicionX, int posicionY)
    {
        boolean posicionOcupada = false;

        if (posicionX == this.posicionX && posicionY == this.posicionY)
        {
            posicionOcupada = true;
        }

        return posicionOcupada;
    }

}
