/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.canvas;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import microui.generic.Style;

/**
 *
 * @author h
 */
public class MicroCanvas extends Canvas {

    protected Style style;

    public MicroCanvas() {
        setStyle(new Style());
    }
    
    public void setStyle(Style style) {
        this.style = style;
    }

    protected void paint(Graphics g) {
        if(style != null)
            g.setColor(style.colorBG);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}
