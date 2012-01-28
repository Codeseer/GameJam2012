/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 *
 * @author Scott Adams
 */
public class GridMap implements TileBasedMap
{
    /** The map width in tiles */
	public static final int WIDTH = 400;
	/** The map height in tiles */
	public static final int HEIGHT = 400;        
        /** The terrain settings for each tile in the map */
	private int[][] terrain = new int[WIDTH][HEIGHT];
        /** Indicator if a given tile has been visited during the search */
	private boolean[][] visited = new boolean[WIDTH][HEIGHT];
    @Override
    public int getWidthInTiles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getHeightInTiles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void pathFinderVisited(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean blocked(Mover mover, int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getCost(Mover mover, int i, int i1, int i2, int i3) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
