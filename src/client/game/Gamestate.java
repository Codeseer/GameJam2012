/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import java.util.ArrayList;
import shared.networking.GameObject;

/**
 *
 * @author Syynth
 */
public abstract class Gamestate {
    
    public abstract void onPush();
    public abstract void onPop();
    public abstract void update(ArrayList<ArrayList<GameObject>> updates);
    
    public abstract void prerender();
    public abstract void render();
    
}
