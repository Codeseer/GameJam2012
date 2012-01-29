/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

/**
 *
 * @author Scott Adams
 */
public abstract class ServerRequest
{
    public int objectId,clientId;
    public ServerRequest(int objectId,int clientId)
    {
        this.clientId = clientId;
        this.objectId = objectId;
    }
}
