package microui.splash;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;
import microui.lcd.Submitable;

/**
 * @author simtec
 */
public class Slider extends Canvas
{
    int i=0;
    boolean stopped = false;;

    int     color;
    Image   image;
    Submitable action;

    /**
     * constructor
     */
    public Slider(String imgUrl, int color, Submitable action)
    {
        setFullScreenMode(true);
        this.color  = color;
        this.action = action;
        try {
            image = Image.createImage(imgUrl);
            preview();
        } catch(Exception e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * paint
     */
    public void paint(Graphics g)
    {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, getWidth()/2 + 100 - i*3, getHeight()/2, Graphics.VCENTER|Graphics.HCENTER);
    }

    public void preview()
    {
        Thread t = new Thread()
        {
            public void run()
            {
                for(i=0; i<40; i++)
                {
                    if(stopped)
                        break;
                    try {
                        sleep(80);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    repaint();
                }
                action.submit();
            }
        };
        t.start();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode)
    {
        stopped = true;
    }
    
}