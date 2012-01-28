/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Syynth
 */
public class MultipleInstanceException extends Exception {
    
    private String message;

    public MultipleInstanceException(String string) {
        message = string;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
    
}
