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
    protected Connection con;
    public UpdaterThread(Connection connection)
    {
        con = connection;
    }
    
    @Override
    public void run()
    {
       
    }
}
