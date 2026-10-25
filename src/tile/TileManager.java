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
        mapTileNum = new int[gp.maxCol][gp.maxRow];


        getTileImages();
        loadMap("map1.txt");
    }

    public void loadMap(String mapFileName) {
        try {
            InputStream  inpS = getClass().getResourceAsStream("../../res/maps/"+mapFileName);
            BufferedReader bufR = new BufferedReader(new InputStreamReader(inpS));
            

            int col = 0;
            int row = 0;

            while (row < gp.maxRow) {
                String line = bufR.readLine();
                
                while(col < gp.maxCol)
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
        
        

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        int tileToDraw = 0;

        while(col < gp.maxCol && row < gp.maxRow)
        {
            tileToDraw = mapTileNum[col][row];
            
            g2d.drawImage(tile[tileToDraw].image, x, y, gp.tileSize, gp.tileSize, null);
            x+=gp.tileSize;
            col++;
            

            if(col == gp.maxCol)
            {
                col = 0;
                x= 0;
                row++;
                y+=gp.tileSize;
            }
        }
    }
}