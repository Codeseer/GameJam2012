/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

/**
 * A simple class sent by the client to the server when the player wishes to move something.
 * @author Scott Adams
 */
public class Movement extends ServerMessage 
{
    public int destX,destY;
    
    public Movement(int destX, int destY, int objectId)
    {
        super(objectId);
        this.destX = destX;
        this.destY = destY;
    }
}
