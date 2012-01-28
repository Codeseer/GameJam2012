/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.graphics;

import client.MultipleInstanceException;

/**
 *
 * @author Syynth
 */
public final class VideoManager {
    
    private static VideoManager gID;
    
    public VideoManager() throws MultipleInstanceException
    {
        if (gID != null)
            throw new MultipleInstanceException("You can only have one " +
                    " instance of the singleton class VideoManager");
        gID = this;
    }
    
    public static VideoManager getVideoManager()
    {
        return gID;
    }
    
    public static void render(RenderData r)
    {
        
    }
    
}
