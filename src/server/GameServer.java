/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serialize.ClassSerializer;
import com.esotericsoftware.kryo.serialize.CollectionSerializer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import shared.networking.*;

/**
 *
 * @author Scott Adams
 */
public final class GameServer {
    private Server server;
    private StyledDocument server_output_doc;
    public final GameObjectManager gameObjectManager;
    private GameServerUpdater gameServerUpdater;
    public final Style style_error,style_fatal_error,style_success,style_unimportant;
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
        gameServerUpdater = new GameServerUpdater();
        gameServerUpdater.setGameManagerObject(gameObjectManager);
        gameServerUpdater.start();
        //Create new kryonet server
        server = new Server();
        
        
        //Register classes with kryo
        Kryo kryo = server.getKryo();
            KryoNetworking kryoNetworking = new KryoNetworking(kryo);
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
                if(object instanceof UpdateRequest)
                {
                    serverMessage("Update Request Recieved from "+connection.getRemoteAddressTCP()+"\n",style_unimportant);
                    ArrayList response = Main.gameServer.gameObjectManager.getUpdatedObjectsTCP();
                    int numBytes = connection.sendTCP(response);
                    serverMessage("Sent Response of size "+numBytes+"bytes to "+connection.getRemoteAddressTCP()+"\n",style_unimportant);
                }
                else if(object instanceof ServerRequest)
                {
                    Main.gameServer.gameObjectManager.addMessage((ServerRequest)object);
                    serverMessage("Server Request Recieved from "+connection.getRemoteAddressTCP()+"\n",style_unimportant);
                }
                connection.sendTCP(Main.gameServer.gameObjectManager.getUpdatedObjectsTCP());
            }
            @Override
            public void connected(Connection connection)
            {
                PlayerEntity newPlayer = new PlayerEntity();
                newPlayer.owner = connection.getID();
                newPlayer.setGameObject(new PlayerObject());
                newPlayer.getGameObject().x = (int)(Math.random()*700);
                newPlayer.getGameObject().y = (int)(Math.random()*500);
                Main.gameServer.gameObjectManager.add(newPlayer);
                serverMessage("Connection Established from client "+connection.getRemoteAddressTCP()+"\n",style_unimportant);
            }
            
            @Override
            public void disconnected(Connection connection)
            {
                serverMessage("Connection terminated from client.\n",style_unimportant);
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
