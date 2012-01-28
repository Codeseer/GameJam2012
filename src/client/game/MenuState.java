/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.NetworkManager;

/**
 *
 * @author Syynth
 */
public class MenuState extends Gamestate {

    @Override
    public void onPush() {
        //throw new UnsupportedOperationException("Not supported yet.");
        NetworkManager.getNetworkManager().start();
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
    
}
