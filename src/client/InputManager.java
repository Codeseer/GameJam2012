/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Syynth
 */
public final class InputManager {
    
    private static InputManager gID = null;
    
    public InputManager() throws MultipleInstanceException
    {
        if (gID != null)
            throw new MultipleInstanceException("You can only have one " +
                    " instance of the singleton class InputManager");
        gID = this;
        
        initHID();
    }
    
    private void initHID()
    {
        try {
            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException e) {}
    }
    
    public void destroy()
    {
        Keyboard.destroy();
        Mouse.destroy();
    }
    
    public static InputManager getInputManager()
    {
        return gID;
    }
    
}
