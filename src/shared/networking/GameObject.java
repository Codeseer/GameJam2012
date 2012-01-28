/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

/**
 *
 * @author Scott Adams
 */
public abstract class GameObject 
{
    
    public int x,y;
    private int objectId;

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
