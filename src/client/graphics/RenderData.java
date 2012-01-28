/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.graphics;

import client.resources.ResourceManager;

/**
 *
 * @author Syynth
 */
public class RenderData {
    
    private Quad quad;
    //private Resource;
    
    public RenderData()
    {
        quad = null;
    }
    
    public RenderData(Quad q)
    {
        quad = q;
    }
    
    public boolean isBlank()
    {
        //return Resource == null;
        return true;
    }
    
}
