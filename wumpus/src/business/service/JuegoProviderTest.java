package business.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import business.model.Oro;
import business.model.Wumpus;

class JuegoProviderTest
{

    @Test
    void testPosicionOcupada()
    {
       JuegoProvider juegoProvider = new JuegoProvider();
        
       juegoProvider.oro = new Oro(0,0);
       assertTrue(juegoProvider.posicionOcupada(0, 0));

    }

    
    @Test
    void testExisteElementoEnTablero()
    {
       JuegoProvider juegoProvider = new JuegoProvider();
        
       Wumpus wumpus = new Wumpus(99,99);
       assertFalse(juegoProvider.existeElementoEnTablero(wumpus));

       juegoProvider.oro = new Oro();
       assertTrue(juegoProvider.existeElementoEnTablero(juegoProvider.oro));
    }
}
