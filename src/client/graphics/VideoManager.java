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
    
    private VideoManager gID;
    
    public VideoManager() throws MultipleInstanceException
    {
        if (gID != null)
            throw new MultipleInstanceException("You can only have one " +
                    " instance of the singleton class VideoManager");
    }
    
}
