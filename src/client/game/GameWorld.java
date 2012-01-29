/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.NetworkManager;
import client.graphics.VideoManager;
import java.util.ArrayList;
import shared.networking.GameObject;
import shared.networking.PlayerObject;

/**
 *
 * @author Syynth
 */
public class GameWorld {
    
    ArrayList<GameEntity> gameEntities;
    
    public GameWorld()
    {
        gameEntities = new ArrayList<>();
        /*PlayerObject p = new PlayerObject();
        p.x = 200; p.y = 200; p.setObjectId(0);
        gameEntities.add(new PlayerEntity(p));*/
    }
    
    public void update(ArrayList<GameObject> updates)
    {
        for (GameObject g : updates)
        {
            boolean exists = false;
            for (GameEntity e : gameEntities)
            {
                if (e.object.getObjectId() == g.getObjectId())
                {
                    exists = true;
                    e.object = g;
                    System.out.println(e.object.x);
                }
            }
            if (!exists)
            {
                if (g instanceof PlayerObject)
                {
                    PlayerObject po = (PlayerObject)g;
                    gameEntities.add(new PlayerEntity(po));
                    if (po.owner == NetworkManager.getNetworkManager().client.getID())
                    {
                        PlayState.playerId = po.getObjectId();
                    }
                }
            }
        }
    }
    
    public void render()
    {
        VideoManager.getVideoManager().newFrame();
        for (GameEntity g : gameEntities)
        {
            VideoManager.getVideoManager().addQuad(g.renderData);
        }
    }
    
}
