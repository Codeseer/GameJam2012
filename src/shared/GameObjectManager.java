/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

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
    
    public GameObjectManager()
    {        
        maxObjectId = 0;
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
}
