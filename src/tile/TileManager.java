package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import src.main.*;

public class TileManager
{
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];


        getTileImages();
        loadMap("world_map.txt");
    }

    public void loadMap(String mapFileName) {
        try {
            InputStream  inpS = getClass().getResourceAsStream("../../res/maps/"+mapFileName);
            BufferedReader bufR = new BufferedReader(new InputStreamReader(inpS));
            

            int col = 0;
            int row = 0;

            while (row < gp.maxWorldRow) {
                String line = bufR.readLine();
                
                while(col < gp.maxWorldCol)
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                col = 0;
                row++;
                
            }

            bufR.close();
            
        } catch (Exception e) {
        }
    }

    public void getTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/water.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d)
    {
        
        

        int wCol = 0;
        int wRow = 0;
        int tileToDraw = 0;

        while(wCol < gp.maxWorldCol && wRow < gp.maxWorldRow)
        {
            tileToDraw = mapTileNum[wCol][wRow];

            int wX = wCol * gp.tileSize;
            int wY = wRow * gp.tileSize;
            int screenX = wX - gp.player.worldX + gp.player.screenX;
            int screenY = wY - gp.player.worldY + gp.player.screenY;

            if(wX > gp.player.worldX - gp.player.screenX - gp.tileSize && wX < gp.player.worldX + gp.player.screenX + gp.tileSize && wY > gp.player.worldY - gp.player.screenX - gp.tileSize && wY < gp.player.worldY + gp.player.screenY + gp.tileSize)
            {
                g2d.drawImage(tile[tileToDraw].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            
            screenX+=gp.tileSize;
            wCol++;
            

            if(wCol == gp.maxWorldCol)
            {
                wCol = 0;
                wRow++;
                wX= 0;
                wY+=gp.tileSize;
                
            }
        }
    }
}