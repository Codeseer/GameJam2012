/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import java.util.ArrayList;
import shared.networking.UpdateResponse;

/**
 *
 * @author Syynth
 */
public abstract class Gamestate {
    
    public abstract void onPush();
    public abstract void onPop();
    //public abstract void create();
    public abstract void update(ArrayList<UpdateResponse> updates);
    
    public abstract void prerender();
    public abstract void render();
    
}
