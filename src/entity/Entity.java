package src.entity;

import java.awt.image.BufferedImage;

public class Entity
{
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage front, back, left, right;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
