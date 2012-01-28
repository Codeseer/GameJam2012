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
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * @author jediTofu
 * @see <a href="http://lwjgl.org/">LWJGL Home Page</a>
 */
public class Main {

    public static final int DISPLAY_HEIGHT = 480;
    public static final int DISPLAY_WIDTH = 640;
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    // create client singletons
    static InputManager input = null;  // to be impl.
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

        try {
            System.out.println("Welcome to bloopsClient v0.0");
            main = new Main();
            main.create();
            main.run();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            if (main != null) {
                main.destroy();
            }
        }
    }

    public Main() {

        gamestateManager = new GamestateManager();

    }

    public void create() throws LWJGLException {
        //Display
        Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
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
        //resizeGL();
    }

    public void destroy() {
        //Methods already check if created before destroying.
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
    }

    /*
     * public void initGL() { //2D Initialization
     * glClearColor(0.0f,0.0f,0.0f,0.0f); glDisable(GL_DEPTH_TEST);
     * glDisable(GL_LIGHTING); }
     *
     * public void processKeyboard() { //Square's Size
     * //if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) { //} }
     *
     * public void processMouse() { //squareX = Mouse.getX(); }
     *
     * public void render() { glClear(GL_COLOR_BUFFER_BIT); glLoadIdentity();
     *
     * //Draw a basic square glTranslatef(squareX,squareY,0.0f);
     * glRotatef(squareZ,0.0f,0.0f,1.0f); glTranslatef(-(squareSize >>
     * 1),-(squareSize >> 1),0.0f); glColor3f(0.0f,0.5f,0.5f);
     * glBegin(GL_QUADS); glTexCoord2f(0.0f,0.0f); glVertex2f(0.0f,0.0f);
     * glTexCoord2f(1.0f,0.0f); glVertex2f(squareSize,0.0f);
     * glTexCoord2f(1.0f,1.0f); glVertex2f(squareSize,squareSize);
     * glTexCoord2f(0.0f,1.0f); glVertex2f(0.0f,squareSize); glEnd(); }
     *
     * public void resizeGL() { //2D Scene
     * glViewport(0,0,DISPLAY_WIDTH,DISPLAY_HEIGHT);
     *
     * glMatrixMode(GL_PROJECTION); glLoadIdentity();
     * gluOrtho2D(0.0f,DISPLAY_WIDTH,0.0f,DISPLAY_HEIGHT); glPushMatrix();
     *
     * glMatrixMode(GL_MODELVIEW); glLoadIdentity(); glPushMatrix(); }
     */
    public void run() {

        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if (Display.isVisible()) {
                //processKeyboard();
                //processMouse();
                update();
                //render();
            } else {
                if (Display.isDirty()) {
                    //render();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
            Display.update();
            Display.sync(60);
        }
    }

    public void update() {

        gamestateManager.update();

    }
}
