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
public abstract class ServerRequest implements Serializable
{
    public int objectId;
    public ServerRequest(int objectId)
    {
        this.objectId = objectId;
    }
}
