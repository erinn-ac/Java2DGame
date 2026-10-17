package scr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel; 

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int ogTileSize = 16; //before scaling
    final int scale = 3;
    final int tileSize = ogTileSize * scale;
    final int maxCol = 16;
    final int maxRow = 12;
    final int screenWidth = tileSize * maxRow;
    final int screenHeight = tileSize * maxCol;
    
    Thread gameThread; //when the game thread is started, GamePanel's run method is called

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenHeight, screenWidth));
        this.setBackground(new Color(157,199,255));
        this.setDoubleBuffered(true); //helds rendering performance
        

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    public void run() {

        while(gameThread != null) {
            System.out.println("game loop running");

            //UPDATE

            //DRAW
        }


    }

    public void update()
    {

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }


}