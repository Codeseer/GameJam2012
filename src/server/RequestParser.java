/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import shared.networking.Movement;

/**
 *This class is sent the clients request and then decides what to return as a response(if anything)
 * @author Scott Adams
 */
public class RequestParser {
    private Object response;
    
    public RequestParser(Object request)
    {        
        //to add a request object write the method and then add it to the if else ladder.
        if(request instanceof Movement)
            movementRequest((Movement)request);
    }
    
    private void movementRequest(Movement movement)
    {        
        GameObject gObj = Main.gameServer.gameObjectManager.get(movement.objectId);
        gObj.x = movement.destX;
        gObj.y = movement.destY;
        gObj.setUpdated(true);
    }
    
    
    /**
     * Gets the response that was generated based on the request.
     * @return a response object that should be sent back to the server if not null.
     */
    public Object getResponse()
    {
        return response;
    }
}
