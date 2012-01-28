/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.ConnectionSuccessful;
import client.NetworkManager;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void prerender() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void connectedSuccessfully() {
        GamestateManager.getGamestateManager().pushGamestate(new PlayState());
    }
    
}
