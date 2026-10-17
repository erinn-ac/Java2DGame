package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements KeyListener
{
    public boolean upPressed, leftPressed, rightPressed, downPressed;

    @Override 
    public void keyTyped(KeyEvent event)
    {
        //not used
    }

    @Override 
    public void keyPressed(KeyEvent event)
    {
        int code = event.getKeyCode();

        if(code == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if(code == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S)
        {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_D)
        {
            downPressed = true;
        }
    }

    @Override 
    public void keyReleased(KeyEvent event)
    {
        int code = event.getKeyCode();

        if(code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if(code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S)
        {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_D)
        {
            downPressed = false;
        }
    }
    



}