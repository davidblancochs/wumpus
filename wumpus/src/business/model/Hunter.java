package business.model;

/**
 * @author samuel
 *
 */
public class Hunter extends Elemento
{
    private int nFlechas;
    private boolean tieneOro;

    private EstadoElemento estadoHunter;

    private String motivoMuerte;

    public Hunter(int posicionX, int posicionY)
    {
        super(posicionX, posicionY);

        nFlechas = 9;
        tieneOro = false;
        estadoHunter = EstadoElemento.vivo;
    }

    public Hunter()
    {
        super();
    }

    public EstadoElemento matarHunter()
    {
        estadoHunter = EstadoElemento.muerto;

        return estadoHunter;
    }

    public int getNFlechas()
    {
        return nFlechas;
    }

    public void setNFlechas(int nFlechas)
    {
        this.nFlechas = nFlechas;
    }

    public boolean isOro()
    {
        return tieneOro;
    }

    public void setOro(boolean oro)
    {
        this.tieneOro = oro;
    }

    public EstadoElemento getEstadoHunter()
    {
        return estadoHunter;
    }

    public void setEstadoHunter(EstadoElemento estadoHunter)
    {
        this.estadoHunter = estadoHunter;
    }

    public String getMotivoMuerte()
    {
        return motivoMuerte;
    }

    public void setMotivoMuerte(String motivoMuerte)
    {
        this.motivoMuerte = motivoMuerte;
    }

    public boolean estaEnCasillaSalida()
    {
        boolean puedeSalir = false;

        if (posicionX == 0 && posicionY == 0)
        {
            puedeSalir = true;
        }

        return puedeSalir;
    }

    public boolean comprobarHaGanado()
    {
        boolean haGanado = false;

        if (estaEnCasillaSalida() && tieneOro)
        {
            haGanado = true;
        }
        return haGanado;
    }

    public boolean comprobarSigueVivo()
    {
        boolean sigueVivo = false;

        if (estadoHunter == EstadoElemento.vivo)
        {
            sigueVivo = true;
        }
        return sigueVivo;
    }

    public void gastarFlecha()
    {
        this.nFlechas = (this.nFlechas - 1);
    }

    @Override
    public String toString()
    {
        String hunterString = "";

        hunterString += "Hunter situado en casilla " + posicionX + "x" + posicionY + "\n";
        hunterString += "Tiene " + nFlechas + " flechas. \n";
        hunterString += "Tiene oro: " + tieneOro + "\n";
        hunterString += "Estado Hunter: " + estadoHunter + "\n";

        return hunterString;
    }

}
