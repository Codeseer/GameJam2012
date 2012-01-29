/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;

/**
 *
 * @author Scott Adams
 */
public class GridWorld {
    public ArrayList<GameEntity> gameEntities;
    public static final int WIDTH = 512;//4096px X4096 px
    public static final int HEIGHT = 512;
    public static final int BLOCK_SIZE = 8;
    public static boolean[][] tiles = new boolean[WIDTH][HEIGHT];
    public GridWorld()
    {
        gameEntities = new ArrayList();
    }
    
    public void update(ArrayList<GameEntity> gameEntities)
    {
        tiles = new boolean[WIDTH][HEIGHT];
        for(GameEntity ge : gameEntities)  
        {
            if(ge instanceof SolidObject)
            {
                int topLeftH = (int)Math.round(ge.getGameObject().x/BLOCK_SIZE);
                int topLeftV = (int)Math.round(ge.getGameObject().y/BLOCK_SIZE);
                int bottomRightH = (int)Math.round((ge.getGameObject().x+ge.getGameObject().width)/BLOCK_SIZE);
                int bottomRightV = (int)Math.round((ge.getGameObject().y+ge.getGameObject().height)/BLOCK_SIZE);
                
                for(int i = topLeftH; i<=bottomRightH;i++)
                {
                    for(int j = topLeftV; j<bottomRightV; j++)
                    {
                        tiles[i][j] = true;
                    }                
                }
            }
        }
    }
    
    public void  AStar(int originX,int originY, int destX, int destY, int distanceToMove)
    {
        
    }
}
