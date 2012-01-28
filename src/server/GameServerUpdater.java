/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Scott Adams
 */
public class GameServerUpdater extends Thread
{
    private GameObjectManager gom;
    public boolean updating = false;
    public void setGameManagerObject(GameObjectManager gom)
    {
        this.gom = gom;
        updating = true;
    }
    @Override
    public void run()
    {
        while(updating)
        {
            gom.sendMessages();
            gom.updateAll();
        }
    }
}
