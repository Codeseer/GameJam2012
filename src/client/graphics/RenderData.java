/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.graphics;

import client.resources.ResourceManager;
import client.resources.Resource;
import shared.networking.GameObject;

/**
 *
 * @author Syynth
 */
public class RenderData {
    
    private Quad quad;
    private Resource resource;
    private String currentFrame;
    private GameObject object;
    
    public RenderData()
    {
        quad = null;
    }
    
    public RenderData(Quad q)
    {
        quad = q;
    }
    
    public RenderData(String rName, String tName, GameObject ref)
    {
        resource = ResourceManager.getResourceManager().getResource(rName);
        System.out.println(ResourceManager.getResourceManager());
        quad = new Quad(0, 0, resource.getTexture(tName).getImageWidth(),
                resource.getTexture(tName).getImageHeight());
        //quad = new Quad(0, 0, 50, 50);
        currentFrame = tName;
        object = ref;
    }
    
    public String getFrame()
    {
        return currentFrame;
    }
    
    public boolean isBlank()
    {
        return resource == null;
    }
    
    public Resource getResource()
    {
        return resource;
    }
    
    public float getX()
    {
        return (float)object.x;
    }
    
    public float getY()
    {
        return (float)object.y;
    }
    
    public Quad getQuad()
    {
        return quad;
    }
    
}
