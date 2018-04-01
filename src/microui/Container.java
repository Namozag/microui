/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui;

import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import microui.generic.GenericItem;
import microui.utils.ImageManager;

/**
 *
 * @author h
 */
public class Container extends Canvas {

    public int w, h;
    public int margin = 5;
    public int padding = 2;
    public Font font;

    int bgcolor;
    int color;
    Image bgimage;

    Vector components = new Vector();

    public void setStyle(int color, int bgcolor, String url) {
        this.color = color;
        this.bgcolor = bgcolor;
        if(url != null)
            bgimage = ImageManager.get(url);
    }

    public void append(GenericItem component) {
        components.addElement(component);
    }

    protected void clearComponents() {
        components.removeAllElements();
    }

    public void append(GenericItem component, int horizontal, int vertical) {
        int ix=0, iy=0;
        switch(horizontal) {
            case Graphics.LEFT      : ix = margin;    break;
            case Graphics.RIGHT     : ix = w - component.w - margin;   break;
            case Graphics.HCENTER   : ix = (w-component.w)/2;
        }
        switch(vertical) {
            case Graphics.TOP       : iy = margin;    break;
            case Graphics.BOTTOM    : iy = h - component.h - margin ;   break;
            case Graphics.VCENTER   : iy = (h-component.h)/2;
        }
        component.setLocation(ix, iy);
        components.addElement(component);
    }

    public void append(GenericItem component, GenericItem followed, int direction) {
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
    }

    protected void paintBackground(Graphics g) {
        g.setColor(bgcolor);
        g.fillRect(0, 0, w, h);
        if(bgimage != null)
            g.drawImage(bgimage, 0, 0, Graphics.TOP|Graphics.LEFT);
    }

    protected void paintComponents(Graphics g) {
        for(int i=0; i<components.size(); i++) {
            g.setColor(color);
            GenericItem item = (GenericItem) components.elementAt(i);
            item.paint(g);
        }
    }

}
