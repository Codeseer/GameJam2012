/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.NetworkManager;
import client.graphics.VideoManager;
import java.util.ArrayList;
import shared.networking.UpdateResponse;

/**
 *
 * @author Syynth
 */
public class PlayState extends Gamestate {
    
    private GameWorld world;

    @Override
    public void onPush() {
        // tell resource loader to load resources
    }

    @Override
    public void onPop() {
        // tell resource loader to unload resources
    }

    @Override
    public void create() {
        world = new GameWorld();
    }

    @Override
    public void update(ArrayList<UpdateResponse> updates) {
        NetworkManager.getNetworkManager().addUpdateRequest();
        for (UpdateResponse u : updates)
        {
            world.update(u.gameObjects);
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
