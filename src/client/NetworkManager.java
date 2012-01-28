/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.game.GamestateManager;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.ArrayList;
import shared.networking.GameObject;
import shared.networking.ServerRequest;
import shared.networking.UpdateRequest;
import shared.networking.UpdateResponse;

/**
 *
 * @author Syynth
 */
public final class NetworkManager extends Thread {
    
    private static NetworkManager gID = null;
    public Client client;
    private Callback callback;
    
    public NetworkManager() throws MultipleInstanceException
    {
        if (gID != null)
            throw new MultipleInstanceException("You can only have one " +
                    " instance of the singleton class NetworkManager");
    }
    
    @Override
    public void run()
    {
        client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(ArrayList.class);
        kryo.register(UpdateRequest.class);
        kryo.register(UpdateResponse.class);
        kryo.register(ServerRequest.class);
        client.start();
        try {
            client.connect(5000, "localhost", 54555,54777);
        } catch (IOException ex) {}
        client.addListener(new Listener()
        {
            @Override
            public void connected(Connection c)
            {
                if (callback instanceof ConnectionSuccessful)
                {
                    ((ConnectionSuccessful)(callback)).connectedSuccessfully();
                }
            }
            
            @Override
            public void disconnected(Connection c)
            {
                
            }
            
            @Override
            public void received(Connection c, Object o)
            {
                if (o instanceof ArrayList)
                {
                    GamestateManager.getGamestateManager().addToUpdateQueue(
                            ((ArrayList<GameObject>)o));
                }
            }
        });
    }
    
    public void addUpdateRequest()
    {
        if (client.isConnected())
        {
            client.sendTCP(new UpdateRequest());
        }
    }
    
    public void addServerMessage(ServerRequest s)
    {
        if (client.isConnected())
        {
            client.sendTCP(s);
        }
    }
    
    public void addConnectRequest(ConnectionSuccessful c)
    {
        start();
        callback = c;
    }
    
    public static NetworkManager getNetworkManager()
    {
        return gID;
    }
    
}
