/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import shared.networking.GameObject;
import shared.networking.ServerRequest;

/**
 *
 * @author Scott Adams
 */
public class GameEntity{
    private boolean TCP = true;
    private boolean updated;
    private GameObject gameObject;
    private ArrayList<ServerRequest> messages;

    public GameObject getGameObject() {
    return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
    
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
    
    public void addMessage(ServerRequest sm)
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
