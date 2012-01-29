/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Scott Adams
 */
import client.game.GamestateManager;
import client.graphics.VideoManager;
import client.resources.ResourceManager;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
//import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
//import org.lwjgl.opengl.DisplayMode;

/**
 * @author jediTofu
 * @see <a href="http://lwjgl.org/">LWJGL Home Page</a>
 */
public class Main {

    public static final int DISPLAY_HEIGHT = 720;
    public static final int DISPLAY_WIDTH = 1280;
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    // create client singletons
    static InputManager inputManager = null;  // to be impl.
    static NetworkManager networkManager = null;   // to be impl.
    static ResourceManager resourceManager = null; // to be impl.
    static GamestateManager gamestateManager = null;   // to be impl.
    static AudioManager audioManager = null;   // to be impl.
    static VideoManager videoManager = null;   // to be impl.

    static {
        try {
            LOGGER.addHandler(new FileHandler("errors.log", true));
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, ex.toString(), ex);
        }
    }

    public static void main() {
        Main main = null;

        // create client singletons
        ResourceManager resourceManager = null;
        InputManager inputManager = null;  // to be impl.
        NetworkManager networkManager = null;   // to be impl.
        GamestateManager gamestateManager = null;   // to be impl.
        AudioManager audioManager = null;   // to be impl.
        VideoManager videoManager = null;   // to be impl.

        try {
            System.out.println("Welcome to bloopsClient v0.0");
            main = new Main();
            main.create();
            System.out.println("LWJGL contexts created");
            main.run();
            System.out.println("Main has finished running");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            if (main != null) {
                main.destroy();
            }
        }
    }

    public Main() {
        try {
            gamestateManager = new GamestateManager();
            networkManager = new NetworkManager();
            try {
            videoManager = new VideoManager();
            } catch (LWJGLException e) {}
            resourceManager = new ResourceManager();
            inputManager = new InputManager();
        } catch (MultipleInstanceException m) { }
        
        gamestateManager.start();

    }

    public void create() throws LWJGLException {
        //Display
        
        /*Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        Display.setFullscreen(false);
        Display.setTitle("Hello LWJGL World!");
        Display.create();

        //Keyboard
        Keyboard.create();

        //Mouse
        Mouse.setGrabbed(false);
        Mouse.create();

        //OpenGL
        //initGL();
        //resizeGL();*/
    }

    public void destroy() {
        //Methods already check if created before destroying.
        /*Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();*/
        inputManager.destroy();
        videoManager.destroy();
        
        System.exit(0);
    }
    
    public static void shutdown()
    {
        
    }

    
      public void initGL() { //2D Initialization
      }
    public void run() {

        while (!Display.isCloseRequested()/* && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)*/) {
            if (Display.isVisible()) {
                update();
                videoManager.render();
            } else {
                if (Display.isDirty()) {
                    videoManager.render();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
            Display.update();
            Display.sync(60);
        }
        networkManager.disconnect();
    }

    public void update() {
        gamestateManager.update();
    }
}
