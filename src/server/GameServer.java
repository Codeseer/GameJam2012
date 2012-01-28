/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Scott Adams
 */
public final class GameServer {
    private Server server;
    private StyledDocument server_output_doc;
    public final GameObjectManager gameObjectManager;
    public final Style style_error,style_fatal_error,style_success,style_unimportant;
    private ArrayList<UpdaterThread> updaterThreads;
    
    public GameServer(JTextPane server_output_pane)
    {
        server_output_doc = server_output_pane.getStyledDocument();
        //setup all the diffrent styles for the server output pane.
        
        style_error = server_output_pane.addStyle("Error", null);
        StyleConstants.setForeground(style_error, Color.red);
        
        style_fatal_error = server_output_pane.addStyle("Fatal Error", style_error);
        StyleConstants.setBold(style_fatal_error, true);
        StyleConstants.setFontSize(style_fatal_error, 18);
        
        style_success = server_output_pane.addStyle("Success", null);
        StyleConstants.setForeground(style_success, Color.green);
        
        style_unimportant = server_output_pane.addStyle("Unimportant", null);
        StyleConstants.setForeground(style_unimportant, Color.yellow);
        StyleConstants.setFontSize(style_unimportant, 12);
        
        //create a new GameObjectManager for the server
        gameObjectManager = new GameObjectManager();
        
        updaterThreads = new ArrayList();
        //Create new kryonet server
        server = new Server();
        
        //Register classes with kryo
        Kryo kryo = server.getKryo();
        kryo.register(ArrayList.class);
        
        server.start();
        try {
            server.bind(54555,54777);
                serverMessage("Successful started server on port 54555(TCP) and 54777(UDP)\n",style_success);
        } catch (IOException ex) {
            serverMessage("Could not connect on port 54555(TCP) or 54777(UDP)\n",style_fatal_error);
        }
        
        server.addListener(new Listener(){
           
            @Override
            public void received(Connection connection, Object object)
            {
                RequestParser rParser = new RequestParser(object);
                                
                serverMessage("Request Recieved from "+connection.getRemoteAddressTCP()+"\n",style_unimportant);
            }
            @Override
            public void connected(Connection connection)
            {
                UpdaterThread uThread = new UpdaterThread();
                uThread.setConnection(connection);
                uThread.start();
                updaterThreads.add(uThread);
                serverMessage("Connection Established from client\n",style_unimportant);
            }
            
            @Override
            public void disconnected(Connection connection)
            {
                                        serverMessage("Connection terminated from client: "+connection.getRemoteAddressTCP(),style_unimportant);

                UpdaterThread tmpUThread;
                Iterator<UpdaterThread> iterator = updaterThreads.iterator();
                for(;iterator.hasNext();)
                {
                    tmpUThread = iterator.next();
                    if (tmpUThread.con.getID()==connection.getID())
                    {
                        updaterThreads.remove(tmpUThread);
                        tmpUThread.updating = false;
                        tmpUThread = null;
                        serverMessage("Connection terminated from client: "+tmpUThread.con.getRemoteAddressTCP(),style_unimportant);
                        break;
                    }
                }
                
            }
        });
    }
    
    public void serverMessage(String message, Style style)
    {
        try {
        server_output_doc.insertString(server_output_doc.getLength(), message, style);
        } catch (BadLocationException ex1) {}
        System.out.println(message);
    }
}
