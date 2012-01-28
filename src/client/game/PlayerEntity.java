/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

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
    }
    
}
