package microui.canvas;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class Fader
{
    public static final int STATUS_NONE     = 0;
    public static final int STATUS_FADEIN   = 1;
    public static final int STATUS_FADEOUT  = 2;
    public static final int STATUS_BLANK    = 3;

    private Vector fadeImages   = new Vector();
    public boolean animating    = false;
    public int status   = STATUS_NONE;

    /**
     * constructor
     */
    public Fader(boolean fadeOutOnly)
    {
        build();
    }

    private void build()
    {
        for(int i=0; i<9; i++)
        {
            try {
                fadeImages.addElement(Image.createImage("/images/fade/black/" + i + ".png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Image fadeImage = null;
    public void fade(final boolean fadein, final boolean fadeout, final int degree)
    {
        Thread t = new Thread()
        {
            public void run()
            {
                int max = degree;
                if(max == 0)
                    max = fadeImages.size();
                
                animating = true;
                if(fadein)
                {
                    status  = STATUS_FADEIN;
                    for(int i=0; i<degree; i++)
                    {
                        fadeImage = (Image) fadeImages.elementAt(i);
                        System.out.print(" X ");
                        try {
                            sleep(100);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                status = STATUS_BLANK;

                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                if(fadeout)
                {
                    status = STATUS_FADEOUT;
                    for(int i=degree; i>-1; i--)
                    {
                        fadeImage = (Image) fadeImages.elementAt(i);
                        System.out.print(" X ");
                        try {
                            sleep(100);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    fadeImage = null;
                }
                status = STATUS_NONE;
                animating = false;
            }
        };
        t.start();
    }

    /**
     * paint
     */
    Painter fadeScreen = new Painter();
    public void paint(Graphics g)
    {
        fadeScreen.setG(g);
        if(fadeImage != null)
            fadeScreen.insertBgTiles(fadeImage, 0);
    }

}