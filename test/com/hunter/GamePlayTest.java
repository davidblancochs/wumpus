package com.hunter;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GamePlayTest {


    @Test
    public void playSomeGameUntilDieTest() throws Exception {

        String input = " 2\n 1\n 1\n M\n L\n M\n L\n M\n L\n M\n L\n";
        System.out.println("- Parameters: \n" + input);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String args [] = {"DEBUG"};

        WumpusGame.main(args);

    }
}
