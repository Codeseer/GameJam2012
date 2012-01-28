/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Scott Adams
 */
public class Resource 
{
    private HashMap textures;
    private HashMap sounds;
    private HashMap rTextures;
    private HashMap rSounds;
    private String name;
    
    public Resource(String name)
    {
        this.name = name;
        textures = new HashMap();
        sounds = new HashMap();
        rTextures = new HashMap();
        rSounds = new HashMap();
    }
    public String getName() {
        return name;
    }
    public void addTexture(String name, InputStream resourceStream)
    {
        rTextures.put(name, resourceStream);
    }
    public Texture getTexture(String name)
    {
        if(textures.containsKey(name))
        {
            return (Texture)textures.get(name);
        }
        else
        {
            if(rTextures.containsKey(name))
            {
                InputStream tmpResource = (InputStream)rTextures.get(name);
                try {
                    Texture newTexture = TextureLoader.getTexture(name,tmpResource);
                    textures.put(name,newTexture);
                    return newTexture;
                } catch (IOException ex) {
                    System.err.println("Could not load "+name+" texture");
                }
            }
        }
        return null;
    }
    
    public void addSound(String name,InputStream resourceStream)
    {
        rSounds.put(name, resourceStream);
    }
    public Audio getSound(String name)
    {
        if(sounds.containsKey(name))
        {
            return (Audio)sounds.get(name);
        }
        else
        {
            if(rSounds.containsKey(name))
            {
                InputStream tmpResource = (InputStream)rSounds.get(name);
                try {
                    Audio newSound = AudioLoader.getAudio(name,tmpResource);
                    sounds.put(name,newSound);
                    return newSound;
                } catch (IOException ex) {
                    System.err.println("Could not load "+name+" audio");
                }
            }
        }
        return null;
    }
    
    public HashMap getAllLoadedSounds()
    {
        return sounds;
    }
    
    public HashMap getAllLoadedTextures()
    {
        return textures;
    }
}
