/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import javax.microedition.lcdui.Graphics;
import microui.oslike.Desktop;

/**
 *
 * @author h
 */
public abstract class Item {
    final int ORIGIN = Graphics.TOP | Graphics.LEFT;
    final int CENTER = Graphics.TOP | Graphics.HCENTER;

    public int x;
    public int y;
    public int h;
    public int w;
    protected Desktop window;
    private boolean visible = true;

//    public PositionedObject(int x, int y, Window win) {
//        this(win);
//        setLocation(x, y);
//    }

    public Item(Desktop win) {
        this.window = win;
    }

    abstract public void paint(Graphics g);
    abstract public void keyPressed(int keyCode);

    public boolean isPointed() {
        return isPointed(window.cursor);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public void show() {
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

    public boolean isPointed(Cursor c) {
        if(c.x > x && c.x < x+w && c.y > y && c.y < y+h) {
            return true;
        }
        return false;
    }
}
