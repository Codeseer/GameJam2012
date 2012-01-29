/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.graphics;

import client.MultipleInstanceException;
import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;

/**
 *
 * @author Syynth
 */
public final class VideoManager {
    
    private static VideoManager gID;
    
    private static final int DISPLAY_WIDTH = 320;
    private static final int DISPLAY_HEIGHT = 240;
    
    private static ArrayList<RenderData> renderQuads;
    
    public VideoManager() throws MultipleInstanceException, LWJGLException
    {
        if (gID != null)
            throw new MultipleInstanceException("You can only have one " +
                    " instance of the singleton class VideoManager");
        gID = this;
        
        renderQuads = new ArrayList<>();
        
        Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        Display.setFullscreen(false);
        Display.setTitle("bloops Client v0.0");
        Display.create();
        
        initGL();
    }
    
    private void initGL()
    {
        glEnable(GL_TEXTURE_2D);
        glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        glViewport(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }
    
    public static VideoManager getVideoManager()
    {
        return gID;
    }
    
    public void newFrame()
    {
        renderQuads = new ArrayList<>();
    }
    
    public void addQuad(RenderData r)
    {
        renderQuads.add(r);
    }
    
    public void render()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        Color.white.bind();
        for (RenderData r : renderQuads)
        {
            r.getResource().getTexture(r.getFrame()).bind();
            
            glBegin(GL_QUADS);
            
            glTexCoord2f(0, 0);
            glVertex2f(r.getX(), r.getY());
            
            glTexCoord2f(1, 0);
            glVertex2f(r.getX() + r.getQuad().getWidth(), r.getY());
            
            glTexCoord2f(1, 1);
            glVertex2f(r.getX() + r.getQuad().getWidth(),
                    r.getY() + r.getQuad().getHeight());
            
            glTexCoord2f(0, 1);
            glVertex2f(r.getX(), r.getY() + r.getQuad().getHeight());
            
            glEnd();
            
        }
    }
    
    public void destroy()
    {
        
    }
    
}
