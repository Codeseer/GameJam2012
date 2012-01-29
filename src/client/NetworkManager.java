/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.game.GamestateManager;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serialize.ClassSerializer;
import com.esotericsoftware.kryo.serialize.CollectionSerializer;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.ArrayList;
import shared.networking.*;

/**
 *
 * @author Syynth
 */
public final class NetworkManager {
    
    private static NetworkManager gID = null;
    public Client client;
    private Callback callback;
    private NetworkConnection nc;
    
    private class NetworkConnection extends Thread
    {
        @Override
        public void run()
        {            
            Kryo kryo = client.getKryo();
            new KryoNetworking(kryo);

            client.start();
            client.addListener(new Listener()
            {
                @Override
                public void connected(Connection c)
                {
                    if (callback instanceof ConnectionSuccessful)
                    {
                        ((ConnectionSuccessful)(callback)).connectedSuccessfully();
                    }
                    addUpdateRequest();
                }

                @Override
                public void disconnected(Connection c)
                {
                    //System.out.println("qwerqwerqwer" + c);
                }

                @Override
                public void received(Connection c, Object o)
                {
                    if (o instanceof ArrayList)
                    {
                        GamestateManager.getGamestateManager().addToUpdateQueue(
                                ((ArrayList)o));
                    }
                }
            });
            try {
                client.connect(5000, "localhost", 54555,54777);
            } catch (IOException ex) {}
        }
    }
    
    public NetworkManager() throws MultipleInstanceException
    {
        super();
        if (gID != null)
            throw new MultipleInstanceException("You can only have one " +
                    " instance of the singleton class NetworkManager");
        gID = this;
        nc = new NetworkConnection();
        client = new Client();
    }
    
    public void disconnect()
    {
       // client.close();
    }
    
    public void addUpdateRequest()
    {
        if (client.isConnected())
        {
            client.sendTCP(new UpdateRequest());
        }
    }
    
    public void addServerRequest(ServerRequest s)
    {
        if (client.isConnected())
        {
            client.sendTCP(s);
        }
    }
    
    public void addConnectRequest(ConnectionSuccessful c)
    {
        nc.start();
        callback = c;
    }
    
    public static NetworkManager getNetworkManager()
    {
        return gID;
    }
    
}
