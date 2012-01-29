/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.ConnectionSuccessful;
import client.NetworkManager;
import java.util.ArrayList;
import shared.networking.UpdateRequest;
import shared.networking.UpdateResponse;

/**
 *
 * @author Syynth
 */
public class MenuState extends Gamestate implements ConnectionSuccessful {

    @Override
    public void onPush() {
        //NetworkManager.getNetworkManager().addConnectRequest(this);
        GamestateManager.getGamestateManager().pushGamestate(new PlayState());
    }

    @Override
    public void onPop() {
        
    }

    public MenuState() {
    }

    @Override
    public void update(ArrayList<UpdateResponse> updates) {
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
