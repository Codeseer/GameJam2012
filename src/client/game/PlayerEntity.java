/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.graphics.RenderData;
import shared.networking.PlayerObject;

/**
 *
 * @author Syynth
 */
public class PlayerEntity extends GameEntity {
    
    private double health;
    
    public PlayerEntity(PlayerObject p)
    {
        super(p);
        health = p.getHealth();
        
        renderData = new RenderData("Yellow", "static", p);
        
    }
    
}
