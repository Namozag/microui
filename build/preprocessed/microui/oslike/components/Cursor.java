/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike.components;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import microui.oslike.Desktop;
import microui.utils.ImageManager;

/**
 *
 * @author h
 */
public class Cursor extends Item {

    public final static int NONE    = -1;
    public final static int ARROW   = 0;
    public final static int HAND    = 1;
    public final static int BUSY    = 2;

    int shape;

    int step = 20;
    Image[] images = new Image[3];
    String text;

    public Cursor(String url, Desktop win) {
        super(win);
        images[0] = ImageManager.get(url + "arrow.png");
        images[1] = ImageManager.get(url + "hand.png");
        images[2] = ImageManager.get(url + "busy.png");
        setLocation(win.w/2, win.h/2);
    }

    public Cursor(Image[] images, Desktop win) {
        super(win);
        this.images = images;
        setLocation(win.w/2, win.h/2);
    }

    public void paint(Graphics g) {
        if(shape > -1) {
            g.drawImage(images[shape], x, y, ORIGIN);
            if(text != null)
                g.drawString(text, x+images[shape].getWidth()/2, y+images[shape].getHeight(), Graphics.TOP|Graphics.HCENTER);
        }
//        System.out.println(x + " | " + y);
    }

    public void pointTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setShape(int i) {
        shape = i;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void hover() {
        shape = HAND;
    }

    public void point() {
        shape = ARROW;
    }

    public void busy() {
        shape = BUSY;
    }

    public void move(int direction) {
        keyPressed(direction);
    }

    public void keyPressed(int key) {
//        System.out.println("%%");
        switch (key) {
            case Canvas.UP      : y -= step;    break;
            case Canvas.DOWN    : y += step;    break;
            case Canvas.LEFT    : x -= step;    break;
            case Canvas.RIGHT   : x += step;    break;
        }
        if(x < 0) x = 0;
        if(y < 0) y = 0;
        if(x > window.w-step) x = window.w-step;
        if(y > window.h-step) y = window.h-step;

    }
}
