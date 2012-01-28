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
public class GamestateManager {
    
    static Stack<Gamestate> gamestateStack;
    
    public GamestateManager()
    {
        gamestateStack = new Stack<>();
    }
    
    public void start()
    {
        gamestateStack.push(null);
    }
    
    public void run()
    {
        
    }
    
    public static void add(Gamestate gamestate)
    {
        
    }
}
