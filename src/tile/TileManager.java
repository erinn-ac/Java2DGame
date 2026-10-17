package src.tile;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.main.*;

public class TileManager
{
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[10];

        getTileImages();
    }

    public void getTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/grass.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tile[0].image, 48, 0, gp.tileSize, gp.tileSize, null);
    }
}