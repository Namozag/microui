/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui;

import javax.microedition.lcdui.Graphics;
import microui.canvas.MicroCanvas;
import microui.components.Icon;
import microui.components.TextItem;
import microui.generic.GenericItem;
import microui.generic.Style;

/**
 *
 * @author h
 */
public class RichCanvas extends MicroCanvas {
    GenericItem[] items;

    public RichCanvas(GenericItem[] items) {
        this.items = items;
        setStyle(new Style());
    }

    protected void paint(Graphics g) {
        super.paint(g);
        g.setColor(style.colorText);

//        TextItem item = new TextItem("hello my mobile world ya hany", style.font, 150);
//        item.paint(g);
        int shift = 10;
        for(int i=0; i<items.length; i++) {
            items[i].paintShifted(g, 5, shift);
            shift += items[i].h;
        }

    }



}
