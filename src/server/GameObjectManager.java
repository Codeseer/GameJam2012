/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import server.GameObject;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Scott Adams
 */
public class GameObjectManager {
    private ArrayList<GameObject> gameObjects;
    
    //manages ids so no object will ever have the same id.
    private int maxObjectId;
    
    private long timeLastUpdate = 0;
    private ArrayList<GameObject> udpGameObjects;
    private ArrayList<GameObject> tcpGameObjects;
    public GameObjectManager()
    {        
        maxObjectId = 0;
        gameObjects = new ArrayList();
        udpGameObjects = new ArrayList();
        tcpGameObjects = new ArrayList();
    }
    
    /**
     * Assigns a unique Id to the game object and adds it to the collection of game objects.
     * @param gameObject the GameObject to add to the collection of game objects
     */
    public void add(GameObject gameObject)
    {
        gameObject.setObjectId(maxObjectId);
        gameObjects.add(gameObject);
        maxObjectId++;
    }
    
    public boolean remove(GameObject gameObject)
    {
        return gameObjects.remove(gameObject);
    }
    
    public GameObject get(int objectId)
    {
        Iterator<GameObject> iterator = gameObjects.iterator();
        GameObject tmpObj;
        for(;iterator.hasNext();)
        {
            tmpObj = iterator.next();
            if(tmpObj.getObjectId()==objectId)
                return tmpObj;
        }
        return null;
    }
    
    /**
     * Checks to see if an objectId corresponds to a GameObject
     * @param objectId the id to check for
     * @return
     */
    public boolean checkForId(int objectId)
    {
        Iterator<GameObject> iterator = gameObjects.iterator();
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
            Iterator<GameObject> iterator = gameObjects.iterator();
            GameObject tmpGObj;
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
            Iterator<GameObject> iterator = gameObjects.iterator();
            GameObject tmpGObj;
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
}
