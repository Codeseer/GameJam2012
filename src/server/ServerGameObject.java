/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import shared.networking.GameObject;
import shared.networking.ServerMessage;

/**
 *
 * @author Scott Adams
 */
public class ServerGameObject extends GameObject{
    private boolean TCP;
    private boolean updated;
    private ArrayList<ServerMessage> messages;

    public boolean isTCP() {
        return TCP;
    }

    public void setTCP(boolean TCP) {
        this.TCP = TCP;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }  
    
    public void addMessage(ServerMessage sm)
    {
        messages.add(sm);
    }
    
    public void update()
    {
        //add your game logic here
    }
    
    public void clearMessages()
    {
        messages.clear();
    }
}
