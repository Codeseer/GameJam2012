/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.MultipleInstanceException;
import java.util.ArrayList;
import java.util.Stack;
import shared.networking.GameObject;
import shared.networking.UpdateResponse;

/**
 *
 * @author Syynth
 */
public final class GamestateManager {
    
    private static Stack<Gamestate> gamestateStack;
    static ArrayList<UpdateResponse> updateQueue;
    private static GamestateManager gID = null;
    
    public GamestateManager() throws MultipleInstanceException
    {
        gamestateStack = new Stack<>();
        updateQueue = new ArrayList<>();
        if (gID != null)
            throw new MultipleInstanceException("You can only have one " +
                    " instance of the singleton class GamestateManager");
        gID = this;
    }
    
    public void start()
    {
        gamestateStack.push(new MenuState());
    }
    
    public void pushGamestate(Gamestate gamestate)
    {
        gamestateStack.push(gamestate).create();
        gamestateStack.peek().onPush();
    }
    
    public void popGamestate()
    {
        gamestateStack.pop().onPop();
    }
    
    public void addToUpdateQueue(UpdateResponse a)
    {
        updateQueue.add(a);
    }
    
    public void update()
    {
        if (!gamestateStack.empty())
        {
            gamestateStack.peek().update(updateQueue);
            gamestateStack.peek().prerender();
            gamestateStack.peek().render();
        }
    }
    
    public static GamestateManager getGamestateManager()
    {
        return gID;
    }
}
