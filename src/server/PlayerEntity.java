/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import shared.networking.Movement;
import shared.networking.PlayerObject;
import shared.networking.ServerRequest;

/**
 *
 * @author Syynth
 */
public class PlayerEntity extends GameEntity {
    
    public int owner;
    private PlayerObject po;
    public void setGameObject(PlayerObject playerObject)
    {
        super.setGameObject(playerObject);
        po = playerObject;
    }
    public void update()
    {
        
        for(ServerRequest m : messages)
        {
            if(m instanceof Movement)
            {
                Movement mov = (Movement)m;
                if(mov.clientId == owner)
                {                    
                    this.getGameObject().x = mov.destX;
                    this.getGameObject().y = mov.destY;
                }
            }
        }
    }
}
