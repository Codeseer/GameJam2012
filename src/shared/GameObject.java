/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 *
 * @author Scott Adams
 */
public class GameObject {
    public int x,y;
    protected int objectId;
    public GameObject()
    {}
    
    public int getObjectId()
    {
        return objectId;
    }
    
    void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }
}
