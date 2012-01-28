/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.Iterator;
import shared.networking.GameObject;
import shared.networking.ServerMessage;

/**
 *
 * @author Scott Adams
 */
public class GameObjectManager {
    private ArrayList<GameEntity> gameObjects;
    
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
     * @param gameObject the GameEntity to add to the collection of game objects
     */
    public void add(GameEntity gameObject)
    {
        gameObject.setObjectId(maxObjectId);
        gameObjects.add(gameObject);
        maxObjectId++;
    }
    
    public boolean remove(GameEntity gameObject)
    {
        return gameObjects.remove(gameObject);
    }
    
    public GameEntity get(int objectId)
    {
        Iterator<GameEntity> iterator = gameObjects.iterator();
        GameEntity tmpObj;
        for(;iterator.hasNext();)
        {
            tmpObj = iterator.next();
            if(tmpObj.getObjectId()==objectId)
                return tmpObj;
        }
        return null;
    }
    
    /**
     * Checks to see if an objectId corresponds to a GameEntity
     * @param objectId the id to check for
     * @return
     */
    public boolean checkForId(int objectId)
    {
        Iterator<GameEntity> iterator = gameObjects.iterator();
        for(;iterator.hasNext();)
        {
            if(iterator.next().getObjectId()==objectId)
                return true;
        }
        return false;
    }
    
    public ArrayList<GameObject> getUpdatedObjectsTCP()
    {
        if(System.currentTimeMillis()-timeLastUpdate>50)
        {
            ArrayList<GameObject> tcpArray = new ArrayList();
            Iterator<GameEntity> iterator = gameObjects.iterator();
            GameEntity tmpGObj;
            for(;iterator.hasNext();)
            {
                tmpGObj = iterator.next();
                if(tmpGObj.isUpdated()&&tmpGObj.isTCP())
                {
                        tcpArray.add(tmpGObj);
                        tmpGObj.setUpdated(false);
                }
            }
            tcpGameObjects = tcpArray;
            timeLastUpdate = System.currentTimeMillis();
            return tcpArray;
        }
        else
            return tcpGameObjects;
    }
    
    public ArrayList<GameObject> getUpdatedObjectsUDP()
    {
        if(System.currentTimeMillis()-timeLastUpdate>50)
        {
            ArrayList<GameObject> udpArray = new ArrayList();
            Iterator<GameEntity> iterator = gameObjects.iterator();
            GameEntity tmpGObj;
            for(;iterator.hasNext();)
            {
                tmpGObj = iterator.next();
                if(tmpGObj.isUpdated()&&!tmpGObj.isTCP())
                {
                        udpArray.add(tmpGObj);
                        tmpGObj.setUpdated(false);
                }
            }
            udpGameObjects = udpArray;
            timeLastUpdate = System.currentTimeMillis();
            return udpArray;
        }
        else
            return udpGameObjects;
    }
    public void updateAll()
    {
        Iterator<GameEntity> iterator = gameObjects.iterator();
        GameEntity tmpGObj;
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
