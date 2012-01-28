/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.resources;

import java.util.HashMap;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Scott Adams
 */
public class Resource 
{
    private HashMap textures;
    private HashMap sounds;
    private String name;
    
    public Resource(String name)
    {
        this.name = name;
        textures = new HashMap();
        sounds = new HashMap();
    }
    public String getName() {
        return name;
    }
    public void addTexture(String name,Texture T)
    {
        textures.put(name, T);
    }
    public Texture getTexture(String name)
    {
        return (Texture)textures.get(name);
    }
    
    public void addSound(String name,Audio A)
    {
        sounds.put(name, A);
    }
    public Audio getSound(String name)
    {
        return (Audio)sounds.get(name);
    }
    
    public HashMap getAllSounds()
    {
        return sounds;
    }
    
    public HashMap getAllTextures()
    {
        return textures;
    }
}
