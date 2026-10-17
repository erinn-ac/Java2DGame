package src.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import src.main.*;

public class Player extends Entity{
    GamePanel gp;
    KeyInputHandler keyIH;

    public Player(GamePanel gp, KeyInputHandler keyIH)
    {
        this.gp = gp;
        this.keyIH = keyIH;

        setDefaultValues();
    }

    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        speed = 5;
    }

    public void update() {
         if(keyIH.upPressed == true) {
            y -= speed;
        }
        else if(keyIH.downPressed == true) {
            y += speed;
        }
        else if(keyIH.leftPressed == true) {
            x -= speed;
        }
        else if(keyIH.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
