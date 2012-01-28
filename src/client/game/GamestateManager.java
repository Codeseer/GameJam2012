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
    
    Stack<Gamestate> gamestateStack;
    
    public GamestateManager()
    {
        gamestateStack = new Stack<Gamestate>();
    }
    
}
