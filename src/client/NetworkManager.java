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
            client = new Client();
            
            Kryo kryo = client.getKryo();
            kryo.register(Object.class);
            kryo.register(UpdateRequest.class);
            //kryo.register(UpdateResponse.class,new ClassSerializer(kryo));
            kryo.register(ServerRequest.class,new ClassSerializer(kryo));
            kryo.register(GameObject.class);
            kryo.register(ArrayList.class, new CollectionSerializer(kryo));
            kryo.register(PlayerObject.class);

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
                }

                @Override
                public void disconnected(Connection c)
                {
                    //System.out.println("qwerqwerqwer" + c);
                }

                @Override
                public void received(Connection c, Object o)
                {
                    if (o instanceof UpdateResponse)
                    {
                        GamestateManager.getGamestateManager().addToUpdateQueue(
                                ((UpdateResponse)o));
                    }
                    addUpdateRequest();
                }
            });
            try {
                client.connect(5000, "localhost", 54555,54777);
                System.out.println("Connect");
            } catch (IOException ex) {}
            System.out.println("asdfasdfasdf");
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
    
    public void addServerMessage(ServerRequest s)
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
