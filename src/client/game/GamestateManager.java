/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import java.util.Stack;

/**
 *
 * @author Syynth
 */
public final class GamestateManager {
    
    private static Stack<Gamestate> gamestateStack;
    
    public GamestateManager()
    {
        gamestateStack = new Stack<>();
    }
    
    public void start()
    {
        gamestateStack.push(new MenuState());
    }
    
    public void pushGamestate(Gamestate gamestate)
    {
        gamestateStack.push(gamestate).onPush();
    }
    
    public void popGamestate()
    {
        gamestateStack.pop().onPop();
    }
    
    public void update()
    {
        gamestateStack.peek().update();
        gamestateStack.peek().prerender();
        gamestateStack.peek().render();
    }
}
