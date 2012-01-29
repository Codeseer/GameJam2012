/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.esotericsoftware.kryonet.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.networking.UpdateResponse;

/**
 *
 * @author Syynth
 */
public class ClientConnectionThread extends Thread
{
    public Connection connection;
    public ClientConnectionThread(Connection con)
    {
        connection = con;
    }
    public void run()
    {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.sendTCP(new UpdateResponse(Main.gameServer.gameObjectManager.getUpdatedObjectsTCP()));
        
    }
}
