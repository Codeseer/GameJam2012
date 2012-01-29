/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game;

import client.MultipleInstanceException;
import java.util.ArrayList;
import java.util.Stack;
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
    
    public synchronized void start()
    {
        pushGamestate(new MenuState());
    }
    
    public synchronized void pushGamestate(Gamestate gamestate)
    {
        gamestateStack.push(gamestate).create();
        gamestateStack.peek().onPush();
    }
    
    public synchronized void popGamestate()
    {
        gamestateStack.pop().onPop();
    }
    
    public synchronized void addToUpdateQueue(UpdateResponse a)
    {
        updateQueue.add(a);
    }
    
    public synchronized void update()
    {
        if (!gamestateStack.empty())
        {
            gamestateStack.peek().update(updateQueue);
            gamestateStack.peek().prerender();
            gamestateStack.peek().render();
        }
    }
    
    public synchronized static GamestateManager getGamestateManager()
    {
        return gID;
    }
}
