/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

import java.io.Serializable;

/**
 *
 * @author Scott Adams
 */
public abstract class GameObject implements Serializable
{
    
    public int x,y;
    protected int objectId;

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
