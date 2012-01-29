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
    private GameObject gameObject;
    protected ArrayList<ServerRequest> messages;
    protected int destX,destY;
    public GameObject getGameObject() {
    return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
        messages = new ArrayList();
    }
    
    public boolean isTCP() {
        return TCP;
    }

    public void setTCP(boolean TCP) {
        this.TCP = TCP;
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
