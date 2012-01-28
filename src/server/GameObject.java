/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Scott Adams
 */
public class GameObject {
    public int x,y;
    protected int objectId;
    private boolean TCP;
    private boolean updated;

    public boolean isTCP() {
        return TCP;
    }

    public void setTCP(boolean TCP) {
        this.TCP = TCP;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

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
