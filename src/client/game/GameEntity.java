/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.graphics.RenderData;
import shared.networking.GameObject;

/**
 *
 * @author Syynth
 */
public class GameEntity {
    
    public GameObject object;
    public RenderData renderData;
    private boolean visible;
    private int objectId;
    
    public GameEntity(GameObject g)
    {
        objectId = g.getObjectId();
        object = g;
    }
    
    public void assignRenderData()
    {
        
    }
    
    public void update()
    {
        
    }
    
    public void render()
    {
        if (visible)
        {
            
        }
    }
    
}
