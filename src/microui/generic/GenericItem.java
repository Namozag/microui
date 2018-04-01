/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.generic;

import javax.microedition.lcdui.Graphics;
import microui.interfaces.KeyPressable;
import microui.interfaces.Paintable;
import microui.interfaces.Positionable;

/**
 *
 * @author h
 */
public abstract class GenericItem implements Positionable, Paintable, KeyPressable {

    public int x;
    public int y;
    public int h;
    public int w;
    private boolean visible = true;

    protected Style style;
    
    public void setStyle(Style style) {
        this.style = style;
    }

    public boolean isPointed() {
        return false;
    }

    public void show() {
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

    // paintable
    public void paintBorder(Graphics g) {
        g.drawRect(x, y, w, h);
    }

    public void paintShiftedBorder(Graphics g, int sx, int sy) {
        g.drawRect(x +sx, y +sy, w, h);
    }

    public void paintBackground(Graphics g) {
        g.fillRect(x, y, w, h);
    }

    // positionable
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void paint(Graphics g) {
        
    }

    public void paintShifted(Graphics g, int x, int y) {
        
    }

}
