/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

/**
 * A simple class sent by the client to the server when the player wishes to move something.
 * @author Scott Adams
 */
public class Movement {
    public int destX,destY,objectId;
    
    public Movement(int destX, int destY, int objectId)
    {
        this.destX = destX;
        this.destY = destY;
        this.objectId = objectId;
    }
}
