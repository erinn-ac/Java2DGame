package src.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.*;
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
            //update direction here if want different image displayed 
        }
        else if(keyIH.downPressed == true) {
            direction = "front";
            y += speed;
        }
        else if(keyIH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if(keyIH.rightPressed == true) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2d) {

        BufferedImage image = null;
        switch(direction) {
            case "front":
                image = front;
                break;
            case "back":
                image = back;
                break;
            case "right":
                image = right;
                break;
            case "left":
                image = left;
                break;
        }

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
