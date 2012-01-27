package launcher;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scott Adams
 */
public class Launcher {
    public static void main(String [] args)
    {
        if(args.length>0)
        {
            if(args[0].equalsIgnoreCase("client"))
            {
                client.Main.main();
            }
            else if(args[0].equalsIgnoreCase("server"))
            {
                server.Main.main();
            }
        }
        else
        {
            LauncherFrame launcher_frame = new LauncherFrame();
            launcher_frame.setVisible(true);
        }
    }
}
