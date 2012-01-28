/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.graphics;

import client.resources.ResourceManager;
import client.resources.Resource;

/**
 *
 * @author Syynth
 */
public class RenderData {
    
    private Quad quad;
    private Resource resource;
    
    public RenderData()
    {
        quad = null;
    }
    
    public RenderData(Quad q)
    {
        quad = q;
    }
    
    public RenderData(String rName, String tName)
    {
        resource = ResourceManager.getResourceManager().getResource(rName);
        quad = new Quad(0, 0, resource.getTexture(tName).getImageWidth(),
                resource.getTexture(tName).getImageHeight());
    }
    
    public boolean isBlank()
    {
        //return Resource == null;
        return true;
    }
    
}
