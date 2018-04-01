/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.oslike;

import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import microui.oslike.components.*;
import microui.utils.ImageManager;

/**
 *
 * @author h
 */
public class Window extends Canvas {
    
    public int w, h;
    public int margin = 5;
    public int padding = 2;
    public Font font;

    public Cursor cursor;
    public StatusBar statusBar;
    int bgcolor;
    Image bgimage;
    boolean busy = false;

    Vector components = new Vector();

    public Window() {
        setFullScreenMode(true);
        this.w = getWidth();
        this.h = getHeight();
        System.out.println("WINDOW w:" + w + "  h:" + h );

        this.font       = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        this.cursor     = getDefaultCursor();
        this.statusBar  = getDefaultStatusBar();
    }

    public Window(Cursor cursor, StatusBar bar, Font font) {
        setFullScreenMode(true);
        this.w = getWidth();
        this.h = getHeight();
        System.out.println("WINDOW w:" + w + "  h:" + h );

        this.font       = font;
        this.cursor     = cursor;
        this.statusBar  = bar;
    }

    public StatusBar getDefaultStatusBar() {
        return new StatusBar(this, "nothing", font);
    }

    public Cursor getDefaultCursor() {
        return new Cursor("/images/gui/cursor/", this);
    }

    public void setStyle(int color, int bgcolor, String url) {
        this.bgcolor = bgcolor;
        if(url != null)
            bgimage = ImageManager.get(url);
    }

    public void setStatusBar(StatusBar bar) {
        this.statusBar = bar;
    }

    public void freez() {
        busy = true;
        cursor.setShape(Cursor.BUSY);
        repaint();
    }

    public void unfreez() {
        busy = false;
        cursor.point();
        repaint();
    }

    public void append(Item component) {
        components.addElement(component);
    }

    public void append(Item component, int horizontal, int vertical) {
        int ix=0, iy=0;
        switch(horizontal) {
            case Graphics.LEFT      : ix = margin;    break;
            case Graphics.RIGHT     : ix = w - component.w - margin;   break;
            case Graphics.HCENTER   : ix = (w-component.w)/2;
        }
        switch(vertical) {
            case Graphics.TOP       : iy = margin;    break;
            case Graphics.BOTTOM    : iy = h - component.h - margin - statusBar.h;   break;
            case Graphics.VCENTER   : iy = (h-component.h)/2;
        }
        component.setLocation(ix, iy);
        components.addElement(component);
    }

    public void append(Item component, Item followed, int direction) {
        int ix=0, iy=0;
        ix = followed.x;
        iy = followed.y;

        switch(direction) {
            case Graphics.LEFT      : ix = followed.x - component.w - margin; break;
            case Graphics.RIGHT     : ix = followed.x + followed.w + margin;    break;
            case Graphics.TOP       : iy = followed.y - component.h - margin;  break;
            case Graphics.BOTTOM    : iy = followed.y + followed.h + margin;    break;
        }
        component.setLocation(ix, iy);
        components.addElement(component);
    }

    protected void paint(Graphics g) {
        g.setFont(font);
        paintBackground(g);
        paintComponents(g);
        paintStatusBar(g);
        paintCursor(g);
    }

    protected void paintBackground(Graphics g) {
        g.setColor(bgcolor);
        g.fillRect(0, 0, w, h);
        if(bgimage != null)
            g.drawImage(bgimage, 0, 0, Graphics.TOP|Graphics.LEFT);
    }

    protected void paintCursor(Graphics g) {
        if(cursor != null)
            cursor.paint(g);
    }

    protected void paintComponents(Graphics g) {
        for(int i=0; i<components.size(); i++) {
            Item obj = (Item) components.elementAt(i);
            obj.paint(g);
        }
    }

    protected void clearComponents() {
        components.removeAllElements();
    }

    protected void paintStatusBar(Graphics g) {
        if(statusBar != null)
            statusBar.paint(g);
    }

    public void keyPressed(int keyCode) {

        if(busy)
            return;

        int key = getGameAction(keyCode);
        cursor.keyPressed(key);

        if(cursor != null) {
            cursor.point();
            cursor.setText("");
        }
        for(int i=0; i<components.size(); i++) {
            Item component = (Item) components.elementAt(i);
            if(component.isPointed()) {
                component.keyPressed(keyCode);

                if(statusBar != null && component instanceof Component)
                    statusBar.setString( ((Component)component).label );
                if(cursor != null) {
                    cursor.hover();
                    cursor.setText(((Component)component).label);
                }
                break;
            }
            if(statusBar != null)
                statusBar.setString(cursor.x + " : " + cursor.y);
        }

        repaint();
    }

    protected void keyRepeated(int keyCode) {
        try{
            keyPressed(keyCode);
        }catch(Exception ex){System.out.print(" kr_ex ");}
    }



}












