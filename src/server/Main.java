/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Scott Adams
 */
public class Main {
    public static GameServer gameServer;
    public static void main()
    {
        ServerFrame server_frame = new ServerFrame();
        server_frame.setVisible(true);
        gameServer = new GameServer(server_frame.server_output_pane);
    }
}
