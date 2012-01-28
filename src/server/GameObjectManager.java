/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import server.ServerGameObject;
import java.util.ArrayList;
import java.util.Iterator;
import shared.networking.GameObject;
import shared.networking.ServerMessage;

/**
 *
 * @author Scott Adams
 */
public class GameObjectManager {
    private ArrayList<ServerGameObject> gameObjects;
    
    //manages ids so no object will ever have the same id.
    private int maxObjectId;
    
    private long timeLastUpdate = 0;
    private ArrayList<GameObject> udpGameObjects;
    private ArrayList<GameObject> tcpGameObjects;
    private ArrayList<ServerMessage> serverMessages;
    
    public GameObjectManager()
    {        
        maxObjectId = 0;
        gameObjects = new ArrayList();
        udpGameObjects = new ArrayList();
        tcpGameObjects = new ArrayList();
        serverMessages = new ArrayList();
    }
    
    /**
     * Assigns a unique Id to the game object and adds it to the collection of game objects.
     * @param gameObject the ServerGameObject to add to the collection of game objects
     */
    public void add(ServerGameObject gameObject)
    {
        gameObject.getGameObject().setObjectId(maxObjectId);
        gameObjects.add(gameObject);
        maxObjectId++;
    }
    
    public boolean remove(ServerGameObject gameObject)
    {
        return gameObjects.remove(gameObject);
    }
    
    public ServerGameObject get(int objectId)
    {
        Iterator<ServerGameObject> iterator = gameObjects.iterator();
        ServerGameObject tmpObj;
        for(;iterator.hasNext();)
        {
            tmpObj = iterator.next();
            if(tmpObj.getGameObject().getObjectId()==objectId)
                return tmpObj;
        }
        return null;
    }
    
    /**
     * Checks to see if an objectId corresponds to a ServerGameObject
     * @param objectId the id to check for
     * @return
     */
    public boolean checkForId(int objectId)
    {
        Iterator<ServerGameObject> iterator = gameObjects.iterator();
        for(;iterator.hasNext();)
        {
            if(iterator.next().getGameObject().getObjectId()==objectId)
                return true;
        }
        return false;
    }
    
    public ArrayList<GameObject> getUpdatedObjectsTCP()
    {
        if(System.nanoTime()-timeLastUpdate>25000)
        {
            ArrayList<GameObject> tcpArray = new ArrayList();
            Iterator<ServerGameObject> iterator = gameObjects.iterator();
            ServerGameObject tmpGObj;
            for(;iterator.hasNext();)
            {
                tmpGObj = iterator.next();
                if(tmpGObj.isUpdated()&&tmpGObj.isTCP())
                {
                        tcpArray.add(tmpGObj.getGameObject());
                        tmpGObj.setUpdated(false);
                }
            }
            tcpGameObjects = tcpArray;
            timeLastUpdate = System.nanoTime();
            return tcpArray;
        }
        else
            return tcpGameObjects;
    }
    
    public ArrayList<GameObject> getUpdatedObjectsUDP()
    {
        if(System.nanoTime()-timeLastUpdate>25000)
        {
            ArrayList<GameObject> udpArray = new ArrayList();
            Iterator<ServerGameObject> iterator = gameObjects.iterator();
            ServerGameObject tmpGObj;
            for(;iterator.hasNext();)
            {
                tmpGObj = iterator.next();
                if(tmpGObj.isUpdated()&&!tmpGObj.isTCP())
                {
                        udpArray.add(tmpGObj.getGameObject());
                        tmpGObj.setUpdated(false);
                }
            }
            udpGameObjects = udpArray;
            timeLastUpdate = System.nanoTime();
            return udpArray;
        }
        else
            return udpGameObjects;
    }
    public void updateAll()
    {
        Iterator<ServerGameObject> iterator = gameObjects.iterator();
        ServerGameObject tmpGObj;
        for(;iterator.hasNext();)
        {
            tmpGObj = iterator.next();
            tmpGObj.update();
        }
    }
    public void sendMessages()
    {
        Iterator<ServerMessage> iterator = serverMessages.iterator();
        ServerMessage tmpSM;
        for(;iterator.hasNext();)
        {
            tmpSM = iterator.next();
            get(tmpSM.objectId).addMessage(tmpSM);                    
        }
    }
    
    public void addMessage(ServerMessage sm)
    {
        serverMessages.add(sm);
    }
}
