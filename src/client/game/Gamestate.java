/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

/**
 *
 * @author Syynth
 */
public abstract class Gamestate {
    
    public abstract void onPush();
    public abstract void onPop();
    public abstract void create();
    public abstract void update();
    public abstract void render();
    
}
