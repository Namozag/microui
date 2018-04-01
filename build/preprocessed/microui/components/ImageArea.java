/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microui.components;

import microui.generic.GenericItem;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author h
 */
public class ImageArea extends GenericItem {

    Image image = null;
    int x = 0, y = 0, v = 0, h = 0;

    public ImageArea(Image im) {
        image = im;
    }

    public ImageArea(Image im, int x, int y) {
        this.image = im;
        setLocation(x, y);
    }

    public ImageArea(Image im, int x, int y, int h, int v) {
        this.image = im;
        setLocation(x, y);
        setOrientation(h, v);
    }

    public void setOrientation(int h, int v) {
        this.h = h;
        this.v = v;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, h|v);
    }

    public void paintShifted(Graphics g, int sx, int sy) {
        g.drawImage(image, x +sx, y +sy, h|v);
    }

    public void keyPressed(int keyCode) {
        
    }
}
