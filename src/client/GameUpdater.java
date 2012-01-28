/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Scott Adams
 */
public class GameUpdater extends Thread
{
    public Client client;
    @Override
    public void run()
    {
        /*
         * THIS IS HOW YOU CONNECT TO SERVER
         * 
         * client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(ArrayList.class);
        client.start();
        try {
            client.connect(5000, "localhost", 54555,54777);
        } catch (IOException ex) {}
        client.addListener();*/
        
    }
}
