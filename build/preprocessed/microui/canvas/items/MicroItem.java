/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.canvas.items;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author h
 */
public abstract class MicroItem {

    protected int width;
    protected int height;
    protected Font font;

    public abstract int getMinimumWidth();
    public abstract int getMinimumHeight();
    public abstract int getWidth(int height);
    public abstract int getHeight(int width);
    public abstract void paint(Graphics g, int y);
    public abstract void keyPressed(int keyCode);
}

