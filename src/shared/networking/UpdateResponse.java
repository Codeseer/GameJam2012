/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

import java.util.ArrayList;

/**
 *
 * @author Scott Adams
 */
public class UpdateResponse
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
