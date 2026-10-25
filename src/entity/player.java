package src.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.main.*;

public class Player extends Entity{
    GamePanel gp;
    KeyInputHandler keyIH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyInputHandler keyIH)
    {
        this.gp = gp;
        this.keyIH = keyIH;

        screenX = gp.screenWidth / 2 - (gp.tileSize /2); //this puts the image in the actual centre of the screen
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImages();
    }

    public void getPlayerImages() {

        try {
            front = ImageIO.read(getClass().getResourceAsStream("../../res/player/squirrel_front.png"));
            back = ImageIO.read(getClass().getResourceAsStream("../../res/player/squirrel_back.png"));
            left = ImageIO.read(getClass().getResourceAsStream("../../res/player/squirrel_left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("../../res/player/squirrel_right.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues()
    {
        worldX = 10 * gp.tileSize;
        worldY = 10 * gp.tileSize;
        speed = 5;
        direction = "front";
    }

    public void update() {
         if(keyIH.upPressed == true) {
            direction = "back";
            worldY -= speed;
            spriteCounter++;
            //update direction here if want different image displayed 
        }
        else if(keyIH.downPressed == true) {
            direction = "front";
            worldY += speed;
            spriteCounter++;
        }
        else if(keyIH.leftPressed == true) {
            direction = "left";
            worldX -= speed;
            spriteCounter++;
        }
        else if(keyIH.rightPressed == true) {
            direction = "right";
            worldX += speed;
            spriteCounter++;
        }

       
        if(spriteCounter > 15) //only switches every 15 frames (otherwise it would be instantaneous)
        {
            if(spriteNum == 1)
            {
                spriteNum = 2;
            }
            else if(spriteNum == 2)
            {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2d) {

        BufferedImage image = null;
        switch(direction) {
            case "front":
                if(spriteNum == 1)
                {
                    image = front;
                }
                else if(spriteNum == 2)
                {
                    image = front; //this will be updated to another image so the character looks like it is actually moving in the future (i just haven't drawn it yet)
                }
                break;
            case "back":
                if(spriteNum == 1)
                {
                    image = back;
                }
                else if(spriteNum == 2)
                {
                    image = back; 
                }
                break;
            case "right":
                if(spriteNum == 1)
                {
                    image = right;
                }
                else if(spriteNum == 2)
                {
                    image = right; 
                }
                break;
            case "left":
                if(spriteNum == 1)
                {
                    image = left;
                }
                else if(spriteNum == 2)
                {
                    image = left; 
                }
                break;
        }

        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
