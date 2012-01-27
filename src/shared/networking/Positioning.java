/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

/**
 * A simple class sent from the server to the client to update the position data for the object.
 * @author Scott Adams
 */
public class Positioning {
    public int x,y,objectId;
    
    public Positioning(int x,int y,int objectId)
    {
        this.x = x;
        this.y = y;
        this.objectId = objectId;
    }
}
