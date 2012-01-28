/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.graphics;

/**
 *
 * @author Syynth
 */
public class Quad {
    
    public float x1;
    public float y1;
    public float x2;
    public float y2;
    
    public Quad(float x1, float y1, float x2, float y2)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    
    public float getWidth()
    {
        return x2 - x1;
    }
    
    public float getHeight()
    {
        return y2 - y1;
    }
}
