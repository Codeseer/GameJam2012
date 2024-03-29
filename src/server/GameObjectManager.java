/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.Iterator;
import shared.networking.GameObject;
import shared.networking.ServerRequest;

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
    private ArrayList<ServerRequest> serverMessages;
    
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
        gameObject.getGameObject().setObjectId(maxObjectId);
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
            if(tmpObj.getGameObject().getObjectId()==objectId)
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
            if(iterator.next().getGameObject().getObjectId()==objectId)
                return true;
        }
        return false;
    }
    
    public ArrayList<GameObject> getUpdatedObjectsTCP()
    {
        if(System.nanoTime()-timeLastUpdate>50000000)
        {
            ArrayList<GameObject> tcpArray = new ArrayList();
            Iterator<GameEntity> iterator = gameObjects.iterator();
            GameEntity tmpGObj;
            for(;iterator.hasNext();)
            {
                tmpGObj = iterator.next();
                if(tmpGObj.isTCP())
                {
                        tcpArray.add(tmpGObj.getGameObject());
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
        if(System.nanoTime()-timeLastUpdate>50000000)
        {
            ArrayList<GameObject> udpArray = new ArrayList();
            Iterator<GameEntity> iterator = gameObjects.iterator();
            GameEntity tmpGObj;
            for(;iterator.hasNext();)
            {
                tmpGObj = iterator.next();
                if(!tmpGObj.isTCP())
                {
                        udpArray.add(tmpGObj.getGameObject());
                }
            }
            udpGameObjects = udpArray;
            timeLastUpdate = System.nanoTime();
            return udpArray;
        }
        else
            return udpGameObjects;
    }
    public synchronized void updateAll()
    {
        Iterator<GameEntity> iterator = gameObjects.iterator();
        GameEntity tmpGObj;
        for(;iterator.hasNext();)
        {
            tmpGObj = iterator.next();
            tmpGObj.update();
        }
    }
    public synchronized void sendMessages()
    {
        Iterator<ServerRequest> iterator = serverMessages.iterator();
        ServerRequest tmpSM;
        for(;iterator.hasNext();)
        {
            tmpSM = iterator.next();
            get(tmpSM.objectId).addMessage(tmpSM);                    
        }
    }
    
    public void addMessage(ServerRequest sm)
    {
        serverMessages.add(sm);
    }
    
    public ArrayList<GameEntity> getAll()
    {
        return gameObjects;
    }
}
