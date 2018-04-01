/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package microui.components;

import microui.generic.GenericItem;
import javax.microedition.lcdui.Graphics;
import microui.utils.TextPainter;

/**
 *
 * @author h
 */
public class MicroTextItem extends GenericItem {
    TextPainter painter;

    public MicroTextItem(TextPainter painter) {
        this.painter = painter;
        h = painter.getHeight();
        w = painter.getWidth();
    }

    public void paint(Graphics g) {
        painter.paint(g, x, y);
    }

    public void keyPressed(int keyCode) {
    }

    public void paintShifted(Graphics g, int sx, int sy) {
        painter.paint(g, x + sx, y + sy);
    }

}