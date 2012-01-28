/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Kryo.Listener;
import com.esotericsoftware.kryonet.Client;
import java.io.IOException;
import java.util.ArrayList;
import shared.networking.ServerMessage;
import shared.networking.UpdateRequest;

/**
 *
 * @author Syynth
 */
public final class NetworkManager extends Thread {
    
    private static NetworkManager gID = null;
    public Client client;
    
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
        kryo.register(ServerMessage.class);
        client.start();
        try {
            client.connect(5000, "localhost", 54555,54777);
        } catch (IOException ex) {}
        //client.addListener();
    }
    
    public void addUpdateRequest()
    {
        if (client.isConnected())
        {
            client.sendTCP(new UpdateRequest());
        }
    }
    
    public static NetworkManager getNetworkManager()
    {
        return gID;
    }
    
}
