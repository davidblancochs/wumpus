package business.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HunterTest
{

    @Test
    void testIsOro()
    {
        Hunter hunter = new Hunter();
        hunter.setOro(true);
        assertTrue(hunter.isOro());
        

        Hunter hunter2 = new Hunter();
        assertFalse(hunter2.isOro());
    }

    @Test
    void testComprobarSigueVivo()
    {
        Hunter hunter = new Hunter();
        hunter.setEstadoHunter(EstadoElemento.muerto);
        assertFalse(hunter.comprobarSigueVivo());
        
        Hunter hunter2 = new Hunter(0,0);
        assertTrue(hunter2.comprobarSigueVivo());
        
    }

}
