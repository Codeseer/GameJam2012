/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Scott Adams
 */
public class UpdateResponse implements Serializable
{
    public ArrayList<GameObject> gameObjects;
    public UpdateResponse()
    {
        
    }
    public UpdateResponse(ArrayList<GameObject> gameObjects)
    {
        this.gameObjects = gameObjects;
    }
}
