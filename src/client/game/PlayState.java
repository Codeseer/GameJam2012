/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

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
    public void update() {
        world.update();
    }

    @Override
    public void prerender() {
        world.prerender();
    }

    @Override
    public void render() {
        world.render();
    }
    
}
