/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.NetworkManager;
import client.graphics.VideoManager;
import java.util.ArrayList;
import shared.networking.GameObject;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import shared.networking.Movement;

/**
 *
 * @author Syynth
 */
public class PlayState extends Gamestate {
    
    private GameWorld world;
    private int playerId;

    @Override
    public void onPush() {        
        // tell resource loader to load resources
    }

    @Override
    public void onPop() {
        // tell resource loader to unload resources
    }
    
    public PlayState() {
        world = new GameWorld();
        playerId = 2;
    }

    @Override
    public void update(ArrayList<ArrayList<GameObject>> updates) {
        
        if (Mouse.isButtonDown(0))
        {
            NetworkManager.getNetworkManager().addServerRequest(new Movement(Mouse.getX(), Mouse.getY(), playerId));
        }
         
        for (ArrayList<GameObject> u : updates)
        {
            if(u!=null)
            {
                world.update(u);
            }
            else
            {
                System.out.println("Null update found.");
            }
        }
    }

    @Override
    public void prerender() {
        world.render();
    }

    @Override
    public void render() {
        //VideoManager.getVideoManager().render();
    }
    
}
