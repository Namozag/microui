package microui.canvas;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Hany ahmed abdallah
 * hanylink@gmail.com
 * hanylink.blogspot.com
 *
 */

public class Loader extends Canvas implements Runnable
{
    String text, subText;
    protected int baseY;
    protected int baseX;
    boolean running = false;
    protected int counter = 0;
    Font font;

    public Loader(String text, String subText) {
        this.text = text;
        this.subText = subText;
        font = Font.getDefaultFont();
        loader = new FrameSet("/images/ajaxarrows/", 6);
        baseY = getHeight()/2;
        baseX = getWidth()/2;
    }

    public Loader(FrameSet set, String text, String subText) {
        this.text = text;
        this.subText = subText;
        font = Font.getDefaultFont();
        loader = set;
        baseY = getHeight()/2;
        baseX = getWidth()/2;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubText(String text) {
        this.subText = text;
    }

    public void start() {
        new Thread(this).start();
    }
    
    public void stop() {
        running = false;
    }

    public void run() {
        running = true;
        while(running) {
            try {
                repaint();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            counter++;
        }
    }

    public FrameSet loader;
    public void paint(Graphics g) {
        g.setColor(0x000000);
        g.drawImage(loader.nextFrame(), baseX, baseY, Graphics.HCENTER|Graphics.BOTTOM);
        if(counter % 5 != 0)
            g.drawString(text, baseX, baseY, Graphics.HCENTER|Graphics.TOP);
        g.drawString(subText, baseX, baseY + font.getHeight(), Graphics.HCENTER|Graphics.TOP);
    }
}
