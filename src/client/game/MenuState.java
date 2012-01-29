/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.ConnectionSuccessful;
import client.NetworkManager;
import java.util.ArrayList;
import shared.networking.GameObject;

/**
 *
 * @author Syynth
 */
public class MenuState extends Gamestate implements ConnectionSuccessful {

    @Override
    public void onPush() {
        NetworkManager.getNetworkManager().addConnectRequest(this);
    }

    @Override
    public void onPop() {
        
    }

    @Override
    public void create() {
    }

    @Override
    public void update(ArrayList<ArrayList<GameObject>> updates) {
    }

    @Override
    public void render() {
    }

    @Override
    public void prerender() {
    }

    @Override
    public void connectedSuccessfully() {
        GamestateManager.getGamestateManager().pushGamestate(new PlayState());
    }
    
}
