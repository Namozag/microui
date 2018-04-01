/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.canvas;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author h
 */
public class LoaderCanvas extends Loader {

    public LoaderCanvas(String text, String subText) {
        super(text, subText);
    }

    public LoaderCanvas(FrameSet set, String text, String subText) {
        super(set, text, subText);
    }

    Painter painter = new Painter(getWidth(), getHeight());
    public void paint(Graphics g)
    {
        painter.setG(g);
        painter.fillWhite();
        super.paint(g);
    }

}
