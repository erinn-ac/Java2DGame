package src.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import src.main.*;

public class Player extends Entity{
    GamePanel gp;
    KeyInputHandler keyIH;

    public Player(GamePanel gp, KeyInputHandler keyIH)
    {
        this.gp = gp;
        this.keyIH = keyIH;

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
        x = 100;
        y = 100;
        speed = 5;
        direction = "front";
    }

    public void update() {
         if(keyIH.upPressed == true) {
            direction = "back";
            y -= speed;
            spriteCounter++;
            //update direction here if want different image displayed 
        }
        else if(keyIH.downPressed == true) {
            direction = "front";
            y += speed;
            spriteCounter++;
        }
        else if(keyIH.leftPressed == true) {
            direction = "left";
            x -= speed;
            spriteCounter++;
        }
        else if(keyIH.rightPressed == true) {
            direction = "right";
            x += speed;
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

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
