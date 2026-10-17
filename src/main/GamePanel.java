package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel; 
import src.entity.*;
import src.tile.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int ogTileSize = 16; //before scaling
    final int scale = 3;
    public final int tileSize = ogTileSize * scale;
    final int maxCol = 16;
    final int maxRow = 12;
    final int screenWidth = tileSize * maxRow;
    final int screenHeight = tileSize * maxCol;
    
    TileManager tileMan = new TileManager(this);
    Thread gameThread; //when the game thread is started, GamePanel's run method is called
    KeyInputHandler keyIH = new KeyInputHandler();
    Player player = new Player(this, keyIH);

    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenHeight, screenWidth));
        this.setBackground(new Color(157,199,255));
        this.setDoubleBuffered(true); //helds rendering performance
        this.addKeyListener(keyIH);
        this.setFocusable(true);
        

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    public void run() {

        double intervalBetweenDraws = 1000000000/FPS; //time between each draw in nanoseconds 
        double nextDrawTime = System.nanoTime() + intervalBetweenDraws;

        while(gameThread != null) {

            //UPDATE
            update();

            //DRAW
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; 
                
                if(remainingTime <0)
                {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += intervalBetweenDraws;
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        
        }

            
    }


    

    public void update()
    {
       player.update();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        tileMan.draw(g2d); //needs to be before player.draw()
        player.draw(g2d); 

        g2d.dispose();

        

    }


}