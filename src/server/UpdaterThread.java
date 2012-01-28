/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.esotericsoftware.kryonet.Connection;

/**
 *
 * @author Scott Adams
 */
public class UpdaterThread extends Thread
{
    public Connection con;
    public boolean updating;
    public void setConnection(Connection connection)
    {
        con = connection;
        updating = true;
    }
    
    @Override
    public void run()
    {
       //check ever 50 miliseconds for updated objects and send them to all the connected clients.
        while(updating)
        {
            int numBytes = con.sendTCP(Main.gameServer.gameObjectManager.getUpdatedObjectsUDP());
            
            numBytes = con.sendUDP(Main.gameServer.gameObjectManager.getUpdatedObjectsTCP());
            
            try {
                UpdaterThread.sleep(50);
            } catch (InterruptedException ex) {}
        }
    }
}
