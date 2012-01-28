/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.networking;

/**
 *
 * @author Syynth
 */
public class PlayerObject extends GameObject {
    
    private double health;
    
    public void setHealth(double health)
    {
        health = health < 0 ? 0 : (health > 1 ? 1 : health);
    }
    
    public double getHealth()
    {
        return health;
    }
    
}
