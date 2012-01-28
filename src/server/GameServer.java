/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import shared.GameObjectManager;

/**
 *
 * @author Scott Adams
 */
public class GameServer {
    private Server server;
    private StyledDocument server_output_doc;
    public GameObjectManager gameObjectManager;
    public GameServer(JTextPane server_output_pane)
    {
        server_output_doc = server_output_pane.getStyledDocument();
        //setup all the diffrent styles for the server output pane.
        Style style_error = server_output_pane.addStyle("Error", null);
        StyleConstants.setForeground(style_error, Color.red);
        
        final Style style_fatal_error = server_output_pane.addStyle("Fatal Error", style_error);
        StyleConstants.setBold(style_fatal_error, true);
        StyleConstants.setFontSize(style_fatal_error, 18);
        
        Style style_success = server_output_pane.addStyle("Success", null);
        StyleConstants.setForeground(style_success, Color.green);
        
        final Style style_unimportant = server_output_pane.addStyle("Unimportant", null);
        StyleConstants.setForeground(style_unimportant, Color.yellow);
        StyleConstants.setFontSize(style_unimportant, 8);
        
        //create a new GameObjectManager for the server
        gameObjectManager = new GameObjectManager();
        
        
        //Create new kryonet server
        server = new Server();
        server.start();
        try {
            server.bind(54555,54777);
                serverMessage("Successful started server on port 54555(TCP) and 54777(UDP)\n",style_success);
        } catch (IOException ex) {
            serverMessage("Could not connect on port 54555(TCP) or 54777(UDP)",style_fatal_error);
        }
        
        server.addListener(new Listener(){
           
            @Override
            public void received(Connection connection, Object object)
            {
                RequestParser rParser = new RequestParser(object);
                
                int numBytesSent = connection.sendTCP(rParser.getResponse());
                
                serverMessage("position response sent\n\t size "+numBytesSent+" bytes",style_unimportant);
            }
            public void connected(Connection connection)
            {
                //Hello Nick
            }
        });
    }
    
    private void serverMessage(String message, Style style)
    {
        try {
        server_output_doc.insertString(server_output_doc.getLength(), message, style);
        } catch (BadLocationException ex1) {}
        System.out.println(message);
    }
}
